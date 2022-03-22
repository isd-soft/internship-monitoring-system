package org.inthergroup.ims.candidateEvaluation.service;

import org.inthergroup.ims.candidateEvaluation.model.TechMark;
import org.springframework.stereotype.Service;

@Service
public interface TechMarkService {

    public TechMark addTechMark(TechMark techMark);

    public void deleteTechMarkById(String id);
}
