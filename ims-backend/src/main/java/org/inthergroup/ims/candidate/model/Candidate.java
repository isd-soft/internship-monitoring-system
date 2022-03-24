package org.inthergroup.ims.candidate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.inthergroup.ims.internship.model.Internship;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Table(name = "candidates")
public class Candidate {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "cv")
    private String cv;
    @Column(name = "comment")
    private String comment;
    @Enumerated(EnumType.STRING)
    private Status status;
    private double mark;
    @ManyToOne(targetEntity = Internship.class)
    @JoinColumn(name="internship_id", nullable = false)
    private Internship internship;

    public Candidate() {
        id = UUID.randomUUID().toString();

    }
}