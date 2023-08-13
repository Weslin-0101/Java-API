package com.projeto.pessoal.services;

import com.projeto.pessoal.data.v1.AuthenticationDTO.CredentialsRequestDTO;
import com.projeto.pessoal.data.v1.AuthenticationDTO.CredentialsResponseDTO;
import com.projeto.pessoal.repositories.AccountRepository;
import com.projeto.pessoal.securityJwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public CredentialsResponseDTO authenticate(CredentialsRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var account = accountRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtTokenProvider.generateToken(account);
        return CredentialsResponseDTO.builder()
                .email(account.getEmail())
                .accessToken(jwtToken)
                .build();
    }
}
