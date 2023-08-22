package com.projeto.pessoal.controllers;

import com.projeto.pessoal.data.v1.AccountDTO.AccountRequestDTO;
import com.projeto.pessoal.data.v1.AccountDTO.AccountResponseDTO;
import com.projeto.pessoal.docs.schemas.BadRequestSchema;
import com.projeto.pessoal.docs.schemas.InternalServerErrorSchema;
import com.projeto.pessoal.docs.schemas.NotFoundSchema;
import com.projeto.pessoal.docs.schemas.UnauthorizedSchema;
import com.projeto.pessoal.model.Account;
import com.projeto.pessoal.services.AccountService;
import com.projeto.pessoal.util.MediaTypeUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/account/v1")
@Tag(name = "Account", description = "Controller endpoint for managing accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping(
            value = "/{id}",
            produces = { MediaTypeUtil.APPLICATION_JSON }
    )
    @Operation(
            summary = "Find account by ID UUID",
            description = "Returns a entity Account by ID UUID",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = { @Content(schema = @Schema(implementation = Account.class)) }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content(schema = @Schema(implementation = UnauthorizedSchema.class)) }),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = { @Content(schema = @Schema(implementation = NotFoundSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
    public ResponseEntity<Account> findById(@PathVariable(value = "id") UUID id) throws Exception {
        var account = accountService.findById(id);
        if (account == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        account.add(linkTo(methodOn(AccountController.class).findById(id)).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @GetMapping(
            value = "/findByName",
            produces = { MediaTypeUtil.APPLICATION_JSON }
    )
    @Operation(
            summary = "Find account by Name",
            description = "Returns a entity Account by Name",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = { @Content(schema = @Schema(implementation = Account.class)) }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content(schema = @Schema(implementation = UnauthorizedSchema.class)) }),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = { @Content(schema = @Schema(implementation = NotFoundSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
    public ResponseEntity<Account> findByName (
            @RequestParam (value = "name") String name
    ) {
        var accountByName = accountService.findByUsername(name);
        accountByName.add(linkTo(methodOn(AccountController.class).findByName(name)).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(accountByName);
    }

    @GetMapping(
            produces = { MediaTypeUtil.APPLICATION_JSON }
    )
    @Operation(
            summary = "Find all accounts",
            description = "Returns a list of entity Accounts",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = { @Content(schema = @Schema(implementation = Account.class)) }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content(schema = @Schema(implementation = UnauthorizedSchema.class)) }),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = { @Content(schema = @Schema(implementation = NotFoundSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
    public ResponseEntity<List<Account>> findAll() {
        List<Account> listAccounts = accountService.findAll();
        if (listAccounts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        listAccounts.forEach(account -> {
            try {
                account.add(linkTo(methodOn(AccountController.class).findById(account.getId())).withSelfRel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return ResponseEntity.status(HttpStatus.OK).body(listAccounts);
    }

    @PostMapping(
            value = "/create",
            produces = { MediaTypeUtil.APPLICATION_JSON }
    )
    @Operation(
            summary = "Create a new account",
            description = "Returns a entity Account",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = { @Content(schema = @Schema(implementation = Account.class)) }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = { @Content(schema = @Schema(implementation = BadRequestSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
    public ResponseEntity<AccountResponseDTO> create(@RequestBody @Valid AccountRequestDTO request) throws Exception {
        var account = accountService.createAccount(request);
        if (account != null) {
            return ResponseEntity.status(HttpStatus.OK).body(account);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping(
            value = "/{email}",
            produces = { MediaTypeUtil.APPLICATION_JSON }
    )
    @Operation(
            summary = "Update a account",
            description = "Returns a new entity Account after update",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = { @Content(schema = @Schema(implementation = Account.class)) }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = { @Content(schema = @Schema(implementation = BadRequestSchema.class)) }),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content(schema = @Schema(implementation = UnauthorizedSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
    public ResponseEntity<Account> update(
            @PathVariable(value = "email") String email,
            @RequestBody @Valid AccountRequestDTO request
    ) throws Exception {
        var accountUpdated = accountService.updateAccount(email, request);
        accountUpdated.add(linkTo(methodOn(AccountController.class).update(email, request)).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(accountUpdated);
    }

    @Operation(
            summary = "Delete a account",
            description = "Delete a entity Account by email",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = { @Content(schema = @Schema(implementation = BadRequestSchema.class)) }),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content(schema = @Schema(implementation = UnauthorizedSchema.class)) }),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = { @Content(schema = @Schema(implementation = NotFoundSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
    @DeleteMapping(
            value = "/{email}",
            produces = { MediaTypeUtil.APPLICATION_JSON }
    )
    public ResponseEntity<?> delete (@PathVariable (value = "email") String email) {
        accountService.deleteAccount(email);
        return ResponseEntity.noContent().build();
    }
}
