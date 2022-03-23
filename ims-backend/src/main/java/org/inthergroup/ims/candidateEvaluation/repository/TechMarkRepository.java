package org.inthergroup.ims.candidateEvaluation.repository;

import org.inthergroup.ims.candidateEvaluation.dto.AvgTechMark;
import org.inthergroup.ims.candidateEvaluation.model.TechMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TechMarkRepository extends JpaRepository<TechMark, String> {
    @Query(value = "SELECT avg(mark) FROM Tech_Mark where candidate_evaluation=?1", nativeQuery = true)
    public Double avg(String id);


}
