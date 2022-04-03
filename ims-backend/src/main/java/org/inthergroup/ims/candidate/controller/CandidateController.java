package org.inthergroup.ims.candidate.controller;

import lombok.extern.slf4j.Slf4j;
import org.inthergroup.ims.candidate.facade.CandidateDTO;
import org.inthergroup.ims.candidate.facade.CandidateFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateFacade candidateFacade;

    public CandidateController(final CandidateFacade candidateFacade) {
        this.candidateFacade = candidateFacade;
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getAllCandidates() {
        return ResponseEntity.ok(candidateFacade.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateDTO> getCandidateById(@PathVariable("id") final String candidateId) {
        return ResponseEntity.ok(candidateFacade.getById(candidateId));
    }

    @PostMapping
    public ResponseEntity<Void> createCandidate(@RequestBody @Valid final CandidateDTO candidateDTO) {
        candidateFacade.create(candidateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCandidate(@PathVariable("id") final String candidateId,
                                                @RequestBody @Valid final CandidateDTO candidateDTO) {
        candidateFacade.update(candidateDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable final String id) {
        candidateFacade.delete(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}



