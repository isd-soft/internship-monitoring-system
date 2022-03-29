package org.inthergroup.ims.internship.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.login.model.User;
import org.inthergroup.ims.techQuestionList.TechQuestionList;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "internships", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Internship {
    @Id
    private String id;
    @Column(columnDefinition = "varchar(100) default 'Upcoming Internship'",name = "project_name")
    private String projectName;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "mentors_internships",
            joinColumns = @JoinColumn(name = "internship_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "mentor_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"internship_id",
                    "mentor_id"},
                    name = "mentor_internship_constraint"))
    private List<User> mentors;
    @Column(columnDefinition = "date", name = "period_from")
    private LocalDate periodFrom;
    @Column(columnDefinition = "date", name = "period_to")
    private LocalDate periodTo;
    @Column(columnDefinition = "varchar(32) default 'NEW'", name = "status")
    @Enumerated(EnumType.STRING)
    private Status internshipStatus;
    @ElementCollection(targetClass = PreInterviewTest.class)
    @Enumerated(EnumType.STRING)
    private List<PreInterviewTest> preInterviewTestList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_question_list_id")
    private TechQuestionList techQuesListId;
    @Column(name = "github_link")
    private String gitHubUrl;
    @Column(name = "trello_board")
    private String trelloBoardUrl;
    @Column(name = "deployed_app")
    private String deployedAppUrl;
    @Column(name = "powerpoint_presentation")
    private String presentationUrl;
    @OneToMany(mappedBy="internship")
    private List<Candidate> candidates;

    public Internship() {
        id = UUID.randomUUID().toString();
    }
}
