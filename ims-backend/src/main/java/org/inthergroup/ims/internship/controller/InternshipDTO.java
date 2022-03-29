package org.inthergroup.ims.internship.controller;

import lombok.*;
import org.inthergroup.ims.candidate.controller.CandidateDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.internship.model.Category;
import org.inthergroup.ims.internship.model.PreInterviewTest;
import org.inthergroup.ims.internship.model.Status;
import org.inthergroup.ims.techQuestionList.TechQuestionList;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class InternshipDTO {
    private String id;
    private String projectName;
    private Category category;
    private List<String> mentorsId;
    private LocalDate periodFrom;
    private LocalDate periodTo;
    private Status internshipStatus;
    private List<PreInterviewTest> preInterviewTestList;
    private String techQuesListId;
    private String gitHubUrl;
    private String trelloBoardUrl;
    private String deployedAppUrl;
    private String presentationUrl;
    private List<String> candidatesId;
    public InternshipDTO() {
    }
}
