package com.ptm.user.service.business.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ptm.user.service.business.UserBusiness;
import com.ptm.user.service.config.AuthoritiesConstants;
import com.ptm.user.service.config.Constants;
import com.ptm.user.service.config.security.SecurityUtils;
import com.ptm.user.service.config.security.TokenProvider;
import com.ptm.user.service.controller.dto.RegisterUserDTO;
import com.ptm.user.service.controller.dto.UserDTO;
import com.ptm.user.service.controller.vo.LoginVM;
import com.ptm.user.service.enitity.Authority;
import com.ptm.user.service.enitity.Role;
import com.ptm.user.service.enitity.User;
import com.ptm.user.service.exception.EmailAlreadyUsedException;
import com.ptm.user.service.exception.InternalServerErrorException;
import com.ptm.user.service.exception.InvalidPasswordException;
import com.ptm.user.service.exception.UserException;
import com.ptm.user.service.repository.AuthorityRepository;
import com.ptm.user.service.repository.RoleRepository;
import com.ptm.user.service.repository.UserRepository;
import com.ptm.user.service.util.RandomUtil;
import com.ptm.user.service.util.Utility;
@Service
@Transactional
public class UserBusinessImpl implements UserBusiness {

    private final Logger log = LoggerFactory.getLogger(UserBusiness.class);

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private  AuthorityRepository authorityRepository;
    
    @Autowired
	private RoleRepository roleRepository;

    @Autowired
	private AuthenticationManager authenticationManager;


	@Autowired
	private TokenProvider tokenProvider;
   
    public String validateUser(LoginVM loginVM) {
    	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginVM.getUsername(), loginVM.getPassword());

		Authentication authentication=null;
		try {
			authentication = authenticationManager.authenticate(authenticationToken);
		} catch (AuthenticationException e) {
			throw new UserException();
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
		String jwt = tokenProvider.createToken(authentication, rememberMe);
		return jwt;
    }
	
    public Optional<User> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        Optional<User> userdata=  userRepository.findOneByActivationKey(key)
        .map(user -> {
            // activate given user for the registration key.
            user.setActivated(true);
            user.setActivationKey(null);
            
            log.debug("Activated user: {}", user);
            return user;
        });
        if (!userdata.isPresent()) {
            throw new InternalServerErrorException("No user was found for this activation key");
        }
        
        return userdata;
    }

    public Optional<User> completePasswordReset(String newPassword, String key) {
       log.debug("Reset user password for reset key {}", key);

       return userRepository.findOneByResetKey(key)
           .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
           .map(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);

                return user;
           });
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmailIgnoreCase(mail)
            .filter(User::isActivated)
            .map(user -> {
                user.setResetKey(RandomUtil.generateResetKey());
                user.setResetDate(Instant.now());

                return user;
            });
    }

    public User registerUser(RegisterUserDTO registerUserDTO) {
    	
    	if (!Utility.checkPasswordLength(registerUserDTO.getPassword())) {
            throw new InvalidPasswordException();
        }

    	 userRepository.findOneByUsername(registerUserDTO.getUserName().toLowerCase()).ifPresent(u -> {throw new UserException();});
         userRepository.findOneByEmailIgnoreCase(registerUserDTO.getEmail()).ifPresent(u -> {throw new EmailAlreadyUsedException();});
    	
        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(registerUserDTO.getPassword());
        newUser.setUsername(registerUserDTO.getUserName());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setPhonenumber(registerUserDTO.getPhoneNumber());
        newUser.setEmail(registerUserDTO.getEmail());
        // new user is not active
        newUser.setActivated(false);
        newUser.setLangKey("en");
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User createUser(UserDTO userDTO) {
    	 if (userRepository.findOneByUsername(userDTO.getUserName().toLowerCase()).isPresent()) {
            throw new UserException();
        } else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        }
    	
        User user = new User();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());

        if (userDTO.getLangKey() == null) {
            user.setLangKey(Constants.DEFAULT_LANGUAGE); // default language
        } else {
            user.setLangKey(userDTO.getLangKey());
        }
        if (userDTO.getAuthorities() != null) {
            Set<Authority> authorities = userDTO.getAuthorities().stream()
                .map(authorityRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
            user.setAuthorities(authorities);
        }
        
        if (userDTO.getAuthorities() != null) {
            Set<Role> roles = userDTO.getAuthorities().stream()
                .map(roleRepository::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
            user.setRoles(roles);
        }
        
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(Instant.now());
        user.setActivated(true);
        userRepository.save(user);
        
        log.debug("Created Information for User: {}", user);
        return user;
    }

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param firstName first name of user
     * @param lastName last name of user
     * @param email email id of user
     * @param langKey language key
     */
    public void updateUser(String firstName, String lastName, String email, String langKey) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findOneByUsername)
            .ifPresent(user -> {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setLangKey(langKey);

                log.debug("Changed Information for User: {}", user);
            });
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update
     * @return updated user
     */
    public Optional<UserDTO> updateUser(UserDTO userDTO) {
    	
    	 Optional<User>   existingUser = userRepository.findOneByUsername(userDTO.getUserName().toLowerCase());
         if (!existingUser.isPresent()) {
             throw new UserException();
         }
        Optional<User> userdata=userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
         if (!Objects.equals(userdata.get().getUsername().toLowerCase(),userDTO.getUserName().toLowerCase())) {
             throw new EmailAlreadyUsedException();
         }
         
         Set<Role> roles = userDTO.getAuthorities().stream()
                 .map(roleRepository::findByName)
                 .filter(Optional::isPresent)
                 .map(Optional::get)
                 .collect(Collectors.toSet());
         
        return Optional.of(existingUser)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(user -> {

                user.setUsername(userDTO.getUserName());
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setEmail(userDTO.getEmail());
                user.setActivated(userDTO.isActivated());
                user.setLangKey(userDTO.getLangKey());
                Collection<Role> roles1= user.getRoles();
                roles1.clear();
                roles1=roles;
                Set<Authority> managedAuthorities = user.getAuthorities();
                managedAuthorities.clear();
                userDTO.getAuthorities().stream()
                    .map(authorityRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(managedAuthorities::add);

                log.debug("Changed Information for User: {}", user);
                return user;
            })
            .map(UserDTO::new);
    }

    public void deleteUser(String login) {
        userRepository.findOneByUsername(login).ifPresent(user -> {
            userRepository.delete(user);
            
            log.debug("Deleted User: {}", user);
        });
    }

    public void changePassword(String currentClearTextPassword, String newPassword) {
    	 if (!Utility.checkPasswordLength(newPassword)) {
             throw new InvalidPasswordException();
         }
        SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findOneByUsername)
            .ifPresent(user -> {
                String currentEncryptedPassword = user.getPassword();
                if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                    throw new InvalidPasswordException();
                }
                String encryptedPassword = passwordEncoder.encode(newPassword);
                user.setPassword(encryptedPassword);

                log.debug("Changed password for User: {}", user);
            });
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAllByUsernameNot(pageable, Constants.ANONYMOUS_USER).map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByUsername(login);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(Long id) {
        return userRepository.findOneWithAuthoritiesById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS));
        for (User user : users) {
            log.debug("Deleting not activated user {}", user.getUsername());
            userRepository.delete(user);
        }
    }

    /**
     * @return a list of all the authorities
     */
    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }

   
}
