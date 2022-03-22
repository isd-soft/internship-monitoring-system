package org.inthergroup.ims.candidateEvaluation.controller;

import org.inthergroup.ims.candidateEvaluation.model.TechQuestion;
import org.inthergroup.ims.candidateEvaluation.service.TechQuestionService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/techquestion")
public class TechQuestionController {

    private final TechQuestionService techQuestionService;

    public TechQuestionController(TechQuestionService techQuestionService) {
        this.techQuestionService = techQuestionService;
    }

    @PostMapping(value = "/add")
    public TechQuestion addTechQuestion(@RequestBody TechQuestion techQuestion) {
        return techQuestionService.addTechQuestion(techQuestion);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteTechQuestionById(@PathVariable String id) {
        techQuestionService.deleteTechQuestionById(id);

    }
}

