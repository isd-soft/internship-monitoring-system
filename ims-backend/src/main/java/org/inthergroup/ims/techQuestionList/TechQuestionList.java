package org.inthergroup.ims.techQuestionList;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.techQuestion.TechQuestion;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Entity
@Getter
@Setter
public class TechQuestionList {

    @Id
    @NotBlank
    private String id;

    @Column
    private String name;


    @OneToMany(mappedBy = "techQuestionList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TechQuestion> techQuestionListTechQuestions;

    @OneToMany(mappedBy = "techQuesList")
    private List<Internship> internships;

    public TechQuestionList() {
        id = UUID.randomUUID().toString();
    }
}
