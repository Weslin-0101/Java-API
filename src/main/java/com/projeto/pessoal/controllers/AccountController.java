package com.projeto.pessoal.controllers;

import com.projeto.pessoal.data.v1.AccountDTO.AccountRequestDTO;
import com.projeto.pessoal.data.v1.AccountDTO.AccountResponseDTO;
import com.projeto.pessoal.model.Account;
import com.projeto.pessoal.services.AccountService;
import com.projeto.pessoal.util.MediaTypeUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = Account.class))
                    }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public Account findById(@PathVariable(value = "id") UUID id) throws Exception {
        return accountService.findById(id);
    }

    @GetMapping(
            value = "/findByName/{name}",
            produces = { MediaTypeUtil.APPLICATION_JSON }
    )
    @Operation(
            summary = "Find account by Name",
            description = "Returns a entity Account by Name",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = Account.class))
                    }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Account> findByName (
            @PathVariable(value = "name") String name
    ) {
        return ResponseEntity.ok(accountService.findByUsername(name));
    }

    @GetMapping(
            produces = { MediaTypeUtil.APPLICATION_JSON }
    )
    @Operation(
            summary = "Find all accounts",
            description = "Returns a list of entity Accounts",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = Account.class))
                    }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<List<Account>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
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
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = Account.class))
                    }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<AccountResponseDTO> create(@RequestBody AccountRequestDTO request) throws Exception {
        return ResponseEntity.ok(accountService.createAccount(request));
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
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = Account.class))
                    }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Account> update(
            @PathVariable(value = "email") String email,
            @RequestBody AccountRequestDTO request
    ) throws Exception {
        return ResponseEntity.ok(accountService.updateAccount(email, request));
    }

    @Operation(
            summary = "Delete a account",
            description = "Delete a entity Account by email",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
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
