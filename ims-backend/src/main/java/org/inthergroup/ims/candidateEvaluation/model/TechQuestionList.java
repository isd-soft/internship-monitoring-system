package org.inthergroup.ims.candidateEvaluation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(	name = "techQuestionList")
public class TechQuestionList {

        @Id
        @NotBlank
        @Column(name = "id")
        private String id;

        @NotBlank
        @Column(name = "name")
        private String name;

    public TechQuestionList() {
        id = UUID.randomUUID().toString();
    }
}
