package com.projeto.pessoal.services;

import com.projeto.pessoal.controllers.AccountController;
import com.projeto.pessoal.data.v1.AccountVO;
import com.projeto.pessoal.mapper.ModelMapperAdapter;
import com.projeto.pessoal.mapper.custom.AccountMapper;
import com.projeto.pessoal.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AccountService {

    @Autowired
    AccountMapper mapper;

    public Account AccountMock() {

        Account account = new Account();
        account.setId((1L));
        account.setName("weslin");
        account.setEmail("weslin@gmail.com");
        account.setPassword("123456");

        return account;
    }

    public Account[] AccountMockArray(int i) {
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

    public AccountVO findById(Long id) throws Exception {
        var entity = AccountMock();

        AccountVO vo = ModelMapperAdapter.parseObject(entity, AccountVO.class);
        vo.add(linkTo(methodOn(AccountController.class).findById(id)).withSelfRel());

        return vo;
    }

    public AccountVO findByName(String name) throws Exception {
        var entity = AccountMock();

        AccountVO vo = ModelMapperAdapter.parseObject(entity, AccountVO.class);
        vo.add(linkTo(methodOn(AccountController.class).findByName(name)).withSelfRel());

        return vo;
    }

    public AccountVO findAll() throws Exception {
        var entity = AccountMockArray(5)[2];

        AccountVO vo = ModelMapperAdapter.parseObject(entity, AccountVO.class);
        vo.add(linkTo(methodOn(AccountController.class).findAll()).withSelfRel());

        return vo;
    }
}