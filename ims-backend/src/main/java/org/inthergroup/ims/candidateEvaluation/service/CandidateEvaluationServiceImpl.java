package org.inthergroup.ims.candidateEvaluation.service;

import org.inthergroup.ims.candidate.repository.CandidateRepository;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.candidateEvaluation.model.CandidateEvaluation;
import org.inthergroup.ims.candidateEvaluation.repository.CandidateEvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateEvaluationServiceImpl implements CandidateEvaluationService {

    final private CandidateRepository candidateRepository;
    final private CandidateEvaluationRepository candidateEvaluationRepository;

    public CandidateEvaluationServiceImpl(CandidateEvaluationRepository candidateEvaluationRepository, CandidateRepository candidateRepository) {
        this.candidateEvaluationRepository = candidateEvaluationRepository;
        this.candidateRepository = candidateRepository;
    }

    @Override
    public CandidateEvaluation addMarksToCandidateById(CandidateEvaluation candidateEvaluation, String id) {
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        candidateEvaluation.setCandidate(candidate);
        return candidateEvaluationRepository.save(candidateEvaluation);
    }

    @Override
    public void deleteMarksFromCandidateByID(String id) {
       candidateEvaluationRepository.deleteById(id);
    }

    @Override
    public List<CandidateEvaluation> get() {
        return null;
    }

    @Override
    public CandidateEvaluation getCandidateEvaluatiom(CandidateEvaluation candidateEvaluation,String id) {
       Candidate candidate = candidateRepository.findById(id).orElse(null);
       candidateEvaluation.setCandidate(candidate);
       return candidateEvaluationRepository.save(candidateEvaluation);
    }
}