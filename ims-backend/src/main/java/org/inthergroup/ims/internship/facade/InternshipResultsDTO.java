package org.inthergroup.ims.internship.facade;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
public class InternshipResultsDTO {
    private List<CandidateEvaluationResultsDTO> newCandidates;
    private List<CandidateEvaluationResultsDTO> acceptedCandidates;
    private List<CandidateEvaluationResultsDTO> onHoldCandidates;
    private List<CandidateEvaluationResultsDTO> rejectedCandidates;
}
