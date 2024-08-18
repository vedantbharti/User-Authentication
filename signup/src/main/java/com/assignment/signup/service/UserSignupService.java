package com.assignment.signup.service;


import com.assignment.signup.dao.entity.User;
import com.assignment.signup.dao.models.UserDetails;
import com.assignment.signup.dao.repository.UserDetailsRepository;
import com.assignment.signup.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserSignupService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public UserDetails registerUser(UserDetails userDetails){

        User user = User.builder()
                .email(userDetails.getEmail())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .contact(userDetails.getContact())
                .password(passwordEncoder(userDetails.getPassword()))
                .build();

        User savedUser = userDetailsRepository.findByEmail(user.getEmail());
        if(Objects.isNull(savedUser)){
            userDetailsRepository.save(user);
        } else {
            throw new UserAlreadyExistsException("User already exists!");
        }

        return userDetails;
    }

    private String passwordEncoder(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder.encode(password);
    }


}
