package org.inthergroup.ims.candidateEvaluation.controller;

import org.inthergroup.ims.candidateEvaluation.model.TechQuestionList;
import org.inthergroup.ims.candidateEvaluation.service.TechQuestionListService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/tql")
public class TechQuestionListController {

    private final TechQuestionListService techQuestionListService;

    public TechQuestionListController(TechQuestionListService techQuestionListService) {
        this.techQuestionListService = techQuestionListService;
    }

    @PostMapping()
    public TechQuestionList addTechQuestionList(@RequestBody TechQuestionList techQuestionList) {
        return techQuestionListService.addQuestionList(techQuestionList);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteQuestionListById(@PathVariable String id) {
        techQuestionListService.deleteQuestionListById(id);
    }
}