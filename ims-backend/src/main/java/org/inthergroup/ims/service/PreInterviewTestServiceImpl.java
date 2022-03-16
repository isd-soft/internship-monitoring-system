package org.inthergroup.ims.service;

import org.inthergroup.ims.internship.controller.PreInterviewTestDTO;
import org.inthergroup.ims.internship.repository.PreInterviewRepository;

public class PreInterviewTestServiceImpl implements PreInterviewTestService{

    private final PreInterviewRepository preInterviewRepository;

    public PreInterviewTestServiceImpl(PreInterviewRepository preInterviewRepository) {
        this.preInterviewRepository = preInterviewRepository;
    }


    @Override
    public void save(PreInterviewTestDTO preInterviewTest) {

    }
}
