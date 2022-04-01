package org.inthergroup.ims.internship.service;

import org.inthergroup.ims.internship.controller.PreInterviewTestEvaluationDTO;
import org.inthergroup.ims.internship.model.PreInterviewTestEvaluation;
import org.inthergroup.ims.internship.repository.PreInterviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreInterviewTestServiceImpl implements PreInterviewTestEvaluationService {

    private final PreInterviewRepository preInterviewRepository;

    public PreInterviewTestServiceImpl(PreInterviewRepository preInterviewRepository) {
        this.preInterviewRepository = preInterviewRepository;
    }

    @Override
    public List<PreInterviewTestEvaluation> getAllPreInterviewTestEvaluation() {
        return preInterviewRepository.findAll();
    }

    @Override
    public void save(PreInterviewTestEvaluationDTO preInterviewTest) {
        preInterviewRepository.save(toPreInterviewTestEntity(preInterviewTest));
    }

    @Override
    public PreInterviewTestEvaluation toPreInterviewTestEntity(PreInterviewTestEvaluationDTO preInterviewTestDTO) {
        PreInterviewTestEvaluation preInterviewTestEvaluation = new PreInterviewTestEvaluation();
        preInterviewTestEvaluation.setPreInterviewTestName(preInterviewTestDTO.getPreInterviewTestName());
        preInterviewTestEvaluation.setInternship(preInterviewTestDTO.getInternship());
        preInterviewTestEvaluation.setCandidate(preInterviewTestDTO.getCandidate());
        preInterviewTestEvaluation.setMark(preInterviewTestDTO.getMark());
        return null;
    }
}
