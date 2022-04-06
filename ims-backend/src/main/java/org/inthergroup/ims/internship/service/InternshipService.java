package org.inthergroup.ims.internship.service;

import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.facade.InternshipDTO;

import java.util.List;

public interface InternshipService {

    List<Internship> getAllInternships();
    Internship getById(String id);
    void create(final Internship internship);
    void update(String id, final Internship internship);
    void delete(final String id);
    List<Candidate> getAllCandidatesByInternshipId(String internshipId);
}
