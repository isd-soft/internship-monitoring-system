package org.inthergroup.ims.candidate.service;

import java.util.List;

import org.inthergroup.ims.candidate.repository.CandidateRepository;
import org.inthergroup.ims.candidate.model.Candidate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CandidateServiceImp implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImp(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    @Override
    public void delete(final String id) {
        candidateRepository.deleteById(id);
    }

    public Candidate getById(final String candidateId) {
        return candidateRepository.findById(candidateId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Could not find any Candidate for the provided ID[%s]!",
                        candidateId)));
    }

    public void update(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    public void create(final Candidate candidate) {
        candidateRepository.save(candidate);
        // TODO: Create candidate evaluation and tech marksfg
    }

    @Override
    public List<Candidate> getAllCandidatesByInternshipId(String internshipId) {
        return candidateRepository.findAllByInternshipId(internshipId);
    }
}
