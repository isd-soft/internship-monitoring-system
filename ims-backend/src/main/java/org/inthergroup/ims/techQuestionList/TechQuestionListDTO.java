package org.inthergroup.ims.techQuestionList;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;


@Getter
@Setter
public class TechQuestionListDTO {

    private String id;

    @Size(max = 255)
    private String name;

}
