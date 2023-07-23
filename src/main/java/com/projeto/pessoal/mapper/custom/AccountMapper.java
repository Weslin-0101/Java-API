package com.projeto.pessoal.mapper.custom;

import com.projeto.pessoal.data.v2.AccountV2;
import com.projeto.pessoal.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

    public AccountV2 convertEntityToVO(Account account) {
        AccountV2 vo = new AccountV2();
        vo.setId(account.getId());
        vo.setName(account.getName());
        vo.setEmail(account.getEmail());
        vo.setPassword(account.getPassword());

        return vo;
    }

    public Account convertVOtoEntity(AccountV2 account) {
        Account entity = new Account();
        entity.setId(account.getId());
        entity.setName(account.getName());
        entity.setEmail(account.getEmail());
        entity.setPassword(account.getPassword());

        return entity;
    }
}
