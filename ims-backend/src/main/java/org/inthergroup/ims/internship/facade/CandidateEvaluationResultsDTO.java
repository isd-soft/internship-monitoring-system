package org.inthergroup.ims.internship.facade;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.preInterwiewTestEvaluation.PreInterviewTestMarkDTO;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
public class CandidateEvaluationResultsDTO {
    private String candidateId;
    private Double englishMark;
    private Double softSkillMark;
    private Double practiceMark;
    private Double techMark;
    private Double averageInterviewMark;
    private Double averagePreInterviewMark;
    private List<PreInterviewTestMarkDTO> testMarks;
}
