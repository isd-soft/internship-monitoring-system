package org.inthergroup.ims.feedback;
import lombok.Data;

@Data
public class FeedbackWithAuthorNameDTO {

    private String id;
    private String feedback;
    private String candidateId;
    private String userId;
    private String userName;
}
