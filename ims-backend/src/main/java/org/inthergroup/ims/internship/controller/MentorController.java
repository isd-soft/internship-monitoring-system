package org.inthergroup.ims.internship.controller;

import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.service.MentorService;
import org.inthergroup.ims.login.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class MentorController {

    private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping
    public List<User> getAllMentors() {
        return mentorService.getAllMentors();
    }
}
