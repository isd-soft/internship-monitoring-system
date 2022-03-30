package org.inthergroup.ims.feedback;


import org.inthergroup.ims.candidate.controller.CandidateDTO;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.candidate.service.CandidateService;
import org.inthergroup.ims.login.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final CandidateService candidateService;

    public FeedbackController(FeedbackService feedbackService, CandidateService candidateService) {
        this.feedbackService = feedbackService;
        this.candidateService = candidateService;
    }

    @GetMapping("/candidate/{id}")
    public List<FeedbackDTO> getFeedbacksByCandidateId(@PathVariable final String id) {
        return feedbackService.getFeedbacksByCandidateId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDTO> getFeedback(@PathVariable final String id) {
        return ResponseEntity.ok(feedbackService.get(id));
    }

    @PostMapping
    public Feedback save(@RequestBody FeedbackDTO feedbackDTO) {
        Feedback feedBack = new Feedback();
        User user = feedbackService.getUserById(feedbackDTO.getUserId());
        feedBack.setUser(user);
        feedBack.setFeedback(feedbackDTO.getFeedback());
        CandidateDTO candidateDTO = candidateService.get(feedbackDTO.getToCandidate());
        Candidate candidate = new Candidate();
        candidateService.mapToEntity(candidateDTO, candidate);
        feedBack.setCandidate(candidate);
        feedbackService.save(feedBack);
        System.out.println("feedback was saved");
        return feedBack;
    }

    @DeleteMapping("/{id}")
    public String deleteFeedback(@PathVariable("id") String id) {
        feedbackService.delete(id);
        return null;
    }


    @PutMapping()
    public Feedback updateFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        Feedback feedback1 = new Feedback();
        feedback1.setId(feedbackDTO.getId());
        feedback1.setFeedback(feedbackDTO.getFeedback());
        //feedback1.s(feedbackDTO.getToCandidate());
        feedbackService.save(feedback1);
        return feedback1;

    }
}
