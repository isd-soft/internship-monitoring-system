package org.inthergroup.ims.internship.service;

import org.inthergroup.ims.internship.controller.PreInterviewTestDTO;
import org.inthergroup.ims.internship.model.PreInterviewTest;
import org.inthergroup.ims.internship.repository.PreInterviewRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreInterviewTestServiceImpl implements PreInterviewTestService{

    private final PreInterviewRepository preInterviewRepository;

    public PreInterviewTestServiceImpl(PreInterviewRepository preInterviewRepository) {
        this.preInterviewRepository = preInterviewRepository;
    }


    @Override
    public void save(PreInterviewTestDTO preInterviewTestDTO) {
        preInterviewRepository.save(toPreInterviewTest(preInterviewTestDTO));
    }

    @Override
    public PreInterviewTest toPreInterviewTest(PreInterviewTestDTO preInterviewTestDTO) {
        return new PreInterviewTest(preInterviewTestDTO.getId(), preInterviewTestDTO.getPreInterviewTestName());
    }

    @Override
    public List<PreInterviewTest> toPreInterviewTestList(List<PreInterviewTestDTO> preInterviewTestDTOList) {
        List<PreInterviewTest> preInterviewTestList = new ArrayList<>();
        preInterviewTestDTOList.forEach(preInterviewTestDTO ->
                preInterviewTestList.add(toPreInterviewTest(preInterviewTestDTO)));
        return preInterviewTestList;
    }
}
