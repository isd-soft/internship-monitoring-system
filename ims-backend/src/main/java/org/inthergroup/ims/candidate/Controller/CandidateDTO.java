package org.inthergroup.ims.candidate.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidate.model.Status;

@AllArgsConstructor
@Getter
@Setter

public class CandidateDTO {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String cv;
    private String comment;
    private Status status;
    private double mark;
}
