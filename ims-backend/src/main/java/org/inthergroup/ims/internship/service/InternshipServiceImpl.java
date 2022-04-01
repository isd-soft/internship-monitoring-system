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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public List<InternshipDTO> getAllInternships() {
        List<Internship> internships = internshipRepository.findAll();
        if (internships.isEmpty()) {
            return Collections.emptyList();
        }
        return internships.stream().map(this::mapToInternshipDTO).collect(Collectors.toList());
    }

    @Override
    public void createInternship(final InternshipDTO internshipDTO) {
        internshipRepository.save(mapToInternshipEntity(internshipDTO));
    }

    @Override
    public void update(String id, InternshipDTO internshipDTO) {
        internshipRepository.save(mapToInternshipEntity(internshipDTO));
    }

    @Override
    public void delete(String id) {
        internshipRepository.deleteById(id);
    }

    @Override
    public Internship mapToInternshipEntity(InternshipDTO internshipDTO) {

        final Internship internship = getInternshipByIdOrCreate(internshipDTO.getId());
        internship.setProjectName(internshipDTO.getProjectName());
        internship.setCategory(internshipDTO.getCategory());
        internship.setMentors(getUserListByIds(internshipDTO.getMentorsId()));
        if (Objects.nonNull(internshipDTO.getPeriodFrom())) {
            internship.setPeriodFrom(LocalDate.parse(internshipDTO.getPeriodFrom().toString()));
        }
        if (Objects.nonNull(internshipDTO.getPeriodTo())) {
            internship.setPeriodTo(LocalDate.parse(internshipDTO.getPeriodTo().toString()));
        }
        internship.setInternshipStatus(internshipDTO.getInternshipStatus());
        internship.setPreInterviewTestList(internshipDTO.getPreInterviewTestList());
        if (Objects.nonNull(internshipDTO.getTechQuesListId())) {
            internship.setTechQuesList(getTechQuestionListById(internshipDTO.getTechQuesListId()));
        }
        internship.setGitHubUrl(internshipDTO.getGitHubUrl());
        internship.setTrelloBoardUrl(internshipDTO.getTrelloBoardUrl());
        internship.setDeployedAppUrl(internshipDTO.getDeployedAppUrl());
        internship.setPresentationUrl(internshipDTO.getPresentationUrl());
        //TODO- review after intergration with candidates list selected by internshipId
//        internship.setCandidates(getCandidateListByIds(internshipDTO.getCandidatesId()));
        return internship;
    }

    private Internship getInternshipByIdOrCreate(String id) {
        if (Objects.isNull(id)) {
            return new Internship();
        }
        return internshipRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "internship not found"));
    }

    @Override
    public InternshipDTO mapToInternshipDTO(Internship internship) {
        InternshipDTO internshipDTO = new InternshipDTO();
        internshipDTO.setId(internship.getId());
        internshipDTO.setProjectName(internship.getProjectName());
        internshipDTO.setCategory(internship.getCategory());
        internshipDTO.setMentorsId(getMentorsIdList(internship.getMentors()));
        internshipDTO.setPeriodFrom(internship.getPeriodFrom());
        internshipDTO.setPeriodTo(internship.getPeriodTo());
        internshipDTO.setInternshipStatus(internship.getInternshipStatus());
        internshipDTO.setPreInterviewTestList(internship.getPreInterviewTestList());
        internshipDTO.setTechQuesListId(getTechQuestionListId(internship.getTechQuesList()));
        internshipDTO.setGitHubUrl(internship.getGitHubUrl());
        internshipDTO.setTrelloBoardUrl(internship.getTrelloBoardUrl());
        internshipDTO.setDeployedAppUrl(internship.getDeployedAppUrl());
        internshipDTO.setPresentationUrl(internship.getPresentationUrl());
        return internshipDTO;
    }

    private String getTechQuestionListId(TechQuestionList techQuestionList) {
        if (Objects.isNull(techQuestionList) || techQuestionList.getId().isEmpty()) {
            return null;
        }
        return techQuestionList.getId();
    }

    private List<String> getMentorsIdList(List<User> mentors) {
        if (Objects.isNull(mentors) || mentors.isEmpty()) {
            return Collections.emptyList();
        }
        return mentors.stream().map(User::getId).collect(Collectors.toList());
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
        return techQuestionListRepository.findById(tqlId)
                .orElseThrow(() -> new IllegalArgumentException("TechQuestion list with id = " + tqlId + " was not found!"));
    }


    @Override
    public Internship getInternship(final String id) {
        Internship internship = internshipRepository.findById(id).get();
        return internship;
    }
}
