package com.pichincha.financial.instruction.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private Person person;

    @BeforeEach
    void setup() {
        person = new Person();
    }

    @Test
    void testGetterAndSetter() {
        // Arrange
        String name = "John Doe";
        String gender = "M";
        Integer age = 30;
        String identification = "1234567890";
        String address = "123 Main St";
        String phone = "0987654321";

        // Act
        person.setName(name);
        person.setGender(gender);
        person.setAge(age);
        person.setIdentification(identification);
        person.setAddress(address);
        person.setPhone(phone);

        // Assert
        assertEquals(name, person.getName());
        assertEquals(gender, person.getGender());
        assertEquals(age, person.getAge());
        assertEquals(identification, person.getIdentification());
        assertEquals(address, person.getAddress());
        assertEquals(phone, person.getPhone());
    }

    @Test
    void testBuilder() {
        // Arrange & Act
        Person personBuilt = Person.builder()
                .name("Jane Doe")
                .gender("F")
                .age(25)
                .identification("0987654321")
                .address("456 Oak St")
                .phone("1234567890")
                .build();

        // Assert
        assertNotNull(personBuilt);
        assertEquals("Jane Doe", personBuilt.getName());
        assertEquals("F", personBuilt.getGender());
        assertEquals(25, personBuilt.getAge());
        assertEquals("0987654321", personBuilt.getIdentification());
        assertEquals("456 Oak St", personBuilt.getAddress());
        assertEquals("1234567890", personBuilt.getPhone());
    }
}