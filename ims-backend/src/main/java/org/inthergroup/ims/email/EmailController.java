package org.inthergroup.ims.email;

import org.inthergroup.ims.feedback.FeedbackWithAuthorNameDTO;
import org.inthergroup.ims.internship.service.InternshipService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.mail.internet.AddressException;

@RestController


public class EmailController {

    private EmailService emailService;
    private InternshipService internshipService;

    @Value("${feedback.recipients}")
    private String feedbackRecipients;

    public EmailController(EmailService emailService,InternshipService internshipService) {
        this.internshipService = internshipService;
        this.emailService = emailService;
    }

    @PostMapping("/api/message/{id}")
    String sendEmailMessage(@PathVariable final String id, @RequestBody final FeedbackWithAuthorNameDTO feedbackWithAuthorNameDTO) throws AddressException {
        String textOfMail  = "Author: " + feedbackWithAuthorNameDTO.getUserName() + " \nFeedback: " + feedbackWithAuthorNameDTO.getFeedback() +
                "\nInternshipName: " + internshipService.getInternship(id).getProjectName();
        this.emailService.sendMessage(feedbackRecipients,
                "Greetings!",
                textOfMail
                );
        return "Message sent";

    }
}
