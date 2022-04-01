package org.inthergroup.ims.internship.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidate.model.Candidate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "pre_interview_tests_evaluation")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class PreInterviewTestEvaluation {

    @Id
    private String id;

    @Column(name = "test_name")
    @Enumerated(EnumType.STRING)
    private PreInterviewTest preInterviewTestName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "internship_id")
    private Internship internship;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Column(name = "mark")
    private double mark;

    public PreInterviewTestEvaluation() {
        id = UUID.randomUUID().toString();
    }
}
