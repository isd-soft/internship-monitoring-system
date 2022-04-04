package org.inthergroup.ims.preInterwiewTestEvaluation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preInterviewTestEvaluation")
public class PreInterviewTestMarkController {

   private final PreInterviewTestMarkFacade preInterviewTestEvaluationFacade;

    public PreInterviewTestMarkController(PreInterviewTestMarkFacade preInterviewTestEvaluationFacade) {
        this.preInterviewTestEvaluationFacade = preInterviewTestEvaluationFacade;
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<PreInterviewTestMarkDTO>> getPreInterviewTestEvaluationByCandidateId(@PathVariable("id") final String candidateId) {
        return ResponseEntity.ok(preInterviewTestEvaluationFacade.getPreInterviewTestMarksByCandidateId(candidateId));
    }

}
