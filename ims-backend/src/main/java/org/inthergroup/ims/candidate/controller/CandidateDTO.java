package org.inthergroup.ims.candidate.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidate.model.Status;


@Getter
@Setter
public class CandidateDTO {

    private String id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String surname;

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 255)
    private String cv;

    @Size(max = 255)
    private String comment;

    @NotNull
    private Status status;

}
