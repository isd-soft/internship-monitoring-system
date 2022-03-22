package org.inthergroup.ims.candidateEvaluation.service;

import org.inthergroup.ims.candidateEvaluation.model.TechQuestion;
import org.inthergroup.ims.candidateEvaluation.model.TechQuestionList;
import org.springframework.stereotype.Service;

@Service
public interface TechQuestionService {

    public TechQuestion addTechQuestion(TechQuestion techQuestion);

    public void deleteTechQuestionById(String id);

}
