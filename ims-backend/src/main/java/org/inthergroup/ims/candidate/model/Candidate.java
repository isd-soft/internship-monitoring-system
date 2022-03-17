package org.inthergroup.ims.candidate.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "candidate")
public class Candidate {
    @Id
    @Column(name = "candidate_id")
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
    @Column(columnDefinition = "varchar(32) default 'NEW'")
    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;
    @Column(name = "mark")
    private double mark;


    public Candidate() {
        id = UUID.randomUUID().toString();

    }
}