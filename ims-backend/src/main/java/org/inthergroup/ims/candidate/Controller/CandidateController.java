package org.inthergroup.ims.candidate.Controller;


import org.inthergroup.ims.candidate.Service.CandidateService;
import org.inthergroup.ims.candidate.model.Candidate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Candidate save(@RequestBody CandidateDTO candidate) {
        candidateService.save(candidate);
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @GetMapping("/candidate{id}")
    public CandidateDTO getCandidate(@PathVariable String id) {
        Candidate candidate = candidateService.getCandidate(getCandidate(id));
        if (candidate == null) {
            throw new RuntimeException("Employee not found for the Id:" + id);
        }
        return getCandidate(id);
    }

    @PostMapping("/add-candidate")
    public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidate) {
        return ResponseEntity.ok().body(this.candidateService.addCandidate(candidate));
    }

}
