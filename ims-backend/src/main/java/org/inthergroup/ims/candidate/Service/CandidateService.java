package org.inthergroup.ims.candidate.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.inthergroup.ims.candidate.Controller.CandidateDTO;
import org.inthergroup.ims.candidate.Repository.CandidateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public interface CandidateService {



    public List<CandidateDTO> findAll();

    public CandidateDTO get(final String id);

    public String create(final CandidateDTO candidateDTO);

    public void update(final String id, final CandidateDTO candidateDTO);

    public void delete(final String id);


}
