package org.inthergroup.ims.feedback;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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


    public Feedback() {
        id = UUID.randomUUID().toString();

    }
}
