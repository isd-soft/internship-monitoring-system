package org.inthergroup.ims.candidate.service;

import org.inthergroup.ims.candidate.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidateService {

    List<Candidate> getAllCandidates();

    void save(Candidate candidate);

    void delete(String id);


    Candidate getCandidate(String id);
}

