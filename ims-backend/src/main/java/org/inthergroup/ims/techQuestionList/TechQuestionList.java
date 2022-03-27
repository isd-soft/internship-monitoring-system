package org.inthergroup.ims.techQuestionList;

import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.techQuestion.TechQuestion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;


@Entity
@Getter
@Setter
public class TechQuestionList {

    @Id
    @NotBlank
    @Column(name = "id")
    private String id;

    @Column
    private String name;

    @OneToMany(mappedBy = "techQuestionList")
    private Set<TechQuestion> techQuestionListTechQuestions;

    public TechQuestionList(){
        id = UUID.randomUUID().toString();
    }
}
