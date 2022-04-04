package org.inthergroup.ims.techQuestion;

import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.techMark.TechMark;
import org.inthergroup.ims.techQuestionList.TechQuestionList;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;


@Entity
@Getter
@Setter
public class TechQuestion {

    @Id
    @NotBlank
    @Column(name = "id")
    private String id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_question_list_id", nullable = false)
    private TechQuestionList techQuestionList;

    public TechQuestion(){
        id = UUID.randomUUID().toString();
    }

}
