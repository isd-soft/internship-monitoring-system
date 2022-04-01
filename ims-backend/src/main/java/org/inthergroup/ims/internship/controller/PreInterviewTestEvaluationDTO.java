package org.inthergroup.ims.internship.controller;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.model.PreInterviewTest;

@Getter
@Setter
@EqualsAndHashCode
public class PreInterviewTestEvaluationDTO {
    private String id;
    private PreInterviewTest preInterviewTestName;
    private Internship internship;
    private Candidate candidate;
    private double mark;
}
