package org.inthergroup.ims.techMark;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechMarkRepository extends JpaRepository<TechMark, String> {

    Optional<TechMark> getByTechQuestionIdAndCandidateId(String techQuestionId, String candidateId);
}
