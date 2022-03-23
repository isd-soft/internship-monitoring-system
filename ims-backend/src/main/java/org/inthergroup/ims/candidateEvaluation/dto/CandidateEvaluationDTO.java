package org.inthergroup.ims.candidateEvaluation.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidate.model.Candidate;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class CandidateEvaluationDTO {

    private String id;

    private Double english;

    private Double softskills;

    private Double practice;

    private String candidate;

    public CandidateEvaluationDTO() {
    }
}
