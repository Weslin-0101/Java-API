package com.projeto.pessoal.controllers;

import com.projeto.pessoal.data.v1.AccountDTO.AccountRequestDTO;
import com.projeto.pessoal.data.v1.AccountDTO.AccountResponseDTO;
import com.projeto.pessoal.model.Account;
import com.projeto.pessoal.services.AccountService;
import com.projeto.pessoal.util.MediaTypeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/account/v1")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping(
            value = "/{id}",
            produces = { MediaTypeUtil.APPLICATION_JSON }
    )
    public Account findById(@PathVariable(value = "id") UUID id) throws Exception {
        return accountService.findById(id);
    }

    @GetMapping(
            value = "/findByName/{name}",
            produces = { MediaTypeUtil.APPLICATION_JSON }
    )
    public ResponseEntity<Account> findByName (
            @PathVariable(value = "name") String name
    ) {
        return ResponseEntity.ok(accountService.findByUsername(name));
    }

    @GetMapping(
            produces = { MediaTypeUtil.APPLICATION_JSON }
    )
    public ResponseEntity<List<Account>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @PostMapping(
            value = "/create",
            produces = { MediaTypeUtil.APPLICATION_JSON }
    )
    public ResponseEntity<AccountResponseDTO> create(@RequestBody AccountRequestDTO request) throws Exception {
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    @PutMapping(
            value = "/{email}",
            produces = { MediaTypeUtil.APPLICATION_JSON }
    )
    public ResponseEntity<Account> update(
            @PathVariable(value = "email") String email,
            @RequestBody AccountRequestDTO request
    ) throws Exception {
        return ResponseEntity.ok(accountService.updateAccount(email, request));
    }

    @DeleteMapping(
            value = "/{email}",
            produces = { MediaTypeUtil.APPLICATION_JSON }
    )
    public ResponseEntity<?> delete (@PathVariable (value = "email") String email) {
        accountService.deleteAccount(email);
        return ResponseEntity.noContent().build();
    }
}
