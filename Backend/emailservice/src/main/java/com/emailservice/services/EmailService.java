package com.emailservice.services;

import com.emailservice.model.Email;
import com.emailservice.model.StatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@Slf4j
public class EmailService {

    public StatusResponse sendEmail(Email email) throws AuthenticationFailedException, SendFailedException {

        boolean flag = false;

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        log.info(email.getEmailPass(), email.getEmailPass(), email.getTo());

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email.getEmailAdd(), email.getEmailPass());
            }
        });
        session.setDebug(true);

        MimeMessage mimeMessage = new MimeMessage(session);
        try{
            //from
            mimeMessage.setFrom(email.getEmailAdd());

            //add Recipient
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));

            //add mail subject
            mimeMessage.setSubject(email.getSubject());

            //add message
            mimeMessage.setText(email.getMessage());

            //send
            Transport.send(mimeMessage);
            flag = true;

            log.info("sent success....");

        }catch (AuthenticationFailedException afe){
            throw new AuthenticationFailedException();
        }catch (SendFailedException sfe){
            throw new SendFailedException("");
        }catch (Exception ex){
            ex.printStackTrace();
            return new StatusResponse(404, "Something went wrong! try again");
        }
        return new StatusResponse(200, "Successfully sent");
    }
}
