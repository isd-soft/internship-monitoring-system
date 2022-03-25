package org.inthergroup.ims.candidate.service;

import org.inthergroup.ims.candidate.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface CandidateService {

    Iterable<Candidate> getAllCandidates();

    void save(Candidate candidate);

    void delete(String id);

    List<Candidate> getAllCandidatesByInternshipId(String internshipId);

    Candidate getCandidate(String id);
}

