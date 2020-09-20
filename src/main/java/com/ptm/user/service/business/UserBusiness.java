package com.ptm.user.service.business;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.ptm.user.service.controller.dto.RegisterUserDTO;
import com.ptm.user.service.controller.dto.UserDTO;
import com.ptm.user.service.controller.vo.LoginVM;
import com.ptm.user.service.enitity.User;

/**
 * Service class for managing users.
 */

public interface UserBusiness {

  


    public Optional<User> activateRegistration(String key) ;

    public Optional<User> completePasswordReset(String newPassword, String key) ;
    

    public Optional<User> requestPasswordReset(String mail) ;

    public User registerUser(RegisterUserDTO registerUserDTO) ;

    public User createUser(UserDTO userDTO) ;

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param firstName first name of user
     * @param lastName last name of user
     * @param email email id of user
     * @param langKey language key
     */
    public void updateUser(String firstName, String lastName, String email, String langKey) ;

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update
     * @return updated user
     */
    public Optional<UserDTO> updateUser(UserDTO userDTO) ;
    public void deleteUser(String login) ;

    public void changePassword(String currentClearTextPassword, String newPassword);

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllManagedUsers(Pageable pageable) ;
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) ;

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(Long id); 
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() ;

    
    /**
     * @return a list of all the authorities
     */
    public List<String> getAuthorities();
    public String validateUser(LoginVM loginVM);

}
