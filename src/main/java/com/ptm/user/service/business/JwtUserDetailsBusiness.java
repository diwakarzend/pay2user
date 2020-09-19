package com.ptm.user.service.business;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ptm.user.service.config.CustomUserDetails;
import com.ptm.user.service.enitity.Role;
import com.ptm.user.service.enitity.User;
import com.ptm.user.service.exception.UserNotActivatedException;
import com.ptm.user.service.repository.UserRepository;

@Service
public class JwtUserDetailsBusiness implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(JwtUserDetailsBusiness.class);

    private final UserRepository userRepository;

    public JwtUserDetailsBusiness(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CustomUserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        if (new EmailValidator().isValid(login, null)) {
            Optional<User> userByEmailFromDatabase = userRepository.findOneWithAuthoritiesByEmail(login);
            return userByEmailFromDatabase.map(user -> createSpringSecurityUser(login, user))
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
        }

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        Optional<User> userByLoginFromDatabase = userRepository.findOneWithAuthoritiesByUsername(lowercaseLogin);
        return userByLoginFromDatabase.map(user -> createSpringSecurityUser(lowercaseLogin, user))
            .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));

    }

    private CustomUserDetails createSpringSecurityUser(String lowercaseLogin, User user) {
        if (!user.getActivated()) {
           throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }
        Set<Role> grantedAuthorities = user.getRoles().stream()
                                                      .collect(Collectors.toSet());
       String userroles= grantedAuthorities.stream().map(Role::getName).collect(Collectors.joining(","));
        return new CustomUserDetails(user.getUsername(),
            user.getPassword(),"",
            grantedAuthorities,userroles);
    }
}