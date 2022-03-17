package org.inthergroup.ims.candidate.Service;

import org.inthergroup.ims.candidate.Controller.CandidateDTO;
import org.inthergroup.ims.candidate.Repository.CandidateRepository;
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
    public void save(CandidateDTO candidate) {
        candidateRepository.save(getCandidate(candidate));
    }


    @Override
    public List<Candidate> get() {
        return this.candidateRepository.findAll();
    }

    @Override
    public Candidate getCandidate(CandidateDTO candidateDTO) {
        return new Candidate(candidateDTO.getId(),
                candidateDTO.getName(),
                candidateDTO.getSurname(),
                candidateDTO.getEmail(),
                candidateDTO.getCv(),
                candidateDTO.getComment(),
                candidateDTO.getStatus(),
                candidateDTO.getMark());
    }

    @Override
    public Candidate addCandidate(Candidate candidate) {
        return null;
    }
}
