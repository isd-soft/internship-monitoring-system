package org.inthergroup.ims.candidateEvaluation.service;

import org.inthergroup.ims.candidateEvaluation.model.TechQuestionList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TechQuestionListService {

    List<TechQuestionList> getAllTechQuestionLists();

    public TechQuestionList addQuestionList(TechQuestionList techQuestionList);

    public void deleteQuestionListById(String id);
}
