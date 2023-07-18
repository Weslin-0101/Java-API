package com.projeto.pessoal.services;

import com.projeto.pessoal.model.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private Account AccountMock() {
        Account account = new Account();
        account.setId((1L));
        account.setName("weslin");
        account.setEmail("weslin@gmail.com");
        account.setPassword("123456");

        return account;
    }

    @Test
    void createAccount() {
        Account ac = AccountMock();

        Account createdAccount = new AccountService().createAccount(ac);

        assertEquals(ac, createdAccount);
        assertEquals(ac.getEmail(), createdAccount.getEmail());
        assertEquals(ac.getName(), createdAccount.getName());
        assertEquals(ac.getPassword(), createdAccount.getPassword());
    }
}