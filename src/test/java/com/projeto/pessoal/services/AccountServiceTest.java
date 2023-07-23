package com.projeto.pessoal.services;

import com.projeto.pessoal.data.v1.AccountVO;
import com.projeto.pessoal.mocks.MockAccount;
import com.projeto.pessoal.model.Account;
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

    @Mock
    private AccountService services;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockAccount();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() throws Exception {
        AccountVO mockAccountVO = input.mockVO(1);
        mockAccountVO.setId(1L);

        when(services.findById(1L)).thenReturn(mockAccountVO);
        var result = services.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Name Test 1", result.getName());
        assertEquals("Email Test 1", result.getEmail());
        assertEquals("Password Test 1", result.getPassword());
    }

    @Test
    void findByName() throws Exception {
        AccountVO mockAccountVO = input.mockVO(1);
        mockAccountVO.setId(1L);

        when(services.findByName("Name Test 1")).thenReturn(mockAccountVO);
        var result = services.findByName("Name Test 1");

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Name Test 1", result.getName());
        assertEquals("Email Test 1", result.getEmail());
        assertEquals("Password Test 1", result.getPassword());
    }

    @Test
    void createAccount() throws Exception {
        AccountVO mockAccount = input.mockVO(1);
        AccountVO persisted = mockAccount;
        persisted.setId(1L);

        AccountVO mockAccountVO = input.mockVO(1);
        mockAccountVO.setId(1L);
        when(services.createAccount(mockAccount)).thenReturn(persisted);

        var result = services.createAccount(mockAccountVO);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Name Test 1", result.getName());
        assertEquals("Email Test 1", result.getEmail());
        assertEquals("Password Test 1", result.getPassword());
    }
}