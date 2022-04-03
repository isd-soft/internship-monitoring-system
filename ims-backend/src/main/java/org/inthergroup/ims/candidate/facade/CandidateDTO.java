package org.inthergroup.ims.candidate.facade;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.candidate.model.CandidateStatus;

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
    private CandidateStatus status;

    private String internship;

    public CandidateDTO() {
        
    }

    public CandidateDTO(Candidate candidate) {
        this.setId(candidate.getId());
        this.setName(candidate.getName());
        this.setSurname(candidate.getSurname());
        this.setEmail(candidate.getEmail());
        this.setCv(candidate.getCv());
        this.setComment(candidate.getComment());
        this.setStatus(candidate.getStatus());
        this.setInternship(candidate.getInternship().getId());
    }
}
