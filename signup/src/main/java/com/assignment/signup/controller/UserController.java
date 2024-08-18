package com.assignment.signup.controller;


import com.assignment.signup.dao.models.LoginDetails;
import com.assignment.signup.dao.models.UserDetails;
import com.assignment.signup.exception.AuthenticationFailedException;
import com.assignment.signup.exception.UserNotFoundException;
import com.assignment.signup.service.UserLoginService;
import com.assignment.signup.service.UserService;
import com.assignment.signup.service.UserSignupService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    private UserSignupService userSignupService;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> signup(@RequestBody UserDetails userDetails){

        try{
            UserDetails signedUpUser = userSignupService.registerUser(userDetails);
        } catch(Exception e){
            return new ResponseEntity<>("User already exists!", HttpStatus.OK);
        }

        return new ResponseEntity<>("Signup Successful!", HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginDetails loginDetails){

        try{
           String jwtToken = userLoginService.verify(loginDetails);
           return new ResponseEntity<>(jwtToken,HttpStatus.OK);
        } catch(AuthenticationFailedException e){
            return new ResponseEntity<>("User is not authenticated!", HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping("/user/{email}")
    public ResponseEntity<UserDetails> getUserWithEmail(@PathVariable String email){

        try{
            UserDetails userDetails = userService.getUserWithEmail(email);
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        } catch(UserNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }


    }
}
