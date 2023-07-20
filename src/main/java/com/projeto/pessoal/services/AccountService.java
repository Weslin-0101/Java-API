package com.projeto.pessoal.services;

import com.projeto.pessoal.model.Account;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class AccountService {

    private static final AtomicLong counter = new AtomicLong();

    private Account AccountMock(int i ) {

        Account account = new Account();
        account.setId((counter.incrementAndGet()));
        account.setName("weslin " + i);
        account.setEmail("weslin@gmail.com " + i);
        account.setPassword("123456 " + i);

        return account;
    }

    public Account createAccount(Account ac) {
        return ac;
    }

    public String findByName(String name) {
        return name;
    }
}