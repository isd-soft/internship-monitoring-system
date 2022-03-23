package org.inthergroup.ims.candidate.Repository;
import org.inthergroup.ims.candidate.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,String> {

    @Query(value="find Candidate c where email=:email and enabled=:enabled",nativeQuery=true)
    Candidate findByEmailAndEnabled(String email, short enabled);
    Candidate findByEmail(String email);
}
