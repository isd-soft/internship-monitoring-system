package org.inthergroup.ims.candidate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
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

    public Candidate() {
        id = UUID.randomUUID().toString();

    }
}