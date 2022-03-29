package org.inthergroup.ims.internship.service;

import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.candidate.repository.CandidateRepository;
import org.inthergroup.ims.internship.controller.InternshipDTO;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.repository.InternshipRepository;
import org.inthergroup.ims.login.model.User;
import org.inthergroup.ims.login.repository.UserRepository;
import org.inthergroup.ims.techQuestionList.TechQuestionList;
import org.inthergroup.ims.techQuestionList.TechQuestionListRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepository;
    private final UserRepository userRepository;
    private final CandidateRepository candidateRepository;
    private final TechQuestionListRepository techQuestionListRepository;

    public InternshipServiceImpl(InternshipRepository internshipRepository,
                                 UserRepository userRepository, CandidateRepository candidateRepository,
                                 TechQuestionListRepository techQuestionListRepository) {
        this.internshipRepository = internshipRepository;
        this.userRepository = userRepository;
        this.candidateRepository = candidateRepository;
        this.techQuestionListRepository = techQuestionListRepository;
    }


    @Override
    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    @Override
    public String createInternship(final InternshipDTO internship) {
        return internshipRepository.save(mapToInternship(internship)).getId();
    }

    @Override
    public Internship mapToInternship(InternshipDTO internshipDTO) {
        Internship internship = new Internship();
        internship.setProjectName(internshipDTO.getProjectName());
        internship.setCategory(internshipDTO.getCategory());
        internship.setMentors(getUserListByIds(internshipDTO.getMentorsId()));
        internship.setPeriodFrom(LocalDate.parse(internshipDTO.getPeriodFrom().toString()));
        internship.setPeriodTo(LocalDate.parse(internshipDTO.getPeriodTo().toString()));
        internship.setInternshipStatus(internshipDTO.getInternshipStatus());
        internship.setPreInterviewTestList(internshipDTO.getPreInterviewTestList());
        internship.setTechQuesListId(getTechQuestionListById(internshipDTO.getTechQuesListId()));
        internship.setGitHubUrl(internshipDTO.getGitHubUrl());
        internship.setTrelloBoardUrl(internshipDTO.getTrelloBoardUrl());
        internship.setDeployedAppUrl(internshipDTO.getDeployedAppUrl());
        internship.setPresentationUrl(internshipDTO.getPresentationUrl());
        internship.setCandidates(getCandidateListByIds(internshipDTO.getCandidatesId()));
        return internship;
    }

    @Override
    public User getUserById(String mentorId) {
        if (Objects.isNull(mentorId) || Objects.equals(mentorId, "")) {
            return null;
        }
        return userRepository.findById(mentorId)
                .orElseThrow(() -> new UsernameNotFoundException("Mentor with id = " + mentorId + " was not found!"));
    }

    @Override
    public List<User> getUserListByIds(List<String> mentorIdList) {
        if (Objects.isNull(mentorIdList) || mentorIdList.isEmpty()) {
            return Collections.emptyList();
        }
        return userRepository.findAllById(mentorIdList);
    }

    @Override
    public List<Candidate> getAllCandidatesByInternshipId(String internshipId) {
        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new IllegalArgumentException("Internship with id" + internshipId + "was not found!"));
        return internship.getCandidates();
    }

    @Override
    public Candidate getCandidateById(String candidateId) {
        if (Objects.isNull(candidateId) || Objects.equals(candidateId, "")) {
            return new Candidate();
        }
        return candidateRepository.findById(candidateId)
                .orElseThrow(() -> new UsernameNotFoundException("Candidate with id = " + candidateId + " was not found!"));
    }

    @Override
    public List<Candidate> getCandidateListByIds(List<String> candidateIdList) {
        if (Objects.isNull(candidateIdList) || candidateIdList.isEmpty()) {
            return Collections.emptyList();
        }
        return candidateRepository.findAllById(candidateIdList);
    }

    @Override
    public TechQuestionList getTechQuestionListById(String tqlId) {
        if (Objects.isNull(tqlId) || Objects.equals(tqlId, "")) {
            return new TechQuestionList();
        }
        return techQuestionListRepository.findById(tqlId)
                .orElseThrow(() -> new IllegalArgumentException("TechQuestion list with id = " + tqlId + " was not found!"));
    }


    @Override
    public Internship getInternship(final  String id) {
        Internship internship = internshipRepository.findById(id).get();
        return internship;
    }
}
