package tech.pzjswpooks.zzpj.chat.api.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsersEntityTest {
    AccountsEntity accountsEntity;
    UsersEntity usersEntity = new UsersEntity();

    @BeforeEach
    void setUp() {
        accountsEntity = new AccountsEntity("Nazwa", "123456", "jankowalski@mail.com", "Jan", "PL", "Kowalski", "123456789");
        usersEntity = new UsersEntity("jankowalski@mail.com", "Jan", "PL", "Kowalski", "123456789", accountsEntity);
    }

    @Test
    void getId() {
        assertNull(usersEntity.getId());
    }

    @Test
    void getEmail() {
        assertEquals("jankowalski@mail.com", usersEntity.getEmail());
    }

    @Test
    void setEmail() {
        assertNotEquals("janekkowalski@mail.com", usersEntity.getEmail());
        usersEntity.setEmail("janekkowalski@mail.com");
        assertEquals("janekkowalski@mail.com", usersEntity.getEmail());
    }

    @Test
    void getFirstName() {
        assertEquals("Jan", usersEntity.getFirstName());
    }

    @Test
    void setFirstName() {
        assertNotEquals("Janek", usersEntity.getFirstName());
        usersEntity.setFirstName("Janek");
        assertEquals("Janek", usersEntity.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Kowalski", usersEntity.getLastName());
    }

    @Test
    void setLastName() {
        assertNotEquals("Kowal", usersEntity.getLastName());
        usersEntity.setLastName("Kowal");
        assertEquals("Kowal", usersEntity.getLastName());
    }

    @Test
    void getPhoneNumber() {
        assertEquals("123456789", usersEntity.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        assertNotEquals("123456780", usersEntity.getPhoneNumber());
        usersEntity.setPhoneNumber("123456780");
        assertEquals("123456780", usersEntity.getPhoneNumber());
    }

    @Test
    void getLanguage() {
        assertEquals("PL", usersEntity.getLanguage());
    }

    @org.junit.jupiter.api.Test
    void setLanguage() {
        assertNotEquals("ENG", usersEntity.getLanguage());
        usersEntity.setLanguage("ENG");
        assertEquals("ENG", usersEntity.getLanguage());
    }

    @Test
    void testEquals() {
        UsersEntity otherUsersEntity = new UsersEntity("jankowalski@mail.com", "Jan", "PL", "Kowalski", "123456789", accountsEntity);
        assertTrue(accountsEntity.equals(accountsEntity));
        assertFalse(accountsEntity.equals(otherUsersEntity));

    }

    @Test
    void testHashCode() {
        UsersEntity otherUsersEntity = new UsersEntity("jankowalski@mail.com", "Jan", "PL", "Kowalski", "123456789", accountsEntity);
        assertEquals(usersEntity.hashCode(), usersEntity.hashCode());
        assertEquals(usersEntity.hashCode(), otherUsersEntity.hashCode());
        otherUsersEntity.setPhoneNumber("123123123");
        assertNotEquals(usersEntity.hashCode(), otherUsersEntity.hashCode());
    }
}