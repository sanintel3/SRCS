package com.srcs.acknowledgement.domain.services;

import com.srcs.acknowledgement.domain.Donation;
import com.srcs.acknowledgement.domain.EmailStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static java.lang.Thread.sleep;

/**
 * Created by santhosh on 09/07/16.
 */
@Component
public class EmailSender {

    private final EmailBody emailBody;
    private final Session mailSession;
    private final String username;

    @Autowired
    public EmailSender(EmailBody emailBody, Session mailSession, @Value("${gmail.authentication.username}") String username) {
        this.emailBody = emailBody;
        this.mailSession = mailSession;
        this.username = username;
    }

    public EmailStatus send(Donation donation) {

        boolean hasEmailSent = true;
        String errorMessage = "";

        try {
            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(donation.email().value()));

            message.setSubject("Thanks for your donation");
            message.setText(emailBody.content(donation));

            Transport.send(message);

            sleep(1000 * 2);
            System.out.println("Mail sent to:" + donation.email().value());
        } catch (Exception e) {
            hasEmailSent = false;
            errorMessage = e.getMessage();
        }

        return new EmailStatus(donation.email(), hasEmailSent, errorMessage);

    }
}
