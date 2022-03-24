package org.inthergroup.ims.candidate_evaluation;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CandidateEvaluationDTO {

    private String id;

    @NotNull
    private Double englishMark;

    @NotNull
    private Double softSkillMark;

    @NotNull
    private Double practiceMark;

    @NotNull
    private String candidate;

}
