package org.inthergroup.ims.internship.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.login.model.User;
import org.inthergroup.ims.preInterwiewTestEvaluation.PreInterviewTest;
import org.inthergroup.ims.techQuestionList.TechQuestionList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "internships", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
@Getter
@Setter
@EqualsAndHashCode
public class Internship {

    @Id
    private String id;

    @Column(columnDefinition = "text default 'Upcoming Internship'",name = "project_name")
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

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status internshipStatus;

    @ElementCollection(targetClass = PreInterviewTest.class)
    @Enumerated(EnumType.STRING)
    private List<PreInterviewTest> preInterviewTestList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tech_question_list_id")
    private TechQuestionList techQuestionList;

    @Column(name = "github_link")
    private String gitHubUrl;

    @Column(name = "trello_board")
    private String trelloBoardUrl;

    @Column(name = "deployed_app")
    private String deployedAppUrl;

    @Column(name = "powerpoint_presentation")
    private String presentationUrl;

    @OneToMany(mappedBy="internship", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidate> candidates;

    public Internship() {
        id = UUID.randomUUID().toString();
        internshipStatus = Status.NEW;
    }
}
