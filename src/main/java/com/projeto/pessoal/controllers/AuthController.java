package com.projeto.pessoal.controllers;

import com.projeto.pessoal.data.v1.AuthenticationDTO.CredentialsRequestDTO;
import com.projeto.pessoal.data.v1.AuthenticationDTO.CredentialsResponseDTO;
import com.projeto.pessoal.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/v1")
@Tag(name = "Authentication", description = "Controller endpoint for managing authentication")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/authenticate")
    @Operation(summary = "Authenticate a user and return a JWT token")
    public ResponseEntity<CredentialsResponseDTO> auth (
            @RequestBody CredentialsRequestDTO request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
