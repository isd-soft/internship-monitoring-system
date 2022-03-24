package org.inthergroup.ims.candidate.service;

import java.util.List;

import org.inthergroup.ims.candidate.controller.CandidateDTO;
import org.springframework.stereotype.Service;


@Service
public interface CandidateService {



    public List<CandidateDTO> findAll();

    public CandidateDTO get(final String id);

    public String create(final CandidateDTO candidateDTO);

    public void update(final String id, final CandidateDTO candidateDTO);

    public void delete(final String id);


}
