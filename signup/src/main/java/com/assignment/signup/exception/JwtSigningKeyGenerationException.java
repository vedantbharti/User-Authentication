package com.assignment.signup.exception;

public class JwtSigningKeyGenerationException extends RuntimeException{

    public JwtSigningKeyGenerationException(String message){
        super(message);
    }
}
