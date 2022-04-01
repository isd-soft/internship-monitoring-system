package org.inthergroup.ims.feedback;

import org.inthergroup.ims.login.model.User;
import org.inthergroup.ims.login.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImp implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    public FeedbackServiceImp(FeedbackRepository feedbackRepository, UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    @Override
    public void delete(String id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public void updateFeedback(String id) {}

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public FeedbackDTO get(String id) {
        return feedbackRepository.findById(id)
                .map(candidate -> mapToDTO(candidate, new FeedbackDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private FeedbackDTO mapToDTO(final Feedback feedback, final FeedbackDTO feedbackDTO) {
        feedbackDTO.setId(feedback.getId());
        feedbackDTO.setFeedback(feedback.getFeedback());
        feedbackDTO.setCandidateId(feedback.getCandidate().getId());
        feedbackDTO.setUserId(feedback.getUser().getId());
        return feedbackDTO;
    }

    public List<FeedbackDTO> getFeedbacksByCandidateId(String id) {
        return feedbackRepository.getFeedbacksByCandidateId(id).stream().map(feedback -> {
            FeedbackDTO feedbackDTO = new FeedbackDTO();
            return mapToDTO(feedback, feedbackDTO);
        }).collect(Collectors.toList());
    }
}
