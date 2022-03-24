package org.inthergroup.ims.techQuestionList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TechQuestionListService {

    private final TechQuestionListRepository techQuestionListRepository;

    public TechQuestionListService(final TechQuestionListRepository techQuestionListRepository) {
        this.techQuestionListRepository = techQuestionListRepository;
    }

    public List<TechQuestionListDTO> findAll() {
        return techQuestionListRepository.findAll()
                .stream()
                .map(techQuestionList -> mapToDTO(techQuestionList, new TechQuestionListDTO()))
                .collect(Collectors.toList());
    }

    public TechQuestionListDTO get(final String id) {
        return techQuestionListRepository.findById(id)
                .map(techQuestionList -> mapToDTO(techQuestionList, new TechQuestionListDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(final TechQuestionListDTO techQuestionListDTO) {
        final TechQuestionList techQuestionList = new TechQuestionList();
        mapToEntity(techQuestionListDTO, techQuestionList);
        return techQuestionListRepository.save(techQuestionList).getId();
    }

    public void update(final String id, final TechQuestionListDTO techQuestionListDTO) {
        final TechQuestionList techQuestionList = techQuestionListRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(techQuestionListDTO, techQuestionList);
        techQuestionListRepository.save(techQuestionList);
    }

    public void delete(final String id) {
        techQuestionListRepository.deleteById(id);
    }

    private TechQuestionListDTO mapToDTO(final TechQuestionList techQuestionList,
            final TechQuestionListDTO techQuestionListDTO) {
        techQuestionListDTO.setId(techQuestionList.getId());
        techQuestionListDTO.setName(techQuestionList.getName());
        return techQuestionListDTO;
    }

    private TechQuestionList mapToEntity(final TechQuestionListDTO techQuestionListDTO,
            final TechQuestionList techQuestionList) {
        techQuestionList.setName(techQuestionListDTO.getName());
        return techQuestionList;
    }

}
