package com.cancela.twitterclone.controller;

import com.cancela.twitterclone.dto.request.RegisterRequest;
import com.cancela.twitterclone.dto.response.GenericResponse;
import com.cancela.twitterclone.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
