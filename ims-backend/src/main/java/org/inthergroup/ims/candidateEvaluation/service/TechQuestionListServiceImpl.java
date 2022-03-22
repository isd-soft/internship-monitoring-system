package org.inthergroup.ims.candidateEvaluation.service;

import org.inthergroup.ims.candidateEvaluation.model.TechQuestionList;
import org.inthergroup.ims.candidateEvaluation.repository.TechQuestionListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechQuestionListServiceImpl implements TechQuestionListService {

    private final TechQuestionListRepository techQuestionListRepository;

    public TechQuestionListServiceImpl(TechQuestionListRepository techQuestionListRepository) {
        this.techQuestionListRepository = techQuestionListRepository;
    }

    @Override
    public List<TechQuestionList> getAllTechQuestionLists() {
        return null;
    }

    @Override
    public TechQuestionList addQuestionList(TechQuestionList techQuestionList) {
    return techQuestionListRepository.save(techQuestionList);
    }

    @Override
    public void deleteQuestionListById(String id) {
      techQuestionListRepository.deleteById(id);
    }
}
