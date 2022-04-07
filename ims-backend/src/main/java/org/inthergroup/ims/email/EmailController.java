package org.inthergroup.ims.email;

import lombok.extern.slf4j.Slf4j;
import org.inthergroup.ims.candidate.service.CandidateService;
import org.inthergroup.ims.feedback.FeedbackWithAuthorNameDTO;
import org.inthergroup.ims.internship.service.InternshipService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.mail.internet.AddressException;
@Slf4j
@RestController


public class EmailController {

    private EmailService emailService;
    private InternshipService internshipService;
    private CandidateService candidateService;

    @Value("${feedback.recipients}")
    private String feedbackRecipients;

    public EmailController(EmailService emailService, InternshipService internshipService, CandidateService candidateService) {
        this.internshipService = internshipService;
        this.emailService = emailService;
        this.candidateService = candidateService;
    }

    @PostMapping("/api/message/{id}")
    String sendEmailMessage(@PathVariable final String id, @RequestBody final FeedbackWithAuthorNameDTO feedbackWithAuthorNameDTO) throws AddressException {
        String subject = candidateService.getById(feedbackWithAuthorNameDTO.getCandidateId()).getName() + " "
                + candidateService.getById(feedbackWithAuthorNameDTO.getCandidateId()).getSurname();
        String textOfMail = "Author: " + feedbackWithAuthorNameDTO.getUserName() +
                " \nFeedback: " +
                feedbackWithAuthorNameDTO.getFeedback() +
                "\nInternshipName: " + internshipService.getById(id).getProjectName() +
                "\nCandidateName: " + subject;
        this.emailService.sendMessage(feedbackRecipients,
                "Internship interview feedback for: " + subject,
                textOfMail
        );
        log.info("Email {} has been sent!", feedbackWithAuthorNameDTO);
        return null;

    }
}
