package org.inthergroup.ims.candidate.Service;

import org.inthergroup.ims.candidate.Repository.CandidateRepository;
import org.inthergroup.ims.candidate.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Candidate> get() {
        return null;
    }

    @Override
    public Candidate getCandidate(Long id) {
        return null;
    }
}
