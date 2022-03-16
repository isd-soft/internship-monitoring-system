package org.inthergroup.ims.service;

import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.controller.InternshipDTO;

import java.util.List;

public interface InternshipService {

    List<Internship> getAllInternships();
    void save(InternshipDTO internship);
    Internship toInternship(InternshipDTO internshipDTO);

}
