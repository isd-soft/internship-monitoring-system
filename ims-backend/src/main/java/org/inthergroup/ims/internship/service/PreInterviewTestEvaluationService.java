package org.inthergroup.ims.internship.service;

import org.inthergroup.ims.internship.controller.PreInterviewTestEvaluationDTO;
import org.inthergroup.ims.internship.model.PreInterviewTestEvaluation;

import java.util.List;

public interface PreInterviewTestEvaluationService {
    List<PreInterviewTestEvaluation> getAllPreInterviewTestEvaluation();
    void save(PreInterviewTestEvaluationDTO preInterviewTest);
    PreInterviewTestEvaluation toPreInterviewTestEntity(PreInterviewTestEvaluationDTO preInterviewTestDTO);
}
