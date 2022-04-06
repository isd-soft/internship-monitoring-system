package org.inthergroup.ims.techMark;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    private String id;

    @Column(name = "mark")
    private Double mark;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "techQuestion", nullable = false)
    private TechQuestion techQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    public TechMark(){
        id = UUID.randomUUID().toString();
    }
}
