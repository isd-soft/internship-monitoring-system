package org.inthergroup.ims.techMark;

import org.inthergroup.ims.candidate.repository.CandidateRepository;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.techQuestion.TechQuestion;
import org.inthergroup.ims.techQuestion.TechQuestionRepository;
import org.inthergroup.ims.techQuestionList.TechQuestionList;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public List<TechMarkDTO> getByCandidateId(final String id) {

        Candidate candidate = candidateRepository.findById(id).orElseThrow();
        Internship internship = candidate.getInternship();
        TechQuestionList techQuestionList = internship.getTechQuestionList();
        List<TechQuestion> techQuestions = techQuestionRepository.getTechQuestionsByTechQuestionListId(techQuestionList.getId());
        List<TechMark> techMarks = new ArrayList<>();
        techQuestions.forEach(techQuestion -> {
            Optional<TechMark> techMarkOptional = techMarkRepository.getByTechQuestionIdAndCandidateId(techQuestion.getId(), candidate.getId());
            if(techMarkOptional.isPresent()){
                techMarks.add(techMarkOptional.get());
            } else {
                techMarks.add(createNewTechMark(candidate, techQuestion));
            }
        });
        return techMarks
                .stream()
                .map(techMark -> mapToDTO(techMark, new TechMarkDTO()))
                .collect(Collectors.toList());
    }

    private TechMark createNewTechMark(Candidate candidate, TechQuestion techQuestion){
        TechMark techMark = new TechMark();
        techMark.setCandidate(candidate);
        techMark.setTechQuestion(techQuestion);
        return techMarkRepository.save(techMark);
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
