package org.inthergroup.ims.internship.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "pre_interview_tests")
@Getter
@Setter
@EqualsAndHashCode
public class PreInterviewTest {
    @Id
    @Column(name = "pre_interview_tests_id")
    private String id;
    @Column(name = "pre_interview_tests_name")
    private @NotBlank String preInterviewTestName;
}
