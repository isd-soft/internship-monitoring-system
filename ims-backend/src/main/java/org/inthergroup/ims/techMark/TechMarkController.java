package org.inthergroup.ims.techMark;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/techMarks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TechMarkController {

    private final TechMarkService techMarkService;

    public TechMarkController(final TechMarkService techMarkService) {
        this.techMarkService = techMarkService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<TechMarkDTO>> getAllTechMarks() {
        return ResponseEntity.ok(techMarkService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechMarkDTO> getTechMark(@PathVariable final String id) {
        return ResponseEntity.ok(techMarkService.get(id));
    }
    @GetMapping("/candidate/{id}")
    public ResponseEntity<List<TechMarkDTO>> getTechMarkByCandidateId(@PathVariable final String id) {
        return ResponseEntity.ok(techMarkService.getByCandidateId(id));
    }

    @PostMapping("add")
    public ResponseEntity<String> createTechMark(@RequestBody @Valid final TechMarkDTO techMarkDTO) {
        return new ResponseEntity<>(techMarkService.create(techMarkDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTechMark(@PathVariable final String id,
            @RequestBody @Valid final TechMarkDTO techMarkDTO) {
        techMarkService.update(id, techMarkDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechMark(@PathVariable final String id) {
        techMarkService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
