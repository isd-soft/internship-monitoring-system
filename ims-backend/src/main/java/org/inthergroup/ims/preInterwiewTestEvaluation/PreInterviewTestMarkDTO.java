package org.inthergroup.ims.preInterwiewTestEvaluation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PreInterviewTestMarkDTO {
    private String id;
    private PreInterviewTest preInterviewTestName;
    private String candidateId;
    private double mark;

    public PreInterviewTestMarkDTO() {
    }

    public PreInterviewTestMarkDTO(PreInterviewTestMark preInterviewTestEvaluation) {
        this.setId(preInterviewTestEvaluation.getId());
        this.setPreInterviewTestName(preInterviewTestEvaluation.getPreInterviewTestName());
        this.setCandidateId(preInterviewTestEvaluation.getCandidate().getId());
        this.setMark(preInterviewTestEvaluation.getMark());
    }
}
