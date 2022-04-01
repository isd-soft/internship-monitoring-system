package org.inthergroup.ims.internship.controller;

import org.inthergroup.ims.internship.model.PreInterviewTestEvaluation;
import org.inthergroup.ims.internship.service.PreInterviewTestEvaluationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/preInterviewTestEvaluation")
public class PreInterviewTestEvaluationController {
    private final PreInterviewTestEvaluationService preInterviewTestEvaluationService;

    public PreInterviewTestEvaluationController(PreInterviewTestEvaluationService preInterviewTestEvaluationService) {
        this.preInterviewTestEvaluationService = preInterviewTestEvaluationService;
    }

    @GetMapping()
    public List<PreInterviewTestEvaluation> getAllPreInterviewTestEvaluation() {
        return preInterviewTestEvaluationService.getAllPreInterviewTestEvaluation();
    }
    @PostMapping
    void addPreInterviewTestEvaluation(@RequestBody PreInterviewTestEvaluationDTO preInterviewTestEvaluationDTO) {
        preInterviewTestEvaluationService.save(preInterviewTestEvaluationDTO);
    }
}
