package org.inthergroup.ims.feedback;

import lombok.extern.slf4j.Slf4j;
import org.inthergroup.ims.candidate.facade.CandidateFacade;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.candidate.service.CandidateService;
import org.inthergroup.ims.login.model.User;
import org.inthergroup.ims.login.security.service.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final CandidateService candidateService;
    private final CandidateFacade candidateFacade;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public FeedbackController(FeedbackService feedbackService,
                              CandidateService candidateService,
                              CandidateFacade candidateFacade,
                              UserDetailsServiceImpl userDetailsServiceImpl) {
        this.feedbackService = feedbackService;
        this.candidateService = candidateService;
        this.candidateFacade = candidateFacade;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @GetMapping("/candidate/{id}")
    public List<FeedbackWithAuthorNameDTO> getFeedbacksByCandidateId(@PathVariable("id") final String id) {
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
        Candidate candidate = candidateService.getById(feedbackDTO.getCandidateId());
        feedBack.setCandidate(candidate);
        feedbackService.save(feedBack);
        log.info("Feedback for {} has been created!", candidate);
        return feedBack;
    }

    @DeleteMapping("/{id}")
    public String deleteFeedback(@PathVariable("id") String id) {
        feedbackService.delete(id);
        return null;
    }

    @PutMapping()
    public Feedback updateFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setId(feedbackDTO.getId());
        feedback.setFeedback(feedbackDTO.getFeedback());
        feedback.setUser(this.userDetailsServiceImpl.findById(feedbackDTO.getUserId()).get());
        Candidate candidate = this.candidateService.getById(feedbackDTO.getCandidateId());
        feedback.setCandidate(candidate);
        feedbackService.save(feedback);
        return feedback;
    }
}
