package org.inthergroup.ims.internship.controller;

import org.inthergroup.ims.candidate.facade.CandidateDTO;
import org.inthergroup.ims.candidate.facade.CandidateFacade;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.service.InternshipService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/internships", produces = MediaType.APPLICATION_JSON_VALUE)
public class InternshipController {

    private final InternshipService internshipService;
    private final CandidateFacade candidateFacade;

    public InternshipController(InternshipService internshipService, CandidateFacade candidateFacade) {
        this.internshipService = internshipService;
        this.candidateFacade = candidateFacade;
    }

    @GetMapping()
    public List<InternshipDTO> getAllInternship() {
        return internshipService.getAllInternships();
    }

    @GetMapping("/{id}")
    public Internship getInternship(@PathVariable final String id) {
        Internship internship = internshipService.getInternship(id);
        return internship;
    }

    @GetMapping("/{id}/candidates")
    public List<CandidateDTO> getCandidatesByInternshipId(@PathVariable("id") String internshipId) {
        List<Candidate> candidates = internshipService.getAllCandidatesByInternshipId(internshipId);
        return candidates.stream().map(candidate -> {
            return candidateFacade.mapToDTO(candidate, new CandidateDTO());
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Void> createInternship(@RequestBody @Valid final InternshipDTO internship) {
        internshipService.createInternship(internship);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateInternship(@PathVariable("id") final String id,
                                                 @RequestBody @Valid final InternshipDTO internshipDTO) {
        internshipService.update(id, internshipDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInternship(@PathVariable("id") final String id) {
        internshipService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
