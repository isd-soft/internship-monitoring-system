package org.inthergroup.ims.feedback;

import org.inthergroup.ims.login.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedbackService {
    List<Feedback> getAllFeedbacks();

    public FeedbackDTO get(final String id);

    void save(Feedback feedback);

    void delete(String id);

    void updateFeedback(String id);

    public User getUserById(String id);

    public List<FeedbackWithAuthorNameDTO> getFeedbacksByCandidateId(String id);

   // Feedback getFeedback(String id);
}
