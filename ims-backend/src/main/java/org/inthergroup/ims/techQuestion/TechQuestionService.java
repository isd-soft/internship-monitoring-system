package org.inthergroup.ims.techQuestion;

import org.inthergroup.ims.techMark.TechMarkDTO;
import org.inthergroup.ims.techQuestionList.TechQuestionList;
import org.inthergroup.ims.techQuestionList.TechQuestionListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TechQuestionService {

    private final TechQuestionRepository techQuestionRepository;
    private final TechQuestionListRepository techQuestionListRepository;

    public TechQuestionService(final TechQuestionRepository techQuestionRepository,
            final TechQuestionListRepository techQuestionListRepository) {
        this.techQuestionRepository = techQuestionRepository;
        this.techQuestionListRepository = techQuestionListRepository;
    }

    public List<TechQuestionDTO> findAll() {
        return techQuestionRepository.findAll()
                .stream()
                .map(techQuestion -> mapToDTO(techQuestion, new TechQuestionDTO()))
                .collect(Collectors.toList());
    }
    public List<TechQuestionDTO> getByQuestionListId(final String id) {
        return techQuestionRepository.getTechQuestionsByTechQuestionListId(id)
                .stream()
                .map(techQuestion -> mapToDTO(techQuestion, new TechQuestionDTO()))
                .collect(Collectors.toList());
    }

    public TechQuestionDTO get(final String id) {
        return techQuestionRepository.findById(id)
                .map(techQuestion -> mapToDTO(techQuestion, new TechQuestionDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(final TechQuestionDTO techQuestionDTO) {
        final TechQuestion techQuestion = new TechQuestion();
        mapToEntity(techQuestionDTO, techQuestion);
        return techQuestionRepository.save(techQuestion).getId();
    }

    public void update(final String id, final TechQuestionDTO techQuestionDTO) {
        final TechQuestion techQuestion = techQuestionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(techQuestionDTO, techQuestion);
        techQuestionRepository.save(techQuestion);
    }

    public void delete(final String id) {
        techQuestionRepository.deleteById(id);
    }

    private TechQuestionDTO mapToDTO(final TechQuestion techQuestion,
            final TechQuestionDTO techQuestionDTO) {
        techQuestionDTO.setId(techQuestion.getId());
        techQuestionDTO.setName(techQuestion.getName());
        techQuestionDTO.setTechQuestionList(techQuestion.getTechQuestionList() == null ? null : techQuestion.getTechQuestionList().getId());
        return techQuestionDTO;
    }

    private TechQuestion mapToEntity(final TechQuestionDTO techQuestionDTO,
            final TechQuestion techQuestion) {
        techQuestion.setName(techQuestionDTO.getName());
        if (techQuestionDTO.getTechQuestionList() != null && (techQuestion.getTechQuestionList() == null || !techQuestion.getTechQuestionList().getId().equals(techQuestionDTO.getTechQuestionList()))) {
            final TechQuestionList techQuestionList = techQuestionListRepository.findById(techQuestionDTO.getTechQuestionList())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "techQuestionList not found"));
            techQuestion.setTechQuestionList(techQuestionList);
        }
        return techQuestion;
    }

}
