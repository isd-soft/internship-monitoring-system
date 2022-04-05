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

    @NotNull
    private Double englishMark;

    @NotNull
    private Double softSkillMark;

    @NotNull
    private Double practiceMark;

    @NotNull
    private Double averageMark;

    @NotNull
    private Double averagePreInterviewTestMark;

    @NotNull
    private String candidate;
}
