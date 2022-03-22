package org.inthergroup.ims.candidate.Service;

import org.inthergroup.ims.candidate.model.Candidate;
import org.springframework.stereotype.Service;

@Service
public interface CandidateService {

    Iterable<Candidate> getAllCandidates();

    void save(Candidate candidate);

    void delete(String id);


    Candidate getCandidate(String id);
}

