package org.inthergroup.ims.candidateEvaluation.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidateEvaluation.model.TechQuestionList;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class TechQuestionDTO {

    private String id;

    private String name;

    private TechQuestionList techQuestionList;
}
