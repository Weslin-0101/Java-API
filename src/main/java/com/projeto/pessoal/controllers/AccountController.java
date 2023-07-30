package com.projeto.pessoal.controllers;

import com.projeto.pessoal.data.v1.AccountVO;
import com.projeto.pessoal.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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
    public ResponseEntity<PagedModel<EntityModel<AccountVO>>> findByName (
            @PathVariable(value = "name") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) throws Exception {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        return ResponseEntity.ok(accountService.findByName(name, pageable));
    }

    @GetMapping()
    public ResponseEntity<PagedModel<EntityModel<AccountVO>>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) throws Exception {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        var pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        return ResponseEntity.ok(accountService.findAll(pageable));
    }

    @PostMapping(value = "/create")
    public AccountVO create(@RequestBody AccountVO account) throws Exception {
        return accountService.createAccount(account);
    }

    @PutMapping()
    public AccountVO update(@RequestBody AccountVO account) throws Exception {
        return accountService.updateAccount(account);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete (@PathVariable (value = "id") Long id) throws Exception {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
