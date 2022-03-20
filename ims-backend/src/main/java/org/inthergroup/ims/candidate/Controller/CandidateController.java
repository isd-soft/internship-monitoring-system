package org.inthergroup.ims.candidate.Controller;


import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.candidate.Service.CandidateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
    private final CandidateService candidateService;

    CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public List<Candidate> getRegisterAllCandidate() {
        return candidateService.getAllCandidates();
    }

    @PostMapping
    public Candidate save(@RequestBody Candidate candidate) {
        candidateService.save(candidate);
        System.out.println("candidate was saved");
        return candidate;
    }

    @DeleteMapping
    public Candidate deleteCandidate(@RequestBody Candidate candidate) {
        candidateService.delete(candidate);
        return null;
    }

}



