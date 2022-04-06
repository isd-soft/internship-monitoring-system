package org.inthergroup.ims.candidate.model;

import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.inthergroup.ims.feedback.Feedback;
import org.inthergroup.ims.internship.model.Internship;

import javax.persistence.*;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidate_evaluation.CandidateEvaluation;
import org.inthergroup.ims.preInterwiewTestEvaluation.PreInterviewTestMark;
import org.inthergroup.ims.techMark.TechMark;

@Entity
@Getter
@Setter
public class Candidate {

    @Id
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
    private CandidateStatus status;

    @ManyToOne(targetEntity = Internship.class)
    @JoinColumn(name = "internship_id")
    private Internship internship;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    private Set<TechMark> candidateTechMarks;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(mappedBy = "candidate", fetch = FetchType.LAZY)
    private CandidateEvaluation candidate;
    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    private Set<Feedback> feedback;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    private Set<PreInterviewTestMark> candidatePreInterviewMarks;

    public Candidate() {
        id = UUID.randomUUID().toString();
        status = CandidateStatus.NEW;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", cv='" + cv + '\'' +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                ", internship=" + internship +
                ", candidateTechMarks=" + candidateTechMarks +
                ", candidate=" + candidate +
                ", feedback=" + feedback +
                '}';
    }
}