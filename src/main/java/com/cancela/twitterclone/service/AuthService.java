package com.cancela.twitterclone.service;

import com.cancela.twitterclone.dto.request.RegisterRequest;
import com.cancela.twitterclone.model.User;
import com.cancela.twitterclone.model.VerificationToken;
import com.cancela.twitterclone.repository.UserRepository;
import com.cancela.twitterclone.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    public User SignUp(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFantasyName(registerRequest.getFantasyName());
        user.setProfilePic(registerRequest.getProfilePic());
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(user.getEmail(), "Activate your Twitter Clone Account", String.format("Thank you for signing up to Twitter Clone!\n\nClick on the following link to activate your account: http://localhost:8080/api/auth/verify/%s", token));

        return user;
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }
}
