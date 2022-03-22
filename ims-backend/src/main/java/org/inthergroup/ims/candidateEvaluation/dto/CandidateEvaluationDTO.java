package org.inthergroup.ims.candidateEvaluation.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class CandidateEvaluationDTO {

    private String id;

    private Double english_mark;

    private Double softskills_mark;

    private Double practical_mark;

    private String candidate;
}
