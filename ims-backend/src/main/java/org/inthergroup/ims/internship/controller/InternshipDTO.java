package org.inthergroup.ims.internship.controller;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.internship.model.Admins;
import org.inthergroup.ims.internship.model.Category;
import org.inthergroup.ims.internship.model.PreInterviewTest2;
import org.inthergroup.ims.internship.model.Status;

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
    private List<Admins> mentors;
    private Date periodFrom;
    private Date periodTo;
    private Status internshipStatus;
    private List<PreInterviewTest2> preInterviewTestList;
    private String techQuesListName;
    private String gitHubUrl;
    private String trelloBoardUrl;
    private String deployedAppUrl;
    private String presentationUrl;

}
