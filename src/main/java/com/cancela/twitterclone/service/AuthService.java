package com.cancela.twitterclone.service;

import com.cancela.twitterclone.dto.request.RegisterRequest;
import com.cancela.twitterclone.model.User;
import com.cancela.twitterclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User SignUp(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFantasyName(registerRequest.getFantasyName());
        user.setProfilePic(registerRequest.getProfilePic());
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        return user;
    }
}
