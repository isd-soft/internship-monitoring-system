package org.inthergroup.ims.candidateEvaluation.repository;

import org.inthergroup.ims.candidateEvaluation.model.TechQuestionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechQuestionListRepository extends JpaRepository<TechQuestionList, String> {
}
