package com.assignment.signup.exception;

public class AuthenticationFailedException extends RuntimeException{

    public AuthenticationFailedException(String message){
        super(message);
    }
}
