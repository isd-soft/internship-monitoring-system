package org.inthergroup.ims.candidateEvaluation.repository;


import org.inthergroup.ims.candidateEvaluation.model.CandidateEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CandidateEvaluationRepository extends JpaRepository<CandidateEvaluation, String> {

}