package org.inthergroup.ims.internship.service;

import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.internship.controller.InternshipDTO;
import org.inthergroup.ims.internship.controller.UserMentorDTO;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.repository.InternshipRepository;
import org.inthergroup.ims.login.model.User;
import org.inthergroup.ims.login.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepository;
    private final PreInterviewTestEvaluationService preInterviewTestService;
    private final UserRepository userRepository;

    public InternshipServiceImpl(InternshipRepository internshipRepository,
                                 PreInterviewTestEvaluationService preInterviewTestService,
                                 UserRepository userRepository) {
        this.internshipRepository = internshipRepository;
        this.preInterviewTestService = preInterviewTestService;
        this.userRepository = userRepository;
    }


    @Override
    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    @Override
    public void createInternship(InternshipDTO internship) {
        internshipRepository.save(toInternship(internship));
    }

    @Override
    public Internship toInternship(InternshipDTO internshipDTO) {
        Internship internship = new Internship();
        internship.setProjectName(internshipDTO.getProjectName());
        internship.setCategory(internshipDTO.getCategory());
        internship.setMentors(toUserList(internshipDTO.getMentors()));
        internship.setPeriodFrom(internshipDTO.getPeriodFrom());
        internship.setPeriodTo(internshipDTO.getPeriodTo());
        internship.setInternshipStatus(internshipDTO.getInternshipStatus());
        internship.setPreInterviewTestList(internshipDTO.getPreInterviewTestList());
        internship.setTechQuesListName(internshipDTO.getTechQuesListName());
        internship.setGitHubUrl(internshipDTO.getGitHubUrl());
        internship.setTrelloBoardUrl(internshipDTO.getTrelloBoardUrl());
        internship.setDeployedAppUrl(internshipDTO.getDeployedAppUrl());
        internship.setPresentationUrl(internshipDTO.getPresentationUrl());
        internship.setCandidates(internshipDTO.getCandidates());
        return internship;
    }

    @Override
    public User toUser(UserMentorDTO mentorDTO) {
        return userRepository.findById(mentorDTO.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Mentor" + mentorDTO.getName() + "was not found!"));
    }

    @Override
    public List<User> toUserList(List<UserMentorDTO> mentorDTOList) {
        return mentorDTOList.stream().map(this::toUser).collect(Collectors.toList());
    }

    @Override
    public List<Candidate> getAllCandidatesByInternshipId(String internshipId) {
        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new IllegalArgumentException("Internship with id" + internshipId + "was not found!"));
        return internship.getCandidates();
    }


}
