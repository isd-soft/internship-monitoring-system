package org.inthergroup.ims.candidate.controller;

import lombok.Data;
import org.inthergroup.ims.candidate.model.Status;
import org.inthergroup.ims.internship.model.Internship;

@Data
public class CandidateDTO {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String cv;
    private String comment;
    private Status status;
    private double mark;
    private Internship internship;
}
