package org.inthergroup.ims.internship.service;

import org.inthergroup.ims.candidate.model.Candidate;
import org.inthergroup.ims.candidate.repository.CandidateRepository;
import org.inthergroup.ims.internship.facade.InternshipDTO;
import org.inthergroup.ims.internship.model.Internship;
import org.inthergroup.ims.internship.repository.InternshipRepository;
import org.inthergroup.ims.login.model.User;
import org.inthergroup.ims.login.repository.UserRepository;
import org.inthergroup.ims.techQuestionList.TechQuestionList;
import org.inthergroup.ims.techQuestionList.TechQuestionListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepository;

    public InternshipServiceImpl(InternshipRepository internshipRepository) {
        this.internshipRepository = internshipRepository;
    }

    @Override
    public List<Internship> getAllInternships() {
        List<Internship> internships = internshipRepository.findAll();
        if (internships.isEmpty()) {
            return Collections.emptyList();
        }
        return internships;
    }

    @Override
    public Internship getById(final String id) {
        return internshipRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "internship not found"));
    }

    @Override
    public void create(final Internship internship) {
        internshipRepository.save(internship);
    }

    @Override
    public void update(String id, Internship internship) {
        internshipRepository.save(internship);
    }

    @Override
    public void delete(String id) {
        internshipRepository.deleteById(id);
    }


    @Override
    public List<Candidate> getAllCandidatesByInternshipId(String internshipId) {
        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new IllegalArgumentException("Internship with id" + internshipId + "was not found!"));
        return internship.getCandidates();
    }
}