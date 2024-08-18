package com.assignment.signup.service;

import com.assignment.signup.dao.entity.User;
import com.assignment.signup.dao.models.UserPrincipal;
import com.assignment.signup.dao.repository.UserDetailsRepository;
import com.assignment.signup.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDetailsRepository.findByEmail(email);
        if(Objects.isNull(user)){
            throw new UserNotFoundException("User doesn't exist");
        }

        return new UserPrincipal(user);
    }
}
