package com.projeto.pessoal.services;

import com.projeto.pessoal.data.v1.AccountDTO.AccountRequestDTO;
import com.projeto.pessoal.data.v1.AccountDTO.AccountResponseDTO;
import com.projeto.pessoal.exceptions.RequiredObjectsIsNullException;
import com.projeto.pessoal.exceptions.ResourceNotFoundException;
import com.projeto.pessoal.model.Account;
import com.projeto.pessoal.model.Permission;
import com.projeto.pessoal.producers.UserProducer;
import com.projeto.pessoal.repositories.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountService {

    private final Logger logger = Logger.getLogger(AccountService.class.getName());

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    final UserProducer userProducer;

    public Account findById(UUID id) throws Exception {
        logger.info("Find Account by id");

        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No record found for this ID"));
    }

    public Account findByEmail(String email) {
        logger.info("Find Account by email");

        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("No record found for this email: " + email));
    }

    public Account findByUsername(String username) {
        logger.info("Find Account by username");

        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("No record found for this username: " + username));
    }

    public List<Account> findAll() {
        logger.info("Find all Accounts");

        return accountRepository.findAll();
    }

    @Transactional
    public AccountResponseDTO createAccount(AccountRequestDTO request) throws Exception {
        if (request == null) throw new RequiredObjectsIsNullException();

        logger.info("Creating Account");
        var account = Account.builder()
                .name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .permission(Permission.ADMIN)
                .build();
        accountRepository.save(account);
        userProducer.publishMessageEmail(account);

        return AccountResponseDTO.builder()
                .id(account.getId())
                .name(account.getName())
                .username(account.getUsername())
                .email(account.getEmail())
                .password(account.getPassword())
                .build();
    }

    public Account updateAccount(String email, AccountRequestDTO request) throws Exception {
        if (request == null) throw new RequiredObjectsIsNullException();

        logger.info("Updating Account");
        var entity = accountRepository.findByEmail(email)
                        .orElseThrow(() -> new ResourceNotFoundException("No records found for this Email: " + email));
        entity.setName(request.getName());
        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());

        return entity;
    }

    public void deleteAccount(String email) {
        logger.info("Deleting Account");

        var entity = accountRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        accountRepository.delete(entity);
    }
}