package org.inthergroup.ims.preInterwiewTestEvaluation;

import org.inthergroup.ims.candidate.facade.CandidateDTO;
import org.inthergroup.ims.candidate.service.CandidateService;
import org.inthergroup.ims.internship.service.InternshipService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PreInterviewTestMarkFacade {

    private final CandidateService candidateService;
    private final PreInterviewTestMarkService preInterviewTestMarkService;

    public PreInterviewTestMarkFacade(CandidateService candidateService,
                                      PreInterviewTestMarkService preInterviewTestMarkService) {
        this.candidateService = candidateService;
        this.preInterviewTestMarkService = preInterviewTestMarkService;
    }

    private PreInterviewTestMark mapToEntity(final PreInterviewTestMarkDTO preInterviewTestEvaluationDTO) {
        PreInterviewTestMark preInterviewTestEvaluation;

        if (preInterviewTestEvaluationDTO.getId() != null) {
            preInterviewTestEvaluation = preInterviewTestMarkService.getById(preInterviewTestEvaluationDTO.getId());
        } else {
            preInterviewTestEvaluation = new PreInterviewTestMark();
        }
        preInterviewTestEvaluation.setPreInterviewTestName(preInterviewTestEvaluationDTO.getPreInterviewTestName());
        preInterviewTestEvaluation.setCandidate(candidateService.getById(preInterviewTestEvaluationDTO.getCandidateId()));
        preInterviewTestEvaluation.setMark(preInterviewTestEvaluationDTO.getMark());
        return preInterviewTestEvaluation;
    }

    public List<PreInterviewTestMarkDTO> getPreInterviewTestMarksByCandidateId(String candidateId) {
            return preInterviewTestMarkService.getPreInterviewTestMarksByCandidateId(candidateId)
                    .stream()
                    .map(PreInterviewTestMarkDTO::new)
                    .collect(Collectors.toList());
    }
}
