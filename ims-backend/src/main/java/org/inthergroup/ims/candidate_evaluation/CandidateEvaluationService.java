package org.inthergroup.ims.candidate_evaluation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.inthergroup.ims.candidate.repository.CandidateRepository;
import org.inthergroup.ims.candidate.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CandidateEvaluationService {

    private final Environment env;
    private final CandidateEvaluationRepository candidateEvaluationRepository;
    private final CandidateRepository candidateRepository;

    public CandidateEvaluationService(
            final CandidateEvaluationRepository candidateEvaluationRepository,
            final CandidateRepository candidateRepository, Environment env) {
        this.candidateEvaluationRepository = candidateEvaluationRepository;
        this.candidateRepository = candidateRepository;
        this.env = env;
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
                .averagePreInterviewTestMark(candidateEvaluationRepository.testAvg(candidateEvaluationDTO.getCandidate()))
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


        Double averageMark = candidateEvaluationRepository.avg(candidateEvaluationDTO.getCandidate());


        return CandidateEvaluationResponseDTO.builder()
                .id(candidateEvaluationDTO.getId())
                .englishMark(candidateEvaluationDTO.getEnglishMark())
                .softSkillMark(candidateEvaluationDTO.getSoftSkillMark())
                .practiceMark(candidateEvaluationDTO.getPracticeMark())
                .candidate(candidateEvaluationDTO.getCandidate())
                .averageMark(averageMark)
                .averageCandidateEvaluation(calculateCandidateEvaluationAverage(candidateEvaluationDTO, averageMark))
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
    private Double calculateCandidateEvaluationAverage(CandidateEvaluationDTO candidateEvaluationDTO, Double averageMark){

        final Double ENGLISH_MARK_WEIGHT = Double.parseDouble(Objects.requireNonNull(env.getProperty("englishMark.average.weight")));
        final Double SOFT_SKILL_MARK_WEIGHT = Double.parseDouble(Objects.requireNonNull(env.getProperty("softSkill.average.weight")));
        final Double PRACTICE_MARK_WEIGHT = Double.parseDouble(Objects.requireNonNull(env.getProperty("practice.average.weight")));
        final Double AVERAGE_MARK_WEIGHT = Double.parseDouble(Objects.requireNonNull(env.getProperty("technical.average.weight")));
        double averageCandidateEvaluations = 0.0d;

        if(candidateEvaluationDTO.getEnglishMark() != null){
            averageCandidateEvaluations += candidateEvaluationDTO.getEnglishMark() * ENGLISH_MARK_WEIGHT;
        }
        if(candidateEvaluationDTO.getSoftSkillMark() != null){
            averageCandidateEvaluations += candidateEvaluationDTO.getSoftSkillMark() * SOFT_SKILL_MARK_WEIGHT;
        }
        if(candidateEvaluationDTO.getPracticeMark() != null){
            averageCandidateEvaluations += candidateEvaluationDTO.getPracticeMark() * PRACTICE_MARK_WEIGHT;
        }
        if(averageMark != null){
            averageCandidateEvaluations += averageMark * AVERAGE_MARK_WEIGHT;
        }
        return averageCandidateEvaluations;
    }
}
