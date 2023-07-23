package com.projeto.pessoal.mocks;

import com.projeto.pessoal.data.v1.AccountVO;
import com.projeto.pessoal.model.Account;

import java.util.ArrayList;
import java.util.List;

public class MockAccount {

    public Account mockEntity() {
        return mockEntity(0);
    }

    public AccountVO mockVO() {
        return mockVO(0);
    }

    public List<Account> mockEntityList() {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            accounts.add(mockEntity(i));
        }

        return accounts;
    }

    public List<AccountVO> mockVOList() {
        List<AccountVO> accounts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            accounts.add(mockVO(i));
        }

        return accounts;
    }

    public Account mockEntity(Integer number) {
        Account account = new Account();
        account.setId((long) number);
        account.setName("Name Test " + number);
        account.setEmail("Email Test " + number);
        account.setPassword("Password Test " + number);

        return account;
    }

    public AccountVO mockVO(Integer number) {
        AccountVO account = new AccountVO();
        account.setId((long) number);
        account.setName("Name Test " + number);
        account.setEmail("Email Test " + number);
        account.setPassword("Password Test " + number);

        return account;
    }
}
