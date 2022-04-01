package org.inthergroup.ims.techQuestion;

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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/techQuestions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TechQuestionController {

    private final TechQuestionService techQuestionService;

    public TechQuestionController(final TechQuestionService techQuestionService) {
        this.techQuestionService = techQuestionService;
    }

    @GetMapping("")
    public ResponseEntity<List<TechQuestionDTO>> getAllTechQuestions() {
        return ResponseEntity.ok(techQuestionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechQuestionDTO> getTechQuestion(@PathVariable final String id) {
        return ResponseEntity.ok(techQuestionService.get(id));
    }

    @PostMapping("")
    public ResponseEntity<String> createTechQuestion(
            @RequestBody @Valid final TechQuestionDTO techQuestionDTO) {
        return new ResponseEntity<>(techQuestionService.create(techQuestionDTO), HttpStatus.CREATED);
    }

    @GetMapping("/techQuestionList/{id}")
    public ResponseEntity<List<TechQuestionDTO>> getTechMarkByCandidateId(@PathVariable final String id) {
        return ResponseEntity.ok(techQuestionService.getByQuestionListId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTechQuestion(@PathVariable final String id,
            @RequestBody @Valid final TechQuestionDTO techQuestionDTO) {
        techQuestionService.update(id, techQuestionDTO);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechQuestion(@PathVariable final String id) {
        techQuestionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
