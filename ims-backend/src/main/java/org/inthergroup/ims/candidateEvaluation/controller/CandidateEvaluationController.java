package org.inthergroup.ims.candidateEvaluation.controller;

import org.inthergroup.ims.candidateEvaluation.dto.CandidateEvaluationDTO;
import org.inthergroup.ims.candidateEvaluation.model.CandidateEvaluation;
import org.inthergroup.ims.candidateEvaluation.service.CandidateEvaluationService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/ce")
public class CandidateEvaluationController {

    final private CandidateEvaluationService candidateEvaluationService;

    public CandidateEvaluationController(CandidateEvaluationService candidateEvaluationService) {
        this.candidateEvaluationService = candidateEvaluationService;
    }

    @PostMapping(value="/addmarks")
    public void addMarksToCandidate(@RequestBody CandidateEvaluationDTO candidateEvaluationDTO) {
        candidateEvaluationService.addMarksToCandidate(candidateEvaluationDTO);
    }
    @GetMapping(value = "/getallmarks")
    public List<CandidateEvaluation> getAllCandidateEvaluations() {
        return candidateEvaluationService.getAllCandidateEvaluations();
    }

    @GetMapping(value = "/getcandidatemarks/{id}")
    public CandidateEvaluation getCandidateEvaluatiom(CandidateEvaluation candidateEvaluation, @PathVariable String id) {
        return candidateEvaluationService.getCandidateEvaluatiom(candidateEvaluation,id);
    }

    @DeleteMapping(value="/delete/{id}")
    public void deleteMarksFromCandidateByID(@PathVariable String id){
        candidateEvaluationService.deleteMarksFromCandidateByID(id);
    }


}