package org.inthergroup.ims.techMark;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TechMarkDTO {

    private String id;

    @NotNull
    private Double mark;

    @NotNull
    private String techQuestion;

    @NotNull
    private String candidate;
}
