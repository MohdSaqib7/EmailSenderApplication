package com.emailservice.exceptions;

public class AuthenticationFailedException extends RuntimeException{

    private final String s;

    public AuthenticationFailedException(String s){
        this.s = s;
    }

    public String getMessage(){
        return s;
    }
}
