package org.inthergroup.ims.candidate_evaluation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
public class CandidateEvaluationResponseDTO {

    private String id;

    private Double englishMark;

    private Double softSkillMark;

    private Double practiceMark;

    private Double averageMark;

    private Double averageCandidateEvaluation;

    @NotNull
    private Double averagePreInterviewTestMark;

    @NotNull
    private String candidate;
}
