package org.inthergroup.ims.internship.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "pre_interview_tests")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class PreInterviewTest {
    @Id
    @Column(name = "pre_interview_test_id")
    private String id;
    @Column(name = "pre_interview_test_name")
    private String preInterviewTestName;

    public PreInterviewTest() {
        id = UUID.randomUUID().toString();
    }
}
