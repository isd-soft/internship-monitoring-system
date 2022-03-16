package org.inthergroup.ims.internship.controller;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
public class PreInterviewTestDTO {
    private String id;
    private String preInterviewTestName;

    public PreInterviewTestDTO(String id, String preInterviewTestName) {
        this.id = id;
        this.preInterviewTestName = preInterviewTestName;
    }
}
