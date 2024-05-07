package com.emailservice.services;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class LoginService{

        public String loginForm(String emailAdd, String emailPass) throws AuthenticationFailedException,SendFailedException{

            Properties properties = System.getProperties();
            properties.put("mail.smtp.host","smtp.gmail.com");
            properties.put("mail.smtp.port","465");
            properties.put("mail.smtp.ssl.enable","true");
            properties.put("mail.smtp.auth","true");

            try {
                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailAdd, emailPass);
                    }
                });
                session.setDebug(true);
                MimeMessage mimeMessage = new MimeMessage(session);
//                mimeMessage.setFrom(emailAdd);
//                Transport.send(mimeMessage);
//            }catch (AuthenticationFailedException ae){
//                throw new AuthenticationFailedException("wrong input");
            }catch (Exception ex){
                return "Wrong input";
            }
            return "success";
        }
}
