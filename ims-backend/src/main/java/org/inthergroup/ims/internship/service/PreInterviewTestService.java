package org.inthergroup.ims.internship.service;

import org.inthergroup.ims.internship.controller.PreInterviewTestDTO;
import org.inthergroup.ims.internship.model.PreInterviewTest;

import java.util.List;

public interface PreInterviewTestService {
    void save(PreInterviewTestDTO preInterviewTest);
    PreInterviewTest toPreInterviewTest(PreInterviewTestDTO preInterviewTestDTO);
    List<PreInterviewTest> toPreInterviewTestList(List<PreInterviewTestDTO> preInterviewTestDTOList);
}
