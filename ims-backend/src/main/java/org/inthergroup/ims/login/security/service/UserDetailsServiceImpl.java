package org.inthergroup.ims.login.security.service;

import org.inthergroup.ims.login.exception.EmailNotFoundException;
import org.inthergroup.ims.login.model.User;
import org.inthergroup.ims.login.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws EmailNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("User with email:" + email + "not found."));

        return UserDetailsImpl.build(user);
    }

    public Optional<User> findById(String id){
        return this.userRepository.findById(id);
    }


}