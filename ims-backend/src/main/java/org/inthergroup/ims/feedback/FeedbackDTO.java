package org.inthergroup.ims.feedback;

import lombok.Data;

@Data
public class FeedbackDTO {

    private String id;
    private String feedback;
    private String candidateId;
    private String userId;
}
