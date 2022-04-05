package org.inthergroup.ims.preInterwiewTestEvaluation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/preInterviewTestMaks")
public class PreInterviewTestMarkController {

    private final PreInterviewTestMarkFacade preInterviewTestMarkFacade;

    public PreInterviewTestMarkController(PreInterviewTestMarkFacade preInterviewTestEvaluationFacade) {
        this.preInterviewTestMarkFacade = preInterviewTestEvaluationFacade;
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<PreInterviewTestMarkDTO>> getPreInterviewTestEvaluationByCandidateId(
            @PathVariable("id") final String candidateId) {
        return ResponseEntity.ok(preInterviewTestMarkFacade.getPreInterviewTestMarksByCandidateId(candidateId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMark(@PathVariable("id") final String markId,
                                                @RequestBody @Valid final PreInterviewTestMarkDTO preInterviewTestMarkDTO) {
        preInterviewTestMarkFacade.update(preInterviewTestMarkDTO);
        return ResponseEntity.ok().build();
    }
}
