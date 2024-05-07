package com.emailservice.controller;

import com.emailservice.model.Email;
import com.emailservice.model.StatusResponse;
import com.emailservice.services.EmailService;
import com.emailservice.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.AuthenticationFailedException;
import javax.mail.SendFailedException;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    LoginService loginService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/sendEmail")
    public StatusResponse sendEmail(@RequestBody Email email) throws AuthenticationFailedException, SendFailedException {
        if(!check(email)){
            return new StatusResponse(400, "Bad Request");
        }
        StatusResponse response  = emailService.sendEmail(email);
        return response;
    }

    @GetMapping("/login")
    public String checkEmailAndPassword(@RequestParam(name = "emailAdd") String emailAdd, @RequestParam(name = "emailPass") String emailPass)throws AuthenticationFailedException, SendFailedException{
        return loginService.loginForm(emailAdd, emailPass);
    }

    public boolean check(Email email){
        if(email.getEmailAdd()=="" ||
           email.getEmailPass()=="" ||
           email.getTo()=="" ||
           email.getSubject()=="" ||
           email.getMessage()=="")
        {
            return false;
        }
        return true;
    }
}
