package org.inthergroup.ims.internship.controller;

import org.inthergroup.ims.candidate.controller.CandidateDTO;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.service.InternshipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/internships", produces = MediaType.APPLICATION_JSON_VALUE)
public class InternshipController {

    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
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
    public List<Candidate> getCandidatesByInternshipId(String internshipId) {
        return internshipService.getAllCandidatesByInternshipId(internshipId);
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
