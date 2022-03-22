package org.inthergroup.ims.candidateEvaluation.service;

import org.inthergroup.ims.candidateEvaluation.model.TechQuestion;
import org.inthergroup.ims.candidateEvaluation.repository.TechQuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class TechQuestionServiceImpl implements TechQuestionService {

    private final TechQuestionRepository techQuestionRepository;

    public TechQuestionServiceImpl(TechQuestionRepository techQuestionRepository) {
        this.techQuestionRepository = techQuestionRepository;
    }

    @Override
    public TechQuestion addTechQuestion(TechQuestion techQuestion) {
        return techQuestionRepository.save(techQuestion);
    }

    @Override
    public void deleteTechQuestionById(String id) {
        techQuestionRepository.deleteById(id);
    }
}
