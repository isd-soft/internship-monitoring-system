package org.inthergroup.ims.candidate_evaluation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CandidateEvaluationRepository extends JpaRepository<CandidateEvaluation, String> {

    @Query(value = "SELECT avg(mark) FROM tech_mark where candidate_id=?1", nativeQuery = true)
    Double avg(String id);

    @Query(value = "SELECT avg(mark) FROM pre_interview_tests_evaluation where candidate_id=?1", nativeQuery = true)
    Double testAvg(String id);

    Optional<CandidateEvaluation> getCandidateEvaluationByCandidateId(String id);
}
