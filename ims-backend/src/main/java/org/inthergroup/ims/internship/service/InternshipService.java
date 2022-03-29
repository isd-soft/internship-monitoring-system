package org.inthergroup.ims.internship.service;

import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.controller.InternshipDTO;
import org.inthergroup.ims.login.model.User;
import org.inthergroup.ims.techQuestionList.TechQuestionList;

import java.util.List;
import java.util.Set;

public interface InternshipService {

    List<Internship> getAllInternships();
    String createInternship(final InternshipDTO internship);
    Internship mapToInternship(InternshipDTO internshipDTO);
    User getUserById(String mentorId);
    List<User> getUserListByIds(List<String> mentorIdList);
    List<Candidate> getAllCandidatesByInternshipId(String internshipId);
    Candidate getCandidateById(String candidateId);
    List<Candidate> getCandidateListByIds(List<String> candidateIdList);
    TechQuestionList getTechQuestionListById(String tqlId);

}
