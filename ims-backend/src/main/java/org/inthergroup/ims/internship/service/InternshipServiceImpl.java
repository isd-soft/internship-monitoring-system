package org.inthergroup.ims.internship.service;

import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.controller.InternshipDTO;
import org.inthergroup.ims.internship.repository.InternshipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternshipServiceImpl implements InternshipService{

    private final InternshipRepository internshipRepository;
    private final PreInterviewTestService preInterviewTestService;

    public InternshipServiceImpl(InternshipRepository internshipRepository,
                                 PreInterviewTestService preInterviewTestService) {
        this.internshipRepository = internshipRepository;
        this.preInterviewTestService = preInterviewTestService;
    }


    @Override
    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    @Override
    public void save(InternshipDTO internship) {
        internshipRepository.save(toInternship(internship));

    }

   @Override
    public Internship toInternship(InternshipDTO internshipDTO) {
        return new Internship(internshipDTO.getId(), internshipDTO.getProjectName(), internshipDTO.getCategory(),
                internshipDTO.getMentors(), internshipDTO.getPeriodFrom(), internshipDTO.getPeriodTo(),
                internshipDTO.getInternshipStatus(),
                preInterviewTestService.toPreInterviewTestList(internshipDTO.getPreInterviewTestList()),
                internshipDTO.getTechQuesListName(), internshipDTO.getGitHubUrl(), internshipDTO.getTrelloBoardUrl(),
                internshipDTO.getDeployedAppUrl(), internshipDTO.getPresentationUrl());
    }
}
