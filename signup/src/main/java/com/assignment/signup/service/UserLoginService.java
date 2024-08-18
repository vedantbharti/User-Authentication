package com.assignment.signup.service;

import com.assignment.signup.dao.models.LoginDetails;
import com.assignment.signup.exception.AuthenticationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    public String verify(LoginDetails loginDetails){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDetails.getEmail(),loginDetails.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(loginDetails.getEmail());
        } else {
            throw new AuthenticationFailedException("The user is not authenticated!");
        }
    }


}
