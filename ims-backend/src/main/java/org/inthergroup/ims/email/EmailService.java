package org.inthergroup.ims.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    void sendMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("isd.epe2021@gmail.com");
        message.setTo(to.split(","));
        message.setSubject(subject);
        message.setText(text);
        this.emailSender.send(message);
    }
}
