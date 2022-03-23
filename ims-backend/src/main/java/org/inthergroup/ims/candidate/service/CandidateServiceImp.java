package org.inthergroup.ims.candidate.service;

import org.inthergroup.ims.candidate.repository.CandidateRepository;
import org.inthergroup.ims.candidate.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImp implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImp(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public void save(Candidate candidate) {
        candidateRepository.save(candidate);
    }


    @Override
    public void delete(String id) {
        candidateRepository.deleteById(id);
    }


    @Override
    public List<Candidate> getAllCandidates() {
        return (List<Candidate>) candidateRepository.findAll();
    }

    @Override
    public Candidate getCandidate(String id) {
        return null;
    }
}
