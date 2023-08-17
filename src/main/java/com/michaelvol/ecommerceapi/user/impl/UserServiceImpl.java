package com.michaelvol.ecommerceapi.user.impl;

import com.michaelvol.ecommerceapi.exception.exceptions.BadRequestException;
import com.michaelvol.ecommerceapi.user.User;
import com.michaelvol.ecommerceapi.user.UserRepository;
import com.michaelvol.ecommerceapi.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found"));
    }


    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    public void registerUser(User user) throws BadRequestException {
        boolean userAlreadyExists = userRepository.findUserByEmail(user.getEmail()).isPresent();

        if (userAlreadyExists) {
            throw new BadRequestException("User with email " + user.getEmail() + " already exists");
        }
        

    }


}
