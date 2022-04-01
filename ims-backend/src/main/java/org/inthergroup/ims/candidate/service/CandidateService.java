package org.inthergroup.ims.candidate.service;
import java.util.List;

import org.inthergroup.ims.candidate.controller.CandidateDTO;
import org.inthergroup.ims.candidate.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface CandidateService {

    List<CandidateDTO> getAllCandidatesByInternshipId(String internshipId);

    public List<CandidateDTO> findAll();

    public CandidateDTO get(final String id);

    public String create(final CandidateDTO candidateDTO);

    public void update(final String id, final CandidateDTO candidateDTO);

    public void delete(final String id);

    public Candidate mapToEntity(final CandidateDTO candidateDTO, final Candidate candidate);
}

