package com.projeto.pessoal.services;

import com.projeto.pessoal.controllers.AccountController;
import com.projeto.pessoal.data.v1.AccountVO;
import com.projeto.pessoal.exceptions.RequiredObjectsIsNullException;
import com.projeto.pessoal.mapper.ModelMapperAdapter;
import com.projeto.pessoal.mapper.custom.AccountMapper;
import com.projeto.pessoal.model.Account;
import com.projeto.pessoal.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
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
    PagedResourcesAssembler<AccountVO> assembler;

    @Autowired
    AccountMapper mapper;

    private PagedModel<EntityModel<AccountVO>>getEntityModels(Pageable pageable, Page<Account> accountPage) throws Exception {
        var accountVOPage = accountPage.map(p -> ModelMapperAdapter.parseObject(p, AccountVO.class));
        accountVOPage.map(p -> {
            try {
                return p.add(linkTo(methodOn(AccountController.class).findById(p.getId())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Link link = linkTo(methodOn(AccountController.class)
                .findAll(pageable.getPageNumber(),
                        pageable.getPageSize(),
                        "asc")).withSelfRel();

        return assembler.toModel(accountVOPage, link);
    }

    public AccountVO findById(Long id) throws Exception {
        logger.info("Find Account by id");
        var entity = accountRepository.findById(id)
                .orElseThrow(() -> new Exception("No record found for this ID"));

        AccountVO vo = ModelMapperAdapter.parseObject(entity, AccountVO.class);
        vo.add(linkTo(methodOn(AccountController.class).findById(id)).withSelfRel());

        return vo;
    }

    public PagedModel<EntityModel<AccountVO>> findByName(String name, Pageable pageable) throws Exception {
        logger.info("Find Account by name");

        var accountPage = accountRepository.findByName(name, pageable);

        return getEntityModels(pageable, accountPage);
    }

    public PagedModel<EntityModel<AccountVO>> findAll(Pageable pageable) throws Exception {
        logger.info("Returning all Accounts");

        var accountPage = accountRepository.findAll(pageable);

        return getEntityModels(pageable, accountPage);
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

    public void deleteAccount(Long id) throws Exception {
        logger.info("Deleting Account");

        var entity = accountRepository.findById(id)
                .orElseThrow(() -> new Exception("No records found for this ID"));
        accountRepository.delete(entity);
    }
}