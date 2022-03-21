package org.inthergroup.ims.Feedback;


import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface FeedbackService {
    List<Feedback> getAllFeedbacks();

    void save(Feedback feedback);

    void delete(String id);


    Feedback getFeedback(Long id);
}
