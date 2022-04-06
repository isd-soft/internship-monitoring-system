package org.inthergroup.ims.internship.facade;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.internship.model.Category;
import org.inthergroup.ims.preInterwiewTestEvaluation.PreInterviewTest;
import org.inthergroup.ims.internship.model.Status;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
    //TODO- review after intergration with candidates list selected by internshipId
//    private List<String> candidatesId;
    public InternshipDTO() {
    }
}
