package org.inthergroup.ims.techQuestion;

import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.techQuestionList.TechQuestionList;
import org.inthergroup.ims.techMark.TechMark;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
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

    @OneToMany(mappedBy = "techQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TechMark> TechMark;
    public TechQuestion(){
        id = UUID.randomUUID().toString();
    }
}
