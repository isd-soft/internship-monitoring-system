package org.inthergroup.ims.candidate_evaluation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CandidateEvaluationRepository extends JpaRepository<CandidateEvaluation, String> {
    @Query(value = "SELECT avg(mark) FROM tech_mark where candidate_id=?1", nativeQuery = true)
    public Double avg(String id);
}
