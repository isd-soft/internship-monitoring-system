package org.inthergroup.ims.preInterwiewTestEvaluation;

import org.inthergroup.ims.candidate.model.Candidate;

import java.util.List;

public interface PreInterviewTestMarkService {

    PreInterviewTestMark getById(String id);
    List<PreInterviewTestMark> getPreInterviewTestMarksByCandidateId(String candidateId);
    void update(PreInterviewTestMark preInterviewTestMark);
}
