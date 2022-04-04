package org.inthergroup.ims.preInterwiewTestEvaluation;

import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.candidate.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PreInterviewTestMarkServiceImpl implements PreInterviewTestMarkService {

    private final PreInterviewTestMarkRepository preInterviewTestMarkRepository;
    private final CandidateRepository candidateRepository;

    public PreInterviewTestMarkServiceImpl(PreInterviewTestMarkRepository preInterviewTestMarkRepository, CandidateRepository candidateRepository) {
        this.preInterviewTestMarkRepository = preInterviewTestMarkRepository;
        this.candidateRepository = candidateRepository;
    }

    @Override
    public List<PreInterviewTestMark> getAllPreInterviewTestEvaluation() {
        return preInterviewTestMarkRepository.findAll();
    }

    @Override
    public PreInterviewTestMark getById(String preInterviewTestEvaluationId) {
        return preInterviewTestMarkRepository.findById(preInterviewTestEvaluationId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Could not find any Pre-Interview test evaluation" +
                                " for the provided ID[%s]!", preInterviewTestEvaluationId)));
    }

    @Override
    public List<PreInterviewTestMark> getPreInterviewTestMarksByCandidateId(String candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Could not find any Candidate for the provided ID[%s]!",
                        candidateId)));
        List<PreInterviewTest> internshipPreInterviewTestList =
                Optional.ofNullable(candidate.getInternship().getPreInterviewTestList()).orElseThrow(()->
                new EntityNotFoundException(String.format("Could not find any Pre-Interview Test List" +
                                " for the provided Internship [%s]!",
                        candidate.getInternship().getProjectName())));
        List<PreInterviewTestMark> preInterviewTestMarkList = new ArrayList<>();
        internshipPreInterviewTestList.forEach(preInterviewTest -> {
            Optional<PreInterviewTestMark> preInterviewTestMarkOptional =
                    preInterviewTestMarkRepository.getByCandidateIdAndPreInterviewTestName(preInterviewTest.name(), candidateId);
            if(preInterviewTestMarkOptional.isPresent()){
                preInterviewTestMarkList.add(preInterviewTestMarkOptional.get());
            } else {
                preInterviewTestMarkList.add(createNewPreInterviewTestMark(candidate, preInterviewTest));
            }
        });

        return preInterviewTestMarkList;
    }

    private PreInterviewTestMark createNewPreInterviewTestMark(Candidate candidate, PreInterviewTest preInterviewTest) {
        PreInterviewTestMark preInterviewTestMark = new PreInterviewTestMark();
        preInterviewTestMark.setCandidate(candidate);
        preInterviewTestMark.setPreInterviewTestName(preInterviewTest);
        return preInterviewTestMarkRepository.save(preInterviewTestMark);
    }


}
