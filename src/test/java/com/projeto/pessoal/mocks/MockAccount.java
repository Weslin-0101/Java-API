package com.projeto.pessoal.mocks;

import com.projeto.pessoal.data.v1.AccountDTO.AccountRequestDTO;
import com.projeto.pessoal.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MockAccount {
    public List<Account> mockEntityList() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(UUID.randomUUID(), "test_name_1", "test_email_1", "test_password_1"));
        accounts.add(new Account(UUID.randomUUID(), "test_name_2", "test_email_2", "test_password_2"));

        return accounts;
    }

    public Account mockEntity() {
        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setName("test_name");
        account.setUsername("test_username");
        account.setEmail("test_email@gmail.com");
        account.setPassword("test_password");

        return account;
    }
}
