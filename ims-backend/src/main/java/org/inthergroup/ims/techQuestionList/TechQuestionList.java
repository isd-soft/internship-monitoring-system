package org.inthergroup.ims.techQuestionList;

import lombok.Getter;
import lombok.Setter;

import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.techQuestion.TechQuestion;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class TechQuestionList {

    @Id
    private String id;

    @Column
    private String name;

    @OneToMany(mappedBy = "techQuestionList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TechQuestion> TechQuestions;

    @OneToMany(mappedBy = "techQuestionList", cascade = CascadeType.REMOVE)
    private List<Internship> internships;

    public TechQuestionList() {
        id = UUID.randomUUID().toString();
    }
}
