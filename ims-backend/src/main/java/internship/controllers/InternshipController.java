package internship.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InternshipController {
    @GetMapping("/internship")
    public String internship() {
        return "internship";
    }
}
