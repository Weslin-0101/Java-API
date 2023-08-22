package com.projeto.pessoal.controllers;

import com.projeto.pessoal.data.v1.AuthenticationDTO.CredentialsRequestDTO;
import com.projeto.pessoal.data.v1.AuthenticationDTO.CredentialsResponseDTO;
import com.projeto.pessoal.docs.schemas.BadRequestSchema;
import com.projeto.pessoal.docs.schemas.InternalServerErrorSchema;
import com.projeto.pessoal.services.AuthenticationService;
import com.projeto.pessoal.util.MediaTypeUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/auth/v1")
@Tag(name = "Authentication", description = "Controller endpoint for managing authentication")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping(
            value = "/authenticate",
            produces = { MediaTypeUtil.APPLICATION_JSON }
    )
    @Operation(
            summary = "Authenticate a user and return a JWT token",
            description = "Authenticate a user and return a JWT token",
            tags = { "Authentication" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = { @Content(schema = @Schema(implementation = CredentialsResponseDTO.class)) }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = { @Content(schema = @Schema(implementation = BadRequestSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
    public ResponseEntity<CredentialsResponseDTO> auth (
            @RequestBody CredentialsRequestDTO request
    ) {
        var response = authenticationService.authenticate(request);
        response.add(linkTo(methodOn(AuthController.class).auth(request)).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
