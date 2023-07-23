package com.projeto.pessoal.services;

import com.projeto.pessoal.model.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private Account AccountMock(int i) {
        Account account = new Account();
        account.setId((1L));
        account.setName("weslin " + i);
        account.setEmail("weslin@gmail.com " + i);
        account.setPassword("123456");

        return account;
    }

    @Test
    void findById() throws Exception {
        Account ac = AccountMock(0);

        Long id = new AccountService().findById(ac.getId()).getId();

        assertEquals(ac.getId(), id);
    }

    @Test
    void findByName() throws Exception {
        Account ac = AccountMock(0);

        String name = new AccountService().findByName(ac.getName()).getName();

        assertEquals(ac.getName(), name);
    }
}