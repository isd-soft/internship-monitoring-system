package org.inthergroup.ims.candidate.repository;

import org.inthergroup.ims.candidate.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, String> {

    // TODO: Remove this one from here and use the method from the Internship repository, do the same thing with the controller.
    List<Candidate> findAllByInternshipId(@Param("internship_id") String internshipsId);
}
