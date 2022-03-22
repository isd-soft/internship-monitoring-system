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
        return (List<Candidate>) candidateService.getAllCandidates();
    }

    @PostMapping
    public Candidate save(@RequestBody Candidate candidate) {
        candidateService.save(candidate);
        System.out.println("candidate was saved");
        return candidate;
    }

    @DeleteMapping("/{id}")
    public String  deleteCandidate(@PathVariable("id") String id) {
        candidateService.delete(id);
        return null;
    }

}



