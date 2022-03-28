package org.inthergroup.ims.candidate.repository;
import org.inthergroup.ims.candidate.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, String> {
//    @Query(value = "find Candidate c where email=:email and enabled=:enabled", nativeQuery = true)
//    Candidate findByEmailAndEnabled(String email, short enabled);
//
//    Candidate findByEmail(String email);
@Query(value = "SELECT * FROM candidate WHERE internship_id = :internship_id", nativeQuery = true)
List<Candidate> getCandidatesByInternshipId(@Param("internship_id") String internshipsId);
}
