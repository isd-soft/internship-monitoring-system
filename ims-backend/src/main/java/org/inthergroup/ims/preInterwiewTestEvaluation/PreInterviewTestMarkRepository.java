package org.inthergroup.ims.preInterwiewTestEvaluation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PreInterviewTestMarkRepository extends JpaRepository<PreInterviewTestMark, String> {
   @Query(value = "SELECT avg(mark) FROM pre_interview_tests_evaluation where candidate_id=?1", nativeQuery = true)
   Double testAvg(String id);

   Optional<PreInterviewTestMark> getByCandidateIdAndPreInterviewTestName(String candidateId, PreInterviewTest preInterviewTestName);
}
