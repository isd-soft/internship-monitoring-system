package org.inthergroup.ims.candidate.facade;

import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.candidate.service.CandidateService;
import org.inthergroup.ims.internship.service.InternshipService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CandidateFacade {

    private final InternshipService internshipService;
    private final CandidateService candidateService;

    public CandidateFacade(InternshipService internshipService, CandidateService candidateService) {
        this.internshipService = internshipService;
        this.candidateService = candidateService;
    }

    public CandidateDTO mapToDTO(final Candidate candidate, final CandidateDTO candidateDTO) {
        candidateDTO.setId(candidate.getId());
        candidateDTO.setName(candidate.getName());
        candidateDTO.setSurname(candidate.getSurname());
        candidateDTO.setEmail(candidate.getEmail());
        candidateDTO.setCv(candidate.getCv());
        candidateDTO.setComment(candidate.getComment());
        candidateDTO.setStatus(candidate.getStatus());
        candidateDTO.setInternship(candidate.getInternship().getId());
        return candidateDTO;
    }

    private Candidate mapToEntity(final CandidateDTO candidateDTO) {
        Candidate candidate;

        if (candidateDTO.getId() != null) {
            candidate = candidateService.getById(candidateDTO.getId());
        } else {
            candidate = new Candidate();
        }

        candidate.setName(candidateDTO.getName());
        candidate.setSurname(candidateDTO.getSurname());
        candidate.setEmail(candidateDTO.getEmail());
        candidate.setCv(candidateDTO.getCv());
        candidate.setComment(candidateDTO.getComment());
        candidate.setStatus(candidateDTO.getStatus());
        candidate.setInternship(internshipService.getById(candidateDTO.getInternship()));
        return candidate;
    }

    public List<CandidateDTO> findAll() {
        return candidateService.findAll()
                .stream()
                .map(CandidateDTO::new)
                .collect(Collectors.toList());
    }

    public void delete(final String id) {
        candidateService.delete(id);
    }

    public CandidateDTO getById(final String candidateId) {
        return new CandidateDTO(candidateService.getById(candidateId));
    }

    public void update(CandidateDTO candidateDTO) {
        Candidate updatedCandidate = mapToEntity(candidateDTO);
        candidateService.update(updatedCandidate);
    }

    public void create(final CandidateDTO candidateDTO) {
        Candidate candidate = mapToEntity(candidateDTO);
        candidateService.create(candidate);
    }

    public List<CandidateDTO> getAllCandidatesByInternshipId(String internshipId) {
        return candidateService.getAllCandidatesByInternshipId(internshipId)
                .stream()
                .map(CandidateDTO::new)
                .collect(Collectors.toList());
    }
}
