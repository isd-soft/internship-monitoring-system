package org.inthergroup.ims.candidate.controller;


import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.candidate.service.CandidateService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PutMapping("/{id}")
        public Candidate updateCandidate(@RequestBody CandidateDTO candidateDTO) {
            Candidate candidate1 = new Candidate();
            candidate1.setId(candidateDTO.getId());
            candidate1.setName(candidateDTO.getName());
            candidate1.setSurname(candidateDTO.getSurname());
            candidate1.setEmail(candidateDTO.getEmail());
            candidate1.setCv(candidateDTO.getCv());
            candidate1.setComment(candidateDTO.getComment());
            candidate1.setStatus(candidateDTO.getStatus());
            candidate1.setMark(candidateDTO.getMark());
            candidateService.save(candidate1);
            return candidate1;
        }

}



