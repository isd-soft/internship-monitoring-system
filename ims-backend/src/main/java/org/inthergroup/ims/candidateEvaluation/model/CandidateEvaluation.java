package org.inthergroup.ims.candidateEvaluation.model;

import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidate.model.Candidate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(	name = "Candidate_Evaluation")
public class CandidateEvaluation {

    @Id
    @NotBlank
    @Column(name = "id")
    private String id;

    @NotBlank
    @Column(name = "english_mark")
    private Double english;

    @NotBlank
    @Column(name = "softskills_mark")
    private Double softskills;

    @NotBlank
    @Column(name = "practice_mark")
    private Double practice;

    @JoinColumn(name = "candidates_id")
    private String candidate;

    public CandidateEvaluation() {
        id = UUID.randomUUID().toString();
    }
}
