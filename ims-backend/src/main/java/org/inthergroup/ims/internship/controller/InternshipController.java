package org.inthergroup.ims.internship.controller;

import lombok.extern.slf4j.Slf4j;
import org.inthergroup.ims.candidate.facade.CandidateDTO;
import org.inthergroup.ims.candidate.facade.CandidateFacade;
import org.inthergroup.ims.internship.facade.InternshipDTO;
import org.inthergroup.ims.internship.facade.InternshipFacade;
import org.inthergroup.ims.internship.facade.InternshipResultsDTO;
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

@Slf4j
@RestController
@RequestMapping(value = "/api/internships", produces = MediaType.APPLICATION_JSON_VALUE)
public class InternshipController {

    private final InternshipService internshipService;
    private final InternshipFacade internshipFacade;
    private final CandidateFacade candidateFacade;

    public InternshipController(InternshipService internshipService, InternshipFacade internshipFacade, CandidateFacade candidateFacade) {
        this.internshipService = internshipService;
        this.internshipFacade = internshipFacade;
        this.candidateFacade = candidateFacade;
    }

    @GetMapping()
    public List<InternshipDTO> getAllInternship() {
        return internshipFacade.getAllInternship();
    }

    @GetMapping("/{id}")
    public InternshipDTO getInternship(@PathVariable final String id) {
        return internshipFacade.getById(id);
    }

    @GetMapping("/{id}/candidates")
    public List<CandidateDTO> getCandidatesByInternshipId(@PathVariable("id") String internshipId) {
        return internshipFacade.getCandidatesByInternshipId(internshipId);
    }

    @GetMapping("/{id}/results")
    public InternshipResultsDTO getInternshipResultsById(@PathVariable("id") String internshipId) {
        return internshipFacade.getInternshipResults(internshipId);
    }

    @PostMapping
    public ResponseEntity<Void> createInternship(@RequestBody @Valid final InternshipDTO internship) {
        internshipFacade.create(internship);
        log.info("Internship {} has been created!", internship);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateInternship(@PathVariable("id") final String id,
                                                 @RequestBody @Valid final InternshipDTO internshipDTO) {
        internshipFacade.update(id, internshipDTO);
        log.info("Internship {} has been updated!", internshipDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInternship(@PathVariable("id") final String id) {
        internshipFacade.delete(id);
        log.info("Internship {} has been deleted!", id);
        return ResponseEntity.noContent().build();
    }
}
