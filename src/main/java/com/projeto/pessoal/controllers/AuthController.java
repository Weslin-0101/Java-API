package com.projeto.pessoal.controllers;

import com.projeto.pessoal.data.v1.AuthenticationDTO.CredentialsRequestDTO;
import com.projeto.pessoal.data.v1.AuthenticationDTO.CredentialsResponseDTO;
import com.projeto.pessoal.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping(
            value = "/authenticate"
    )
    public ResponseEntity<CredentialsResponseDTO> auth (
            @RequestBody CredentialsRequestDTO request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
