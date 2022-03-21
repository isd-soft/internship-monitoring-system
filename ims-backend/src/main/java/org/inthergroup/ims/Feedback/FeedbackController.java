package org.inthergroup.ims.Feedback;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public  FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<Feedback> getRegisterAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @PostMapping
    public Feedback save(@RequestBody Feedback feedback) {
        feedbackService.save(feedback);
        System.out.println("feedback was saved");
        return feedback;
    }

    @DeleteMapping("/{id}")
    public String  deleteFeedback(@PathVariable("id") String id) {
        feedbackService.delete(id);
        return null;
    }

    @PutMapping()
    public Feedback updateFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        Feedback feedback1 = new Feedback();
        feedback1.setId(feedbackDTO.getId());
        feedback1.setFeedback(feedbackDTO.getFeedback());
        feedbackService.save(feedback1);
        return feedback1;

    }
}
