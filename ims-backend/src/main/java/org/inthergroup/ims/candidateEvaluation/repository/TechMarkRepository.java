package org.inthergroup.ims.candidateEvaluation.repository;

import org.inthergroup.ims.candidateEvaluation.model.TechMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechMarkRepository extends JpaRepository<TechMark, String> {
}
