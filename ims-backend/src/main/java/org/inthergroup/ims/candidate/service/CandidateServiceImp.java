package org.inthergroup.ims.candidate.service;
import java.util.List;
import java.util.stream.Collectors;
import org.inthergroup.ims.candidate.controller.CandidateDTO;
import org.inthergroup.ims.candidate.repository.CandidateRepository;
import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.candidate.repository.CandidateRepository;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.repository.InternshipRepository;
import org.inthergroup.ims.internship.service.InternshipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImp implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final InternshipService internshipService;

    public CandidateServiceImp(CandidateRepository candidateRepository, InternshipService internsipService) {
        this.candidateRepository = candidateRepository;
        this.internshipService = internsipService;
    }

    public List<CandidateDTO> findAll() {
        return candidateRepository.findAll()
                .stream()
                .map(candidate -> mapToDTO(candidate, new CandidateDTO()))
                .collect(Collectors.toList());
    }


    @Override
    public void delete(final String id) {
        candidateRepository.deleteById(id);
    }

    public CandidateDTO get(final String id) {
        return candidateRepository.findById(id)
                .map(candidate -> mapToDTO(candidate, new CandidateDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public void update(final String id, final CandidateDTO candidateDTO) {
        final Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(candidateDTO, candidate);
        candidateRepository.save(candidate);
    }

    public String create(final CandidateDTO candidateDTO) {
        final Candidate candidate = new Candidate();
        mapToEntity(candidateDTO, candidate);
        return candidateRepository.save(candidate).getId();
    }

    @Override
    public List<Candidate> getAllCandidatesByInternshipId(String internshipId) {
        List<Candidate> candidates = candidateRepository.getCandidatesByInternshipId(internshipId);
        return candidates;

    }

    private CandidateDTO mapToDTO(final Candidate candidate, final CandidateDTO candidateDTO) {
        candidateDTO.setId(candidate.getId());
        candidateDTO.setName(candidate.getName());
        candidateDTO.setSurname(candidate.getSurname());
        candidateDTO.setEmail(candidate.getEmail());
        candidateDTO.setCv(candidate.getCv());
        candidateDTO.setComment(candidate.getComment());
        candidateDTO.setStatus(candidate.getStatus());

        candidateDTO.setInternship(internshipService.getAllInternships().get(0).getId());

//        candidateDTO.setInternship(candidate.getInternship());
        return candidateDTO;
    }

    private Candidate mapToEntity(final CandidateDTO candidateDTO, final Candidate candidate) {
        candidate.setName(candidateDTO.getName());
        candidate.setSurname(candidateDTO.getSurname());
        candidate.setEmail(candidateDTO.getEmail());
        candidate.setCv(candidateDTO.getCv());
        candidate.setComment(candidateDTO.getComment());
        candidate.setStatus(candidateDTO.getStatus());
        candidate.setInternship(internshipService.getAllInternships().get(0));
        return candidate;
    }
}
