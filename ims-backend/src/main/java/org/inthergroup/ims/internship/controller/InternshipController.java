package org.inthergroup.ims.internship.controller;

import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.service.InternshipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
public class InternshipController {

    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @GetMapping()
    public List<Internship> getAllInternship() {
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
    void addInternship(@RequestBody InternshipDTO internship) {
        internshipService.createInternship(internship);
    }

}
