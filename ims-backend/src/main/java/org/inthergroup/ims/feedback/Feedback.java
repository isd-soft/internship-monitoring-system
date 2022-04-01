package org.inthergroup.ims.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.login.model.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Feedback() {
        id = UUID.randomUUID().toString();
    }
}
