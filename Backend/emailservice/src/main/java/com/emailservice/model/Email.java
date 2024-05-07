package com.emailservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Email {

    private String emailAdd;

    private String emailPass;

    private String to;

    private String subject;

    private String message;
}
