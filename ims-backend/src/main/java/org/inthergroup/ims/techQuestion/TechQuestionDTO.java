package org.inthergroup.ims.techQuestion;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TechQuestionDTO {

    private String id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private String techQuestionList;
}
