package org.inthergroup.ims.candidateEvaluation.service;

import org.inthergroup.ims.candidate.Repository.CandidateRepository;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.candidateEvaluation.dto.CandidateEvaluationDTO;
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
    public void addMarksToCandidate(CandidateEvaluationDTO candidateEvaluationDTO) {
       candidateEvaluationRepository.save(toCandidateEvaluation(candidateEvaluationDTO));
    }

    @Override
    public CandidateEvaluation toCandidateEvaluation(CandidateEvaluationDTO candidateEvaluationDTO) {
        CandidateEvaluation candidateEvaluation = new CandidateEvaluation();
        candidateEvaluation.setEnglish(candidateEvaluationDTO.getEnglish());
        candidateEvaluation.setPractice(candidateEvaluationDTO.getPractice());
        candidateEvaluation.setSoftskills(candidateEvaluationDTO.getSoftskills());
        candidateEvaluation.setCandidate(candidateEvaluationDTO.getCandidate());
        return candidateEvaluation;
    }

    @Override
    public void deleteMarksFromCandidateByID(String id) {
       candidateEvaluationRepository.deleteById(id);
    }

    @Override
    public List<CandidateEvaluation> getAllCandidateEvaluations() {

        return candidateEvaluationRepository.findAll();
    }

    @Override
    public CandidateEvaluation getCandidateEvaluatiom(CandidateEvaluation candidateEvaluation, String id) {
       Candidate candidate = candidateRepository.findById(id).orElse(null);
       candidateEvaluation.setCandidate(String.valueOf(candidate));
       candidateEvaluationRepository.save(candidateEvaluation);
        return candidateEvaluation;
    }
}