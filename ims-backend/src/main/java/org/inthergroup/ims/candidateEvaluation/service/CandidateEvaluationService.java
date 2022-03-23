package org.inthergroup.ims.candidateEvaluation.service;

import org.inthergroup.ims.candidateEvaluation.dto.CandidateEvaluationDTO;
import org.inthergroup.ims.candidateEvaluation.model.CandidateEvaluation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidateEvaluationService {

    public void addMarksToCandidate(CandidateEvaluationDTO candidateEvaluation);
    CandidateEvaluation toCandidateEvaluation(CandidateEvaluationDTO candidateEvaluationDTO);

    public void deleteMarksFromCandidateByID(String id);

    List<CandidateEvaluation> getAllCandidateEvaluations();

    CandidateEvaluation getCandidateEvaluatiom(CandidateEvaluation candidateEvaluation, String id);


}