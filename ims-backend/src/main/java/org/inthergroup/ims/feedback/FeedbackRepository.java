package org.inthergroup.ims.feedback;

import org.inthergroup.ims.candidate.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FeedbackRepository extends JpaRepository <Feedback,String> {

    @Query(value = "SELECT * FROM feedback WHERE candidate_id = :candidate_id", nativeQuery = true)
    List<Feedback> getFeedbacksByCandidateId(@Param("candidate_id") String candidateId);
}
