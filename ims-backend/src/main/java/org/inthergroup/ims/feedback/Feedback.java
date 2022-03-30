package org.inthergroup.ims.feedback;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidate.model.Candidate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Feedback")
@AllArgsConstructor

public class Feedback {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "feedback")
    private String feedback;

    @Column(name = "to_candidate")
    private String toCandidate;

    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;


    public Feedback() {
        id = UUID.randomUUID().toString();

    }
}
