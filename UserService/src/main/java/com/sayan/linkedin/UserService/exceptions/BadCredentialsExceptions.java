package com.sayan.linkedin.UserService.exceptions;

public class BadCredentialsExceptions extends RuntimeException{
    public BadCredentialsExceptions(String message) {
        super(message);
    }
}
