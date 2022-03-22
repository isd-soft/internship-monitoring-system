package org.inthergroup.ims.internship.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidate.model.Candidate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "pre_interview_tests")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class PreInterviewTestEvaluation {
    @Id
    @Column(name = "pre_interview_test_id")
    private String id;
    @Column(name = "pre_interview_test_name")
    @Enumerated(EnumType.STRING)
    private PreInterviewTest preInterviewTestName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "internship_id")
    private Internship internship;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Candidate candidate;
    @Column(name = "mark")
    private double mark;


    public PreInterviewTestEvaluation() {
        id = UUID.randomUUID().toString();
    }

}
