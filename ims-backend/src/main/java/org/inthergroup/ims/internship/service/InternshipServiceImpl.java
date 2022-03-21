package org.inthergroup.ims.internship.service;

import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.controller.InternshipDTO;
import org.inthergroup.ims.internship.repository.InternshipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepository;
    private final PreInterviewTestService preInterviewTestService;

    public InternshipServiceImpl(InternshipRepository internshipRepository, PreInterviewTestService preInterviewTestService) {
        this.internshipRepository = internshipRepository;
        this.preInterviewTestService = preInterviewTestService;
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
        internship.setMentors(internshipDTO.getMentors());
        internship.setPeriodFrom(internshipDTO.getPeriodFrom());
        internship.setPeriodTo(internshipDTO.getPeriodTo());
        internship.setInternshipStatus(internshipDTO.getInternshipStatus());
        internship.setPreInterviewTestList(internshipDTO.getPreInterviewTestList());
        internship.setTechQuesListName(internshipDTO.getTechQuesListName());
        internship.setGitHubUrl(internshipDTO.getGitHubUrl());
        internship.setTrelloBoardUrl(internshipDTO.getTrelloBoardUrl());
        internship.setDeployedAppUrl(internshipDTO.getDeployedAppUrl());
        internship.setPresentationUrl(internshipDTO.getPresentationUrl());
        return internship;
    }
}
