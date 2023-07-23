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

    private Account[] AccountMockArray(int i) {
        Account[] account = new Account[i];

        for (int j = 1; j < i; j++) {
            account[j] = new Account();
            account[j].setId((1L));
            account[j].setName("weslin " + j);
            account[j].setEmail("weslin@gmail.com " + j);
            account[j].setPassword("123456 " + j);
        }

        return account;
    }

    @Test
    void findById() throws Exception {
        Account ac = AccountMock();

        Long id = new AccountService().findById(ac.getId()).getId();

        assertEquals(ac.getId(), id);
    }

    @Test
    void findByName() throws Exception {
        Account ac = AccountMock();

        String name = new AccountService().findByName(ac.getName()).getName();

        assertEquals(ac.getName(), name);
    }

    @Test
    void findAll() throws Exception {
        Account[] ac = AccountMockArray(5);

        String name = new AccountService().findAll().getName();

        assertEquals(ac[2].getName(), name);
    }
}