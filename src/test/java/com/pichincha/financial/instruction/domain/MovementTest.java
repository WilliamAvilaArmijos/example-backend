package com.pichincha.financial.instruction.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MovementTest {
    private Movement movement;

    @BeforeEach
    void setup() {
        movement = Movement.builder().build();
    }

    @Test
    void testGetterAndSetter() {
        // Arrange
        Integer id = 1;
        LocalDateTime date = LocalDateTime.now();
        String type = "DEBIT";
        BigDecimal amount = new BigDecimal("100.00");
        BigDecimal balance = new BigDecimal("900.00");
        String description = "Test movement";
        Integer accountId = 1;

        // Act
        movement.setId(id);
        movement.setDate(date);
        movement.setType(type);
        movement.setAmount(amount);
        movement.setBalance(balance);
        movement.setDescription(description);
        movement.setAccountId(accountId);

        // Assert
        assertEquals(id, movement.getId());
        assertEquals(date, movement.getDate());
        assertEquals(type, movement.getType());
        assertEquals(amount, movement.getAmount());
        assertEquals(balance, movement.getBalance());
        assertEquals(description, movement.getDescription());
        assertEquals(accountId, movement.getAccountId());
    }

    @Test
    void testBuilder() {
        // Arrange
        LocalDateTime testDate = LocalDateTime.now();

        // Act
        Movement movementBuilt = Movement.builder()
                .id(1)
                .date(testDate)
                .type("CREDIT")
                .amount(new BigDecimal("200.00"))
                .balance(new BigDecimal("1200.00"))
                .description("Test movement with builder")
                .accountId(1)
                .build();

        // Assert
        assertNotNull(movementBuilt);
        assertEquals(1, movementBuilt.getId());
        assertEquals(testDate, movementBuilt.getDate());
        assertEquals("CREDIT", movementBuilt.getType());
        assertEquals(new BigDecimal("200.00"), movementBuilt.getAmount());
        assertEquals(new BigDecimal("1200.00"), movementBuilt.getBalance());
        assertEquals("Test movement with builder", movementBuilt.getDescription());
        assertEquals(1, movementBuilt.getAccountId());
    }
}