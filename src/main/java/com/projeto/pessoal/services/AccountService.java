package com.projeto.pessoal.services;

import com.projeto.pessoal.controllers.AccountController;
import com.projeto.pessoal.data.v1.AccountVO;
import com.projeto.pessoal.exceptions.RequiredObjectsIsNullException;
import com.projeto.pessoal.mapper.ModelMapperAdapter;
import com.projeto.pessoal.mapper.custom.AccountMapper;
import com.projeto.pessoal.model.Account;
import com.projeto.pessoal.repositories.AccountRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AccountService {

    private final Logger logger = Logger.getLogger(AccountService.class.getName());

    @Autowired
    AccountRepository accountRepository;

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
        logger.info("Find Account by id");
        var entity = accountRepository.findById(id)
                .orElseThrow(() -> new Exception("No record found for this ID"));

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

    public ResponseEntity<AccountVO> findAll() throws Exception {
        var entity = AccountMockArray(5)[2];;

        AccountVO vo = ModelMapperAdapter.parseObject(entity, AccountVO.class);
        vo.add(linkTo(methodOn(AccountController.class).findAll()).withSelfRel());

        return ResponseEntity.ok(vo);
    }

    public AccountVO createAccount(AccountVO account) throws Exception {
        if (account == null) throw new RequiredObjectsIsNullException();

        logger.info("Creating Account");
        var entity = ModelMapperAdapter.parseObject(account, Account.class);
        var vo = ModelMapperAdapter.parseObject(accountRepository.save(entity), AccountVO.class);
        vo.add(linkTo(methodOn(AccountController.class).findById(vo.getId())).withSelfRel());

        return vo;
    }

    public AccountVO updateAccount(AccountVO account) throws Exception {
        if (account == null) throw new RequiredObjectsIsNullException();

        logger.info("Updating Account");
        var entity = accountRepository.findById(account.getId())
                        .orElseThrow(() -> new Exception("No records found for this ID"));
        entity.setName(account.getName());
        entity.setEmail(account.getEmail());
        entity.setPassword(account.getPassword());

        var vo = ModelMapperAdapter.parseObject(accountRepository.save(entity), AccountVO.class);
        vo.add(linkTo(methodOn(AccountController.class).findById(vo.getId())).withSelfRel());

        return vo;
    }
}