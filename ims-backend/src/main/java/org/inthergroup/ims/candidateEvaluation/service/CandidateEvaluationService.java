package org.inthergroup.ims.candidateEvaluation.service;

import org.inthergroup.ims.candidateEvaluation.model.CandidateEvaluation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidateEvaluationService {

    public CandidateEvaluation addMarksToCandidateById(CandidateEvaluation candidateEvaluation, String id);

    public void deleteMarksFromCandidateByID(String id);

    List<CandidateEvaluation> get();

    CandidateEvaluation getCandidateEvaluatiom(CandidateEvaluation candidateEvaluation, String id);
}