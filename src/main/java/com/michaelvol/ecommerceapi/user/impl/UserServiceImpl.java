package com.michaelvol.ecommerceapi.user.impl;

import com.michaelvol.ecommerceapi.authentication.dao.UserAvailability;
import com.michaelvol.ecommerceapi.exception.exceptions.BadRequestException;
import com.michaelvol.ecommerceapi.user.User;
import com.michaelvol.ecommerceapi.user.UserRepository;
import com.michaelvol.ecommerceapi.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found"));
    }


    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


    public User registerUser(User requestedUser) throws BadRequestException {
        //Availability Checks
        //Email
        boolean emailIsAvailable = checkEmailAvailability(requestedUser.getEmail()).getIsAvailable();
        if (!emailIsAvailable)
            throw new BadRequestException("Email " + requestedUser.getEmail() + " is not available");

        //Username
        boolean usernameIsAvailable = checkUsernameAvailability(requestedUser.getUsername()).getIsAvailable();
        if (!usernameIsAvailable)
            throw new BadRequestException("Username " + requestedUser.getUsername() + " is not available");

        String firstName = requestedUser.getFirstName();
        String lastName = requestedUser.getLastName();
        String username = requestedUser.getUsername();
        String email = requestedUser.getEmail();
        String password = requestedUser.getPassword();

        password = passwordEncoder.encode(password);

        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .email(email)
                .password(password)
                .role(requestedUser.getRole())
                .build();

        User savedUser = userRepository.save(user);
        return user;
    }

    @Override
    public UserAvailability checkUsernameAvailability(String username) {
        boolean userExists = userRepository.findUserByUsername(username).isPresent();
        return new UserAvailability(!userExists);
    }

    @Override
    public UserAvailability checkEmailAvailability(String email) {
        boolean userExists = userRepository.findUserByEmail(email).isPresent();
        return new UserAvailability(!userExists);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User with id " + userId + " not found"));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public void updateUser(User user) {

    }


}
