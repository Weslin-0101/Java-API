package com.projeto.pessoal.services;

import com.projeto.pessoal.data.v1.AccountVO;
import com.projeto.pessoal.exceptions.RequiredObjectsIsNullException;
import com.projeto.pessoal.mocks.MockAccount;
import com.projeto.pessoal.model.Account;
import com.projeto.pessoal.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    MockAccount input;

    @InjectMocks
    private AccountService services;

    @Mock
    AccountRepository repository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockAccount();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() throws Exception {
        Account mockAccount = input.mockEntity(1);
        mockAccount.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(mockAccount));
        var result = services.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Name Test 1", result.getName());
        assertEquals("Email Test 1", result.getEmail());
        assertEquals("Password Test 1", result.getPassword());
    }

    @Test
    void createAccount() throws Exception {
        Account mockAccount = input.mockEntity(1);
        mockAccount.setId(1L);

        AccountVO mockAccountVO = input.mockVO(1);
        mockAccountVO.setId(1L);
        when(repository.save(mockAccount)).thenReturn(mockAccount);

        var result = services.createAccount(mockAccountVO);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertEquals("Name Test 1", result.getName());
        assertEquals("Email Test 1", result.getEmail());
        assertEquals("Password Test 1", result.getPassword());
    }

    @Test
    void createWithNullAccount() throws Exception {
        Exception exception = assertThrows(RequiredObjectsIsNullException.class, () -> {
            services.createAccount(null);
        });

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updateAccount() throws Exception {
        Account mockAccount = input.mockEntity(1);
        mockAccount.setId(1L);

        AccountVO mockAccountVO = input.mockVO(1);
        mockAccountVO.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(mockAccount));
        when(repository.save(mockAccount)).thenReturn(mockAccount);

        var result = services.updateAccount(mockAccountVO);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertEquals("Name Test 1", result.getName());
        assertEquals("Email Test 1", result.getEmail());
        assertEquals("Password Test 1", result.getPassword());
    }

    @Test
    void updateWithNullAccount() throws Exception {
        Exception exception = assertThrows(RequiredObjectsIsNullException.class, () -> {
            services.updateAccount(null);
        });

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deleteAccount() throws Exception {
        Account mockAccount = input.mockEntity();
        mockAccount.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(mockAccount));

        services.deleteAccount(1L);
    }
}