package org.inthergroup.ims.candidateEvaluation.repository;

import org.inthergroup.ims.candidateEvaluation.model.TechQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechQuestionRepository extends JpaRepository<TechQuestion, String> {
}
