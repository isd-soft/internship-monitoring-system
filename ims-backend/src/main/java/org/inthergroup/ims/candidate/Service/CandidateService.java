package org.inthergroup.ims.candidate.Service;

import org.inthergroup.ims.candidate.Controller.CandidateDTO;
import org.inthergroup.ims.candidate.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidateService {

    List<Candidate> get();

    void save(CandidateDTO candidate);

    Candidate getCandidate(CandidateDTO candidateDTO);

    Candidate addCandidate(Candidate candidate);
}

