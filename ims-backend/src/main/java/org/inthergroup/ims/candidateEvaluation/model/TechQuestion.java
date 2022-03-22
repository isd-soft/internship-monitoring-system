package org.inthergroup.ims.candidateEvaluation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(	name = "Tech_Question")
public class TechQuestion {

    @Id
    @NotBlank
    @Column(name = "id")
    private String id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @JoinColumn(name = "techQuestionList_id")
    private String techQuestionList_id;

    public TechQuestion() {
        id = UUID.randomUUID().toString();
    }
}

