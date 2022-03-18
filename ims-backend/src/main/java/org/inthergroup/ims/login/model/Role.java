package org.inthergroup.ims.login.model;


import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "roles")
public class Role{
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private URole name;
    public Role() {
        id = UUID.randomUUID().toString();
    }

}