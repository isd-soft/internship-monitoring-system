package org.inthergroup.ims.techMark;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.techQuestion.TechQuestion;


import java.util.UUID;


@Entity
@Getter
@Setter
public class TechMark {

    @Id
    @NotBlank
    @Column(name = "id")
    private String id;

    @Column(nullable = false)
    private Double mark;

    @OneToOne
    @JoinColumn(name = "tech_question_id", nullable = false)
    private TechQuestion techQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    public TechMark(){
        id = UUID.randomUUID().toString();
    }

}
