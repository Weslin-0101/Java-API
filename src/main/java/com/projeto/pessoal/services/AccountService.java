package com.projeto.pessoal.services;

import com.projeto.pessoal.controllers.AccountController;
import com.projeto.pessoal.data.v1.AccountVO;
import com.projeto.pessoal.mapper.ModelMapperAdapter;
import com.projeto.pessoal.mapper.custom.AccountMapper;
import com.projeto.pessoal.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AccountService {

    private static final AtomicLong counter = new AtomicLong();

    @Autowired
    AccountMapper mapper;

    private Account AccountMock(int i) {

        Account account = new Account();
        account.setId((counter.incrementAndGet()));
        account.setName("weslin " + i);
        account.setEmail("weslin@gmail.com " + i);
        account.setPassword("123456 " + i);

        return account;
    }

    public AccountVO findById(Long id) throws Exception {
        var entity = AccountMock(0);

        AccountVO vo = ModelMapperAdapter.parseObject(entity, AccountVO.class);
        vo.add(linkTo(methodOn(AccountController.class).findById(id)).withSelfRel());

        return vo;
    }

    public AccountVO findByName(String name) throws Exception {
        var entity = AccountMock(0);

        AccountVO vo = ModelMapperAdapter.parseObject(entity, AccountVO.class);
        vo.add(linkTo(methodOn(AccountController.class).findByName(name)).withSelfRel());

        return vo;
    }
}