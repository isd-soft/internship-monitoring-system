package org.inthergroup.ims.techQuestion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechQuestionRepository extends JpaRepository<TechQuestion, String> {

    List<TechQuestion> getTechQuestionsByTechQuestionListId(String id);
}
