package com.pichincha.financial.instruction.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account account;

    @BeforeEach
    void setup() {
        account = Account.builder().build();
    }

    @Test
    void testGetterAndSetter() {
        // Arrange
        Integer id = 1;
        String accountNumber = "123456789";
        BigDecimal balance = new BigDecimal("1000.00");
        String accountType = "Savings";
        Boolean status = true;
        Integer clientId = 1;

        // Act
        account.setId(id);
        account.setAccountNumber(accountNumber);
        account.setBalance(balance);
        account.setAccountType(accountType);
        account.setStatus(status);
        account.setClientId(clientId);

        // Assert
        assertEquals(id, account.getId());
        assertEquals(accountNumber, account.getAccountNumber());
        assertEquals(balance, account.getBalance());
        assertEquals(accountType, account.getAccountType());
        assertEquals(status, account.getStatus());
        assertEquals(clientId, account.getClientId());
    }

    @Test
    void testBuilder() {
        // Arrange & Act
        Account accountBuilt = Account.builder()
                .id(1)
                .accountNumber("123456789")
                .balance(new BigDecimal("1000.00"))
                .accountType("Savings")
                .status(true)
                .clientId(1)
                .build();

        // Assert
        assertNotNull(accountBuilt);
        assertEquals(1, accountBuilt.getId());
        assertEquals("123456789", accountBuilt.getAccountNumber());
        assertEquals(new BigDecimal("1000.00"), accountBuilt.getBalance());
        assertEquals("Savings", accountBuilt.getAccountType());
        assertTrue(accountBuilt.getStatus());
        assertEquals(1, accountBuilt.getClientId());
    }
}