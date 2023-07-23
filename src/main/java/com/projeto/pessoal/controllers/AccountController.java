package com.projeto.pessoal.controllers;

import com.projeto.pessoal.data.v1.AccountVO;
import com.projeto.pessoal.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account/v1")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public AccountVO findById(@PathVariable(value = "id") Long id) throws Exception {
        return accountService.findById(id);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<AccountVO> findByName (
            @PathVariable(value = "name") String name
    ) throws Exception {
        return ResponseEntity.ok(accountService.findByName(name));
    }

    @GetMapping()
    public ResponseEntity<AccountVO> findAll() throws Exception {
        return ResponseEntity.ok(accountService.findAll().getBody());
    }

    @PostMapping(value = "/create")
    public AccountVO create(@RequestBody AccountVO account) throws Exception {
        return accountService.createAccount(account);
    }

    @PutMapping("/account")
    public AccountVO update(@RequestBody AccountVO account) throws Exception {
        return accountService.updateAccount(account);
    }
}
