package org.inthergroup.ims.candidate.model;

import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidate_evaluation.CandidateEvaluation;
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

    @OneToMany(mappedBy = "candidate")
    private Set<TechMark> candidateTechMarks;

    @OneToOne(mappedBy = "candidate", fetch = FetchType.LAZY)
    private CandidateEvaluation candidate;

    public Candidate(){
        id = UUID.randomUUID().toString();
    }
}
