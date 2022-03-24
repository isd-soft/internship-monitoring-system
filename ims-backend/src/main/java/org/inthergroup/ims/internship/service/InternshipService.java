package org.inthergroup.ims.internship.service;

import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.internship.controller.UserMentorDTO;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.controller.InternshipDTO;
import org.inthergroup.ims.login.model.User;

import java.util.List;

public interface InternshipService {

    List<Internship> getAllInternships();
    void createInternship(InternshipDTO internship);
    Internship toInternship(InternshipDTO internshipDTO);
    User toUser(UserMentorDTO mentors);
    List<User> toUserList(List<UserMentorDTO> mentorDTOList);
    List<Candidate> getAllCandidatesByInternshipId(String internshipId);
}
