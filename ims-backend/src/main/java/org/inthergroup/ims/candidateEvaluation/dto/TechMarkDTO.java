package org.inthergroup.ims.candidateEvaluation.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidateEvaluation.model.CandidateEvaluation;
import org.inthergroup.ims.candidateEvaluation.model.TechQuestion;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class TechMarkDTO {

    private String id;

    private CandidateEvaluation candidateEvaluation;

    private TechQuestion techQuestion;

    private Double mark;
}
