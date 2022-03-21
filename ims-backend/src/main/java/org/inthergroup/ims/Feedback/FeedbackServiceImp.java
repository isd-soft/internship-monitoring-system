package org.inthergroup.ims.Feedback;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImp implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImp(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public void save(Feedback feedback) {
        feedbackRepository.save(feedback);
    }


    @Override
    public void delete(Feedback feedback) {
        feedbackRepository.deleteById(feedback.getId());
    }


    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedback(Long id) {
        return null;
    }
}
