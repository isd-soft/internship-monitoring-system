package org.inthergroup.ims.internship.facade;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
