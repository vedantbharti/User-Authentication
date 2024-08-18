package com.assignment.signup.service;


import com.assignment.signup.dao.entity.User;
import com.assignment.signup.dao.models.UserDetails;
import com.assignment.signup.dao.repository.UserDetailsRepository;
import com.assignment.signup.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public UserDetails getUserWithEmail(String email){
        User user = userDetailsRepository.findByEmail(email);
        if(!Objects.isNull(user)){
            return UserDetails.builder()
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .contact(user.getContact())
                    .build();
        } else {
            throw new UserNotFoundException("User does not exist");
        }
    }

}
