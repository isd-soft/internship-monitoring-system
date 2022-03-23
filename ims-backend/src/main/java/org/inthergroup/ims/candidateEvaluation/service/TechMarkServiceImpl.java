package org.inthergroup.ims.candidateEvaluation.service;

import org.inthergroup.ims.candidate.Repository.CandidateRepository;
import org.inthergroup.ims.candidateEvaluation.model.TechMark;
import org.inthergroup.ims.candidateEvaluation.repository.TechMarkRepository;
import org.springframework.stereotype.Service;

@Service
public class TechMarkServiceImpl implements TechMarkService {

    private final CandidateRepository candidateRepository;
    private final TechMarkRepository techMarkRepository;

    public TechMarkServiceImpl(CandidateRepository candidateRepository, TechMarkRepository techMarkRepository) {
        this.candidateRepository = candidateRepository;
        this.techMarkRepository = techMarkRepository;
    }

    @Override
    public TechMark addTechMark(TechMark techMark) {
        return techMarkRepository.save(techMark);
    }

    @Override
    public void deleteTechMarkById(String id) {
    techMarkRepository.deleteById(id);
    }

    @Override

    public Double avg(String id) {
    return techMarkRepository.avg(id);
    }

    @Override
    public TechMark getTechMarkByCandidateId(String id) {
        return techMarkRepository.getById(id);
    }
}
