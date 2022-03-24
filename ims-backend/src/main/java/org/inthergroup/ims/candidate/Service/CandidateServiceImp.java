package org.inthergroup.ims.candidate.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.inthergroup.ims.candidate.Controller.CandidateDTO;
import org.inthergroup.ims.candidate.Repository.CandidateRepository;
import org.inthergroup.ims.candidate.model.Candidate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CandidateServiceImp implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImp(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<CandidateDTO> findAll() {
        return candidateRepository.findAll()
                .stream()
                .map(candidate -> mapToDTO(candidate, new CandidateDTO()))
                .collect(Collectors.toList());
    }

    public CandidateDTO get(final String id) {
        return candidateRepository.findById(id)
                .map(candidate -> mapToDTO(candidate, new CandidateDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(final CandidateDTO candidateDTO) {
        final Candidate candidate = new Candidate();
        mapToEntity(candidateDTO, candidate);
        return candidateRepository.save(candidate).getId();
    }

    public void update(final String id, final CandidateDTO candidateDTO) {
        final Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(candidateDTO, candidate);
        candidateRepository.save(candidate);
    }

    public void delete(final String id) {
        candidateRepository.deleteById(id);
    }

    private CandidateDTO mapToDTO(final Candidate candidate, final CandidateDTO candidateDTO) {
        candidateDTO.setId(candidate.getId());
        candidateDTO.setName(candidate.getName());
        candidateDTO.setSurname(candidate.getSurname());
        candidateDTO.setEmail(candidate.getEmail());
        candidateDTO.setCv(candidate.getCv());
        candidateDTO.setComment(candidate.getComment());
        candidateDTO.setStatus(candidate.getStatus());
        return candidateDTO;
    }

    private Candidate mapToEntity(final CandidateDTO candidateDTO, final Candidate candidate) {
        candidate.setName(candidateDTO.getName());
        candidate.setSurname(candidateDTO.getSurname());
        candidate.setEmail(candidateDTO.getEmail());
        candidate.setCv(candidateDTO.getCv());
        candidate.setComment(candidateDTO.getComment());
        candidate.setStatus(candidateDTO.getStatus());
        return candidate;
    }

}
