package org.inthergroup.ims.candidate.Controller;


import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.candidate.Service.CandidateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidates")
public class CandidateController {
    private final CandidateService candidateService;

    CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public String getRegisterAllCandidate() {
        return "candidate is registered";
    }

    @PostMapping
    public Candidate save(@RequestBody Candidate candidate) {
        candidateService.save(candidate);
        return candidate;
    }

}
