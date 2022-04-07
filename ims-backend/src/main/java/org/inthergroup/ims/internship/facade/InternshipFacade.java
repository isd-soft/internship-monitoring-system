package org.inthergroup.ims.internship.facade;

import org.inthergroup.ims.candidate.facade.CandidateDTO;
import org.inthergroup.ims.candidate.facade.CandidateFacade;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.candidate.model.CandidateStatus;
import org.inthergroup.ims.candidate_evaluation.CandidateEvaluationResponseDTO;
import org.inthergroup.ims.candidate_evaluation.CandidateEvaluationService;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.service.InternshipService;
import org.inthergroup.ims.login.model.User;
import org.inthergroup.ims.login.repository.UserRepository;
import org.inthergroup.ims.preInterwiewTestEvaluation.PreInterviewTestMarkFacade;
import org.inthergroup.ims.preInterwiewTestEvaluation.PreInterviewTestMarkRepository;
import org.inthergroup.ims.techQuestionList.TechQuestionList;
import org.inthergroup.ims.techQuestionList.TechQuestionListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class InternshipFacade {
    private final InternshipService internshipService;
    private final CandidateFacade candidateFacade;
    private final UserRepository userRepository;
    private final TechQuestionListRepository techQuestionListRepository;
    private final PreInterviewTestMarkFacade preInterviewTestMarkFacade;
    private final CandidateEvaluationService candidateEvaluationService;
    private final PreInterviewTestMarkRepository preInterviewTestMarkRepository;

    public InternshipFacade(InternshipService internshipService, CandidateFacade candidateFacade,
                            UserRepository userRepository, TechQuestionListRepository techQuestionListRepository,
                            PreInterviewTestMarkFacade preInterviewTestMarkFacade,
                            CandidateEvaluationService candidateEvaluationService,
                            PreInterviewTestMarkRepository preInterviewTestMarkRepository) {
        this.internshipService = internshipService;
        this.candidateFacade = candidateFacade;
        this.userRepository = userRepository;
        this.techQuestionListRepository = techQuestionListRepository;
        this.preInterviewTestMarkFacade = preInterviewTestMarkFacade;
        this.candidateEvaluationService = candidateEvaluationService;
        this.preInterviewTestMarkRepository = preInterviewTestMarkRepository;
    }

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
            internship.setTechQuestionList(getTechQuestionListById(internshipDTO.getTechQuesListId()));
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
        return internshipService.getById(id);
    }

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
        internshipDTO.setTechQuesListId(getTechQuestionListId(internship.getTechQuestionList()));
        internshipDTO.setGitHubUrl(internship.getGitHubUrl());
        internshipDTO.setTrelloBoardUrl(internship.getTrelloBoardUrl());
        internshipDTO.setDeployedAppUrl(internship.getDeployedAppUrl());
        internshipDTO.setPresentationUrl(internship.getPresentationUrl());
        return internshipDTO;
    }

    private List<User> getUserListByIds(List<String> mentorIdList) {
        if (Objects.isNull(mentorIdList) || mentorIdList.isEmpty()) {
            return Collections.emptyList();
        }
        return userRepository.findAllById(mentorIdList);
    }

    private List<String> getMentorsIdList(List<User> mentors) {
        if (Objects.isNull(mentors) || mentors.isEmpty()) {
            return Collections.emptyList();
        }
        return mentors.stream().map(User::getId).collect(Collectors.toList());
    }

    private String getTechQuestionListId(TechQuestionList techQuestionList) {
        if (Objects.isNull(techQuestionList) || techQuestionList.getId().isEmpty()) {
            return null;
        }
        return techQuestionList.getId();
    }

    private TechQuestionList getTechQuestionListById(String tqlId) {
        return techQuestionListRepository.findById(tqlId)
                .orElseThrow(() ->
                        new IllegalArgumentException("TechQuestion list with id = " + tqlId + " was not found!"));
    }

    public List<InternshipDTO> getAllInternship() {
        return internshipService.getAllInternships().stream()
                .map(this::mapToInternshipDTO)
                .collect(Collectors.toList());
    }

    public InternshipDTO getById(String id) {
        return mapToInternshipDTO(internshipService.getById(id));
    }

    public void create(final InternshipDTO internshipDTO) {
        internshipService.create(mapToInternshipEntity(internshipDTO));
    }

    public void update(String id, InternshipDTO internshipDTO) {
        internshipService.update(id, mapToInternshipEntity(internshipDTO));
    }

    public void delete(final String id) {
        internshipService.delete(id);
    }

    public List<CandidateDTO> getCandidatesByInternshipId(@PathVariable("id") String internshipId) {
        return internshipService.getAllCandidatesByInternshipId(internshipId).stream()
                .map(candidate -> candidateFacade.mapToDTO(candidate, new CandidateDTO()))
                .collect(Collectors.toList());
    }

    public InternshipResultsDTO getInternshipResults(String internshipId) {
        List<CandidateEvaluationResultsDTO> newCandidates = new ArrayList<>();
        List<CandidateEvaluationResultsDTO> acceptedCandidates = new ArrayList<>();
        List<CandidateEvaluationResultsDTO> onHoldCandidates = new ArrayList<>();
        List<CandidateEvaluationResultsDTO> rejectedCandidates = new ArrayList<>();
        List<Candidate> internshipCandidates = internshipService.getAllCandidatesByInternshipId(internshipId);
        Comparator<CandidateEvaluationResultsDTO> compareByResult =
                Comparator.comparingDouble(CandidateEvaluationResultsDTO::getAverageInterviewMark).reversed();
        internshipCandidates.forEach(candidate -> {
            CandidateEvaluationResponseDTO candidateInterviewEvaluation =
                    candidateEvaluationService.getByCandidateId(candidate.getId());
            CandidateEvaluationResultsDTO candidateResults = CandidateEvaluationResultsDTO.builder()
                    .candidateId(candidate.getId())
                    .englishMark(candidateInterviewEvaluation.getEnglishMark())
                    .softSkillMark(candidateInterviewEvaluation.getSoftSkillMark())
                    .practiceMark(candidateInterviewEvaluation.getPracticeMark())
                    .techMark(candidateInterviewEvaluation.getAverageMark())
                    .averageInterviewMark(candidateInterviewEvaluation.getAverageCandidateEvaluation())
                    .averagePreInterviewMark(preInterviewTestMarkRepository.testAvg(candidate.getId()))
                    .testMarks(preInterviewTestMarkFacade.getPreInterviewTestMarksByCandidateId(candidate.getId()))
                    .build();
            CandidateStatus candidateStatus = candidate.getStatus();
            switch (candidateStatus) {
                case NEW:
                    newCandidates.add(candidateResults);
                    break;
                case ACCEPTED:
                    acceptedCandidates.add(candidateResults);
                    break;
                case ON_HOLD:
                    onHoldCandidates.add(candidateResults);
                    break;
                case REJECTED:
                    rejectedCandidates.add(candidateResults);
                    break;
                default:
                    newCandidates.add(candidateResults);
            }
        });
        newCandidates.sort(compareByResult);
        acceptedCandidates.sort(compareByResult);
        onHoldCandidates.sort(compareByResult);
        rejectedCandidates.sort(compareByResult);
        return InternshipResultsDTO.builder()
                .newCandidates(newCandidates)
                .acceptedCandidates(acceptedCandidates)
                .onHoldCandidates(onHoldCandidates)
                .rejectedCandidates(rejectedCandidates)
                .build();
    }
}
