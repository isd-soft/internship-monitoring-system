package org.inthergroup.ims.candidate_evaluation;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/candidateEvaluations", produces = MediaType.APPLICATION_JSON_VALUE)
public class CandidateEvaluationController {

    private final CandidateEvaluationService candidateEvaluationService;

    public CandidateEvaluationController(
            final CandidateEvaluationService candidateEvaluationService) {
        this.candidateEvaluationService = candidateEvaluationService;
    }

    @GetMapping("")
    public ResponseEntity<List<CandidateEvaluationDTO>> getAllCandidateEvaluations() {
        return ResponseEntity.ok(candidateEvaluationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateEvaluationResponseDTO> getCandidateEvaluation(
            @PathVariable final String id) {
        return ResponseEntity.ok(candidateEvaluationService.get(id));
    }

    @GetMapping("/candidate/{id}")
    public ResponseEntity<CandidateEvaluationResponseDTO> getCandidateEvaluationByCandidateId(
            @PathVariable final String id) {
        return ResponseEntity.ok(candidateEvaluationService.getByCandidateId(id));
    }

    @PostMapping("")
    public ResponseEntity<String> createCandidateEvaluation(
            @RequestBody @Valid final CandidateEvaluationDTO candidateEvaluationDTO) {
        return new ResponseEntity<>(candidateEvaluationService.create(candidateEvaluationDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCandidateEvaluation(@PathVariable final String id,
     @RequestBody @Valid final CandidateEvaluationDTO candidateEvaluationDTO) {
        candidateEvaluationService.update(id, candidateEvaluationDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidateEvaluation(@PathVariable final String id) {
        candidateEvaluationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}