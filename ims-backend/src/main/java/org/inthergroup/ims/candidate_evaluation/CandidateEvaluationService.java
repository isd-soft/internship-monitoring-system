package org.inthergroup.ims.candidate_evaluation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.inthergroup.ims.candidate.repository.CandidateRepository;
import org.inthergroup.ims.candidate.model.Candidate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CandidateEvaluationService {

    private final CandidateEvaluationRepository candidateEvaluationRepository;
    private final CandidateRepository candidateRepository;

    public CandidateEvaluationService(
            final CandidateEvaluationRepository candidateEvaluationRepository,
            final CandidateRepository candidateRepository) {
        this.candidateEvaluationRepository = candidateEvaluationRepository;
        this.candidateRepository = candidateRepository;
    }

    public List<CandidateEvaluationDTO> findAll() {
        return candidateEvaluationRepository.findAll()
                .stream()
                .map(candidateEvaluation -> mapToDTO(candidateEvaluation, new CandidateEvaluationDTO()))
                .collect(Collectors.toList());
    }

    public CandidateEvaluationResponseDTO get(final String id) {

        CandidateEvaluationDTO candidateEvaluationDTO = candidateEvaluationRepository.findById(id)
                .map(candidateEvaluation -> mapToDTO(candidateEvaluation, new CandidateEvaluationDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return CandidateEvaluationResponseDTO.builder()
                .id(candidateEvaluationDTO.getId())
                .englishMark(candidateEvaluationDTO.getEnglishMark())
                .softSkillMark(candidateEvaluationDTO.getSoftSkillMark())
                .practiceMark(candidateEvaluationDTO.getPracticeMark())
                .candidate(candidateEvaluationDTO.getCandidate())
                .averageMark(candidateEvaluationRepository.avg(candidateEvaluationDTO.getCandidate()))
                .build();
    }

    public CandidateEvaluationResponseDTO getByCandidateId(final String id) {

        CandidateEvaluationDTO candidateEvaluationDTO;
        Optional<CandidateEvaluation> candidateEvaluationOptional = candidateEvaluationRepository.getCandidateEvaluationByCandidateId(id);
        if (candidateEvaluationOptional.isPresent()) {
                    candidateEvaluationDTO = mapToDTO(candidateEvaluationOptional.get(), new CandidateEvaluationDTO());
        } else {
            CandidateEvaluation candidateEvaluation = new CandidateEvaluation();
            candidateEvaluation.setCandidate(candidateRepository.findById(id).orElseThrow());
            candidateEvaluationRepository.save(candidateEvaluation);
            candidateEvaluationDTO = mapToDTO(candidateEvaluation, new CandidateEvaluationDTO());
        }

        return CandidateEvaluationResponseDTO.builder()
                .id(candidateEvaluationDTO.getId())
                .englishMark(candidateEvaluationDTO.getEnglishMark())
                .softSkillMark(candidateEvaluationDTO.getSoftSkillMark())
                .practiceMark(candidateEvaluationDTO.getPracticeMark())
                .candidate(candidateEvaluationDTO.getCandidate())
                .averageMark(candidateEvaluationRepository.avg(candidateEvaluationDTO.getCandidate()))
                .build();
    }

    public String create(final CandidateEvaluationDTO candidateEvaluationDTO) {
        final CandidateEvaluation candidateEvaluation = new CandidateEvaluation();
        mapToEntity(candidateEvaluationDTO, candidateEvaluation);
        return candidateEvaluationRepository.save(candidateEvaluation).getId();
    }

    public void update(final String id, final CandidateEvaluationDTO candidateEvaluationDTO) {
        final CandidateEvaluation candidateEvaluation = candidateEvaluationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(candidateEvaluationDTO, candidateEvaluation);
        candidateEvaluationRepository.save(candidateEvaluation);
    }

    public void delete(final String id) {
        candidateEvaluationRepository.deleteById(id);
    }

    private CandidateEvaluationDTO mapToDTO(final CandidateEvaluation candidateEvaluation,
                                            final CandidateEvaluationDTO candidateEvaluationDTO) {
        candidateEvaluationDTO.setId(candidateEvaluation.getId());
        candidateEvaluationDTO.setEnglishMark(candidateEvaluation.getEnglishMark());
        candidateEvaluationDTO.setSoftSkillMark(candidateEvaluation.getSoftSkillMark());
        candidateEvaluationDTO.setPracticeMark(candidateEvaluation.getPracticeMark());
        candidateEvaluationDTO.setCandidate(candidateEvaluation.getCandidate() == null ? null :
                candidateEvaluation.getCandidate().getId());
        return candidateEvaluationDTO;
    }

    private CandidateEvaluation mapToEntity(final CandidateEvaluationDTO candidateEvaluationDTO,
                                            final CandidateEvaluation candidateEvaluation) {
        candidateEvaluation.setEnglishMark(candidateEvaluationDTO.getEnglishMark());
        candidateEvaluation.setSoftSkillMark(candidateEvaluationDTO.getSoftSkillMark());
        candidateEvaluation.setPracticeMark(candidateEvaluationDTO.getPracticeMark());
        if (candidateEvaluationDTO.getCandidate() != null && (candidateEvaluation.getCandidate() == null
                || !candidateEvaluation.getCandidate().getId().equals(candidateEvaluationDTO.getCandidate()))) {
            final Candidate candidate = candidateRepository.findById(candidateEvaluationDTO.getCandidate())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "candidate not found"));
            candidateEvaluation.setCandidate(candidate);
        }
        return candidateEvaluation;
    }
}
