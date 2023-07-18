package com.projeto.pessoal.controllers;

import com.projeto.pessoal.model.Account;
import com.projeto.pessoal.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account/v1")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public Account create(@RequestBody Account account) throws Exception {
        return accountService.createAccount(account);
    }
}
