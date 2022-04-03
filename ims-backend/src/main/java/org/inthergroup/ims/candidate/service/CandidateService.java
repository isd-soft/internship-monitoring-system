package org.inthergroup.ims.candidate.service;

import java.util.List;

import org.inthergroup.ims.candidate.model.Candidate;

public interface CandidateService {

    List<Candidate> getAllCandidatesByInternshipId(String internshipId);

    List<Candidate> findAll();

    Candidate getById(final String id);

    void create(final Candidate candidate);

    void update(Candidate candidate);

    void delete(final String id);
}

