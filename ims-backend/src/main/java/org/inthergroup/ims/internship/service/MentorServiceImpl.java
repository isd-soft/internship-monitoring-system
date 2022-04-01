package org.inthergroup.ims.internship.service;

import org.inthergroup.ims.login.model.User;
import org.inthergroup.ims.login.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorServiceImpl implements MentorService{

    private final UserRepository userRepository;

    public MentorServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllMentors() {
        return userRepository.findAll();
    }
}
