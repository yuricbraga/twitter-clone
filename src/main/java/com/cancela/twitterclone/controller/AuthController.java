package com.cancela.twitterclone.controller;

import com.cancela.twitterclone.dto.request.LoginRequest;
import com.cancela.twitterclone.dto.request.RegisterRequest;
import com.cancela.twitterclone.dto.response.GenericResponse;
import com.cancela.twitterclone.dto.response.LoginResponse;
import com.cancela.twitterclone.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<GenericResponse> SignUp(@RequestBody RegisterRequest registerRequest){
        authService.SignUp(registerRequest);

        return new ResponseEntity<>(new GenericResponse("New user added"), OK);
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity<GenericResponse> VerifyAccount(@PathVariable String token){
        authService.verify(token);

        return new ResponseEntity<>(new GenericResponse("User verified"), OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> Login(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(authService.login(loginRequest), OK);
    }
}
