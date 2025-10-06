package com.pichincha.financial.instruction.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private Client client;

    @BeforeEach
    void setup() {
        client = new Client();
    }

    @Test
    void testGetterAndSetter() {
        // Arrange
        Integer id = 1;
        String password = "secretPassword";
        Boolean status = true;
        String name = "John Doe";
        String gender = "M";
        Integer age = 30;
        String identification = "1234567890";
        String address = "123 Main St";
        String phone = "0987654321";

        // Act
        client.setId(id);
        client.setPassword(password);
        client.setStatus(status);
        client.setName(name);
        client.setGender(gender);
        client.setAge(age);
        client.setIdentification(identification);
        client.setAddress(address);
        client.setPhone(phone);

        // Assert
        assertEquals(id, client.getId());
        assertEquals(password, client.getPassword());
        assertEquals(status, client.getStatus());
        assertEquals(name, client.getName());
        assertEquals(gender, client.getGender());
        assertEquals(age, client.getAge());
        assertEquals(identification, client.getIdentification());
        assertEquals(address, client.getAddress());
        assertEquals(phone, client.getPhone());
    }

    @Test
    void testBuilder() {
        // Arrange & Act
        Client clientBuilt = Client.builder()
                .id(1)
                .password("secretPassword")
                .status(true)
                .name("Jane Doe")
                .gender("F")
                .age(25)
                .identification("0987654321")
                .address("456 Oak St")
                .phone("1234567890")
                .build();

        // Assert
        assertNotNull(clientBuilt);
        assertEquals(1, clientBuilt.getId());
        assertEquals("secretPassword", clientBuilt.getPassword());
        assertTrue(clientBuilt.getStatus());
        assertEquals("Jane Doe", clientBuilt.getName());
        assertEquals("F", clientBuilt.getGender());
        assertEquals(25, clientBuilt.getAge());
        assertEquals("0987654321", clientBuilt.getIdentification());
        assertEquals("456 Oak St", clientBuilt.getAddress());
        assertEquals("1234567890", clientBuilt.getPhone());
    }
}