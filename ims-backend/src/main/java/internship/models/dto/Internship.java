package internship.models.dto;

import internship.models.Admins;
import internship.models.Category;
import internship.models.PreInterviewTest;
import internship.models.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "internships")
@Getter
@Setter
@EqualsAndHashCode
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "internship_id")
    private Long id;
    @Column(name = "project_name")
    private @NotBlank String projectName;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "mentors_internships",
            joinColumns = @JoinColumn(name = "internship_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Admins> mentors;
    @Column(name = "period_from")
    private @NotBlank Date periodFrom;
    @Column(name = "period_to")
    private @NotBlank Date periodTo;
    @Column(columnDefinition = "varchar(32) default 'NEW'")
    @Enumerated(EnumType.STRING)
    private Status internshipStatus = Status.NEW;
    @Enumerated(EnumType.STRING)
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
    @Column(name = "functional_specifications")
    private String functionalSpecificationUrl;
    @Column(name = "screenshots")
    private String screenShotsUrl;


}
