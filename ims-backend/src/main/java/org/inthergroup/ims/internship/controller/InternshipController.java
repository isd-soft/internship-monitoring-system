package org.inthergroup.ims.internship.controller;

import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.service.InternshipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internship")
public class InternshipController {

    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @GetMapping
    public List<Internship> getAllInternships() {
        return internshipService.getAllInternships();
    }

    @PostMapping
    void addInternship(@RequestBody InternshipDTO internship) {
        internshipService.save(internship);
    }

}
