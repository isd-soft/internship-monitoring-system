package org.inthergroup.ims.candidateEvaluation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(	name = "Tech_Mark")
public class TechMark {

    @Id
    @NotBlank
    @Column(name = "id")
    private String id;

    @JoinColumn(name = "candidateEvaluation_id")
    private String candidateEvaluation;

    @JoinColumn(name= "techQuestion_id")
    private String techQuestion;

    @NotBlank
    @Column(name = "mark")
    private Double mark;

    public TechMark(){
        id = UUID.randomUUID().toString();
    }
}
