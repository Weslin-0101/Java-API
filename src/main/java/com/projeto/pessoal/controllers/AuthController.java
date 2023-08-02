package com.projeto.pessoal.controllers;

import com.projeto.pessoal.data.v1.security.AccountCredentialsVO;
import com.projeto.pessoal.repositories.UserRepository;
import com.projeto.pessoal.securityJwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @SuppressWarnings("rawtypes")
    @PostMapping(
            value = "/signing",
            produces = { "application/json" }
    )
    public ResponseEntity signIn(@RequestBody AccountCredentialsVO data) {
        try {
            var email = data.getEmail();
            var password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            var user = userRepository.findByUsername(email);

            var token = "";

            if (user != null) {
                token = jwtTokenProvider.createAccessToken(email, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Email " + email + " not found");
            }

            Map<Object, Object> model = new HashMap<>();
            model.put("email", email);
            model.put("token", token);

            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }
}
