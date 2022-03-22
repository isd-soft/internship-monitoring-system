package org.inthergroup.ims.login.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
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