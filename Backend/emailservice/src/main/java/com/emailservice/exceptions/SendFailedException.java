package com.emailservice.exceptions;

public class SendFailedException extends RuntimeException{

    private final String s;

    public SendFailedException(String s){
        this.s = s;
    }

    public String getMessage(){
        return s;
    }
}
