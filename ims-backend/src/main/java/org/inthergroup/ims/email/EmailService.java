package org.inthergroup.ims.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service

public class EmailService {

    JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    void sendMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("berezovski789@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        this.emailSender.send(message);
        System.out.println("Mail send successfully!");
    }


    public Boolean sendMessageWithPredefinedRecipients(Email email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email.getRecipients().stream().collect(Collectors.joining(",")));
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getBody());

        Boolean isSent = false;
        try {
            emailSender.send(mailMessage);
            isSent = true;
        } catch (Exception e) {
            System.out.println("Message isn't sent");
        }
        return isSent;
    }


}
