package org.inthergroup.ims.internship.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.inthergroup.ims.login.model.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "internships")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Internship {
    @Id
    @Column(name = "internship_id")
    private String id;
    @Column(columnDefinition = "varchar(100) default 'Upcoming Internship'",name = "project_name")
    private String projectName;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "mentors_internships",
            joinColumns = @JoinColumn(name = "internship_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> mentors;
    @Column(columnDefinition = "date", name = "period_from")
    private LocalDate periodFrom;
    @Column(columnDefinition = "date", name = "period_to")
    private LocalDate periodTo;
    @Column(columnDefinition = "varchar(32) default 'NEW'", name = "status")
    @Enumerated(EnumType.STRING)
    private Status internshipStatus;
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "internships_pre_interview_tests",
            joinColumns = @JoinColumn(name = "internship_id"),
            inverseJoinColumns = @JoinColumn(name = "pre_interview_test_id"))
    private List<PreInterviewTest> preInterviewTestList;
    @Column(name = "technical_question_list")
    private String techQuesListName;
    @Column(name = "github_link")
    private String gitHubUrl;
    @Column(name = "trello_board")
    private String trelloBoardUrl;
    @Column(name = "deployed_app")
    private String deployedAppUrl;
    @Column(name = "powerpoint_presentation")
    private String presentationUrl;


    public Internship() {
        id = UUID.randomUUID().toString();
    }
}
