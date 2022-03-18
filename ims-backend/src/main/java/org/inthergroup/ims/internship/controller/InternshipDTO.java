package org.inthergroup.ims.internship.controller;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.internship.model.Category;
import org.inthergroup.ims.internship.model.PreInterviewTest;
import org.inthergroup.ims.internship.model.Status;
import org.inthergroup.ims.login.model.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class InternshipDTO {
    private String id;
    private String projectName;
    private Category category;
    private List<User> mentors;
    private LocalDate periodFrom;
    private LocalDate periodTo;
    private Status internshipStatus;
    private List<PreInterviewTest> preInterviewTestList;
    private String techQuesListName;
    private String gitHubUrl;
    private String trelloBoardUrl;
    private String deployedAppUrl;
    private String presentationUrl;

    public InternshipDTO() {
    }
}
