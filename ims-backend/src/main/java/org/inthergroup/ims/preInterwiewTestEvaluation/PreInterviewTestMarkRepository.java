package org.inthergroup.ims.preInterwiewTestEvaluation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreInterviewTestMarkRepository extends JpaRepository<PreInterviewTestMark, String> {
   Optional<PreInterviewTestMark> getByCandidateIdAndPreInterviewTestName(String candidateId, PreInterviewTest preInterviewTestName);
}
