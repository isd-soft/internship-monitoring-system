package org.inthergroup.ims.techQuestion;

import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.techQuestionList.TechQuestionList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Getter
@Setter
public class TechQuestion {

    @Id
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
