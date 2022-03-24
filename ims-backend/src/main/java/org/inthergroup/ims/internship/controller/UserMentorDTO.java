package org.inthergroup.ims.internship.controller;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class UserMentorDTO {
    private String id;
    private String name;
    private String surname;
    private String jobPosition;
}
