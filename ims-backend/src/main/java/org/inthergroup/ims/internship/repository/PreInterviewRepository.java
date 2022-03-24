package org.inthergroup.ims.internship.repository;

import org.inthergroup.ims.internship.model.PreInterviewTestEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreInterviewRepository extends JpaRepository<PreInterviewTestEvaluation, String> {
}
