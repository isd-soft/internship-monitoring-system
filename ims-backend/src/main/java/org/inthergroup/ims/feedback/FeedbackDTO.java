package org.inthergroup.ims.feedback;


import lombok.Data;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.login.model.User;

@Data
public class FeedbackDTO {
    private String id;
    private String feedback;
    private String toCandidate;
    private String userId;
}
