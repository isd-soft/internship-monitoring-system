package org.inthergroup.ims.internship.service;

import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.controller.InternshipDTO;
import org.inthergroup.ims.login.model.User;
import org.inthergroup.ims.techQuestionList.TechQuestionList;

import java.util.List;

public interface InternshipService {

    List<InternshipDTO> getAllInternships();

    void createInternship(final InternshipDTO internshipDTO);

    void update(String id, final InternshipDTO internshipDTO);

    void delete(final String id);

    Internship mapToInternshipEntity(InternshipDTO internshipDTO);

    InternshipDTO mapToInternshipDTO(Internship internship);

    List<User> getUserListByIds(List<String> mentorIdList);

    List<Candidate> getAllCandidatesByInternshipId(String internshipId);

    Candidate getCandidateById(String candidateId);

    List<Candidate> getCandidateListByIds(List<String> candidateIdList);

    TechQuestionList getTechQuestionListById(String tqlId);

    Internship getInternship(String id);
}
