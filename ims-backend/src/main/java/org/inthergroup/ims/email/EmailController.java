package org.inthergroup.ims.email;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/api/message")
    String sendEmailMessage() {
        this.emailService.sendMessage("inther.int22@gmail.com",
                "Greetings!",
                "Test message");
        return "Message sent";

    }


}
