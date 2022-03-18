package org.inthergroup.ims.internship.controller;

import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.service.InternshipService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internships")
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
    public String getInternshipForm() {
        return "internship form";
    }


    @PostMapping
    void addInternship(@RequestBody InternshipDTO internship) {
        internshipService.save(internship);
    }

}
