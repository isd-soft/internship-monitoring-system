package org.inthergroup.ims.preInterwiewTestEvaluation;

import java.util.List;

public interface PreInterviewTestMarkService {

    List<PreInterviewTestMark> getAllPreInterviewTestEvaluation();
    PreInterviewTestMark getById(String id);
    List<PreInterviewTestMark> getPreInterviewTestMarksByCandidateId(String candidateId);
}
