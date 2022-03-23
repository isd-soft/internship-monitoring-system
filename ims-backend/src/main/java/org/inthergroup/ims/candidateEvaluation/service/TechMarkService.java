package org.inthergroup.ims.candidateEvaluation.service;

import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.candidateEvaluation.model.TechMark;
import org.springframework.stereotype.Service;

@Service
public interface TechMarkService {

    public TechMark addTechMark(TechMark techMark);

    public void deleteTechMarkById(String id);

    public Double avg(String id);

    public TechMark getTechMarkByCandidateId(String id);
}
