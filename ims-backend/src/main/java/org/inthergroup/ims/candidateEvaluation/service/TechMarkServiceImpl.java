package org.inthergroup.ims.candidateEvaluation.service;

import org.inthergroup.ims.candidateEvaluation.model.TechMark;
import org.inthergroup.ims.candidateEvaluation.repository.TechMarkRepository;
import org.springframework.stereotype.Service;

@Service
public class TechMarkServiceImpl implements TechMarkService {

    private final TechMarkRepository techMarkRepository;

    public TechMarkServiceImpl(TechMarkRepository techMarkRepository) {
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
}
