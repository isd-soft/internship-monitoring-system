package org.inthergroup.ims.techQuestionList;

import org.inthergroup.ims.techMark.TechMarkDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/techQuestionLists", produces = MediaType.APPLICATION_JSON_VALUE)
public class TechQuestionListController {

    private final TechQuestionListService techQuestionListService;

    public TechQuestionListController(final TechQuestionListService techQuestionListService) {
        this.techQuestionListService = techQuestionListService;
    }

    @GetMapping("")
    public ResponseEntity<List<TechQuestionListDTO>> getAllTechQuestionLists() {
        return ResponseEntity.ok(techQuestionListService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechQuestionListDTO> getTechQuestionList(@PathVariable final String id) {
        return ResponseEntity.ok(techQuestionListService.get(id));
    }

    @PostMapping("")
    public ResponseEntity<String> createTechQuestionList(
            @RequestBody @Valid final TechQuestionListDTO techQuestionListDTO) {
        return new ResponseEntity<>(techQuestionListService.create(techQuestionListDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTechQuestionList(@PathVariable final String id,
            @RequestBody @Valid final TechQuestionListDTO techQuestionListDTO) {
        techQuestionListService.update(id, techQuestionListDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechQuestionList(@PathVariable final String id) {
        techQuestionListService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
