package org.inthergroup.ims.candidate_evaluation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.inthergroup.ims.candidate.model.Candidate;

import java.util.UUID;

@Entity
@Getter
@Setter
public class CandidateEvaluation {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "english_mark")
    private Double englishMark;

    @Column(name = "soft_skill_mark")
    private Double softSkillMark;

    @Column(name = "practice_mark")
    private Double practiceMark;

    @OneToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Candidate candidate;

    public CandidateEvaluation(){
        id = UUID.randomUUID().toString();
    }
}
