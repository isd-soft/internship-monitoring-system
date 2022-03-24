package org.inthergroup.ims.techMark;

import org.inthergroup.ims.candidate.Repository.CandidateRepository;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.techQuestion.TechQuestion;
import org.inthergroup.ims.techQuestion.TechQuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TechMarkService {

    private final TechMarkRepository techMarkRepository;
    private final TechQuestionRepository techQuestionRepository;
    private final CandidateRepository candidateRepository;

    public TechMarkService(final TechMarkRepository techMarkRepository,
            final TechQuestionRepository techQuestionRepository,
            final CandidateRepository candidateRepository) {
        this.techMarkRepository = techMarkRepository;
        this.techQuestionRepository = techQuestionRepository;
        this.candidateRepository = candidateRepository;
    }

    public List<TechMarkDTO> findAll() {
        return techMarkRepository.findAll()
                .stream()
                .map(techMark -> mapToDTO(techMark, new TechMarkDTO()))
                .collect(Collectors.toList());
    }

    public TechMarkDTO get(final String id) {
        return techMarkRepository.findById(id)
                .map(techMark -> mapToDTO(techMark, new TechMarkDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(final TechMarkDTO techMarkDTO) {
        final TechMark techMark = new TechMark();
        mapToEntity(techMarkDTO, techMark);
        return techMarkRepository.save(techMark).getId();
    }

    public void update(final String id, final TechMarkDTO techMarkDTO) {
        final TechMark techMark = techMarkRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(techMarkDTO, techMark);
        techMarkRepository.save(techMark);
    }

    public void delete(final String id) {
        techMarkRepository.deleteById(id);
    }

    private TechMarkDTO mapToDTO(final TechMark techMark, final TechMarkDTO techMarkDTO) {
        techMarkDTO.setId(techMark.getId());
        techMarkDTO.setMark(techMark.getMark());
        techMarkDTO.setTechQuestion(techMark.getTechQuestion() == null ? null : techMark.getTechQuestion().getId());
        techMarkDTO.setCandidate(techMark.getCandidate() == null ? null : techMark.getCandidate().getId());
        return techMarkDTO;
    }

    private TechMark mapToEntity(final TechMarkDTO techMarkDTO, final TechMark techMark) {
        techMark.setMark(techMarkDTO.getMark());
        if (techMarkDTO.getTechQuestion() != null && (techMark.getTechQuestion() == null || !techMark.getTechQuestion().getId().equals(techMarkDTO.getTechQuestion()))) {
            final TechQuestion techQuestion = techQuestionRepository.findById(techMarkDTO.getTechQuestion())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "techQuestion not found"));
            techMark.setTechQuestion(techQuestion);
        }
        if (techMarkDTO.getCandidate() != null && (techMark.getCandidate() == null || !techMark.getCandidate().getId().equals(techMarkDTO.getCandidate()))) {
            final Candidate candidate = candidateRepository.findById(techMarkDTO.getCandidate())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "candidate not found"));
            techMark.setCandidate(candidate);
        }
        return techMark;
    }

}
