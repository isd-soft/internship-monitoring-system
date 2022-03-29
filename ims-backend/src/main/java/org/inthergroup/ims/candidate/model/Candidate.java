package org.inthergroup.ims.candidate.model;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.inthergroup.ims.internship.model.Internship;

import javax.persistence.*;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidate_evaluation.CandidateEvaluation;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.techMark.TechMark;


@Entity
@Getter
@Setter
public class Candidate {

    @Id
    @NotBlank
    @Column(name = "id")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String cv;

    @Column
    private String comment;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    private double mark;
    @ManyToOne(targetEntity = Internship.class)
    @JoinColumn(name="internship_id")
    @JsonIgnore
    private Internship internship;
    @JsonIgnore
    @OneToMany(mappedBy = "candidate",  fetch = FetchType.LAZY)
    private Set<TechMark> candidateTechMarks;
    @JsonIgnore
    @OneToOne(mappedBy = "candidate", fetch = FetchType.LAZY)
    private CandidateEvaluation candidate;

    public Candidate(){
        id = UUID.randomUUID().toString();

    }
}