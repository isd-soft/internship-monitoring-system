package org.inthergroup.ims.internship.service;

import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.controller.InternshipDTO;

import java.util.List;

public interface InternshipService {

    List<Internship> getAllInternships();
    void createInternship(InternshipDTO internship);
    Internship toInternship(InternshipDTO internshipDTO);

}
