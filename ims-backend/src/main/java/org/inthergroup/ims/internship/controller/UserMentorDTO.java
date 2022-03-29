package org.inthergroup.ims.internship.controller;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserMentorDTO {
    private String id;
    private String name;
    private String surname;
    private String jobPosition;
}
