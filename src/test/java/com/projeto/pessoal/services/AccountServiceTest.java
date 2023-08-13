package com.projeto.pessoal.services;

import com.projeto.pessoal.data.v1.AccountDTO.AccountRequestDTO;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        Account mockAccount = input.mockEntity();
        UUID mockAccountID = UUID.randomUUID();
        mockAccount.setId(mockAccountID);

        when(repository.findById(mockAccountID)).thenReturn(Optional.of(mockAccount));
        Account result = services.findById(mockAccountID);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("test_name", result.getName());
        assertEquals("test_username", result.getUsername());
        assertEquals("test_email@gmail.com", result.getEmail());
        assertEquals("test_password", result.getPassword());
    }

    @Test
    void findByEmail() throws Exception {
        Account mockAccount = input.mockEntity();

        when(repository.findByEmail(mockAccount.getEmail())).thenReturn(Optional.of(mockAccount));
        Account result = services.findByEmail(mockAccount.getEmail());

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("test_name", result.getName());
        assertEquals("test_username", result.getUsername());
        assertEquals("test_email@gmail.com", result.getEmail());
        assertEquals("test_password", result.getPassword());
    }

    @Test
    void findByUsername() throws Exception {
        Account mockAccount = input.mockEntity();

        when(repository.findByUsername(mockAccount.getUsername())).thenReturn(Optional.of(mockAccount));
        Account result = services.findByUsername(mockAccount.getUsername());

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("test_name", result.getName());
        assertEquals("test_username", result.getUsername());
        assertEquals("test_email@gmail.com", result.getEmail());
        assertEquals("test_password", result.getPassword());
    }

    @Test
    void findAll() throws Exception {
        List<Account> mock = input.mockEntityList();

        when(repository.findAll()).thenReturn(mock);
        List<Account> result = services.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void createAccount() throws Exception {
        AccountRequestDTO request = new AccountRequestDTO();
        request.setName("test_name");
        request.setUsername("test_username");
        request.setEmail("test_email@gmail.com");
        request.setPassword("test_password");

        var result = services.createAccount(request);
        assertNotNull(result);
        assertEquals("test_name", result.getName());
        assertEquals("test_username", result.getUsername());
        assertEquals("test_email@gmail.com", result.getEmail());
        assertEquals("test_password", result.getPassword());
    };

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
        Account mockAccount = input.mockEntity();
        UUID mockAccountID = UUID.randomUUID();
        mockAccount.setId(mockAccountID);

        AccountRequestDTO request = new AccountRequestDTO();
        request.setName("test_name");
        request.setUsername("test_username");
        request.setEmail("test_email@gmail.com");
        request.setPassword("test_password");

        when(repository.findByEmail(mockAccount.getEmail())).thenReturn(Optional.of(mockAccount));
        var result = services.updateAccount(request.getEmail(), request);

        assertNotNull(result);
        assertEquals("test_name", result.getName());
        assertEquals("test_username", result.getUsername());
        assertEquals("test_email@gmail.com", result.getEmail());
        assertEquals("test_password", result.getPassword());
    }

    @Test
    void updateWithNullAccount() throws Exception {
        Exception exception = assertThrows(RequiredObjectsIsNullException.class, () -> {
            services.updateAccount(null, null);
        });

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deleteAccount() throws Exception {
        Account mockAccount = input.mockEntity();

        when(repository.findByEmail(mockAccount.getEmail())).thenReturn(Optional.of(mockAccount));

        services.deleteAccount(mockAccount.getEmail());
    }
}