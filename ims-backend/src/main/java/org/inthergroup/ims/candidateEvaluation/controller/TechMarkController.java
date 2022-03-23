package org.inthergroup.ims.candidateEvaluation.controller;


import org.inthergroup.ims.candidateEvaluation.model.TechMark;
import org.inthergroup.ims.candidateEvaluation.service.TechMarkService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tm")
public class TechMarkController {

    private final TechMarkService techMarkService;

    public TechMarkController(TechMarkService techMarkService) {

        this.techMarkService = techMarkService;
    }

    @PostMapping(value = "/add")
    public TechMark addTechMark(@RequestBody TechMark techMark) {

        return techMarkService.addTechMark(techMark);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteTechMarkById(@PathVariable String id) {
        techMarkService.deleteTechMarkById(id);

    }
}
