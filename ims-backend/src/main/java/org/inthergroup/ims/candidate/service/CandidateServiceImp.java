package org.inthergroup.ims.candidate.service;

import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.candidate.repository.CandidateRepository;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.repository.InternshipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImp implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final InternshipRepository internshipRepository;

    public CandidateServiceImp(CandidateRepository candidateRepository, InternshipRepository internshipRepository) {
        this.candidateRepository = candidateRepository;
        this.internshipRepository = internshipRepository;
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
    public List<Candidate> getAllCandidatesByInternshipId(String internshipId) {

        return candidateRepository.getCandidatesByInternshipId(internshipId);

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
