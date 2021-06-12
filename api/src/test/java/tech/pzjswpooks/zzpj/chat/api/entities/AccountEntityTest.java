package tech.pzjswpooks.zzpj.chat.api.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountsEntityTest {
    AccountsEntity accountsEntity;
    AccessLevelsEntity accessLevelsEntity;

    @BeforeEach
    void setUp() {
        accountsEntity = new AccountsEntity("Nazwa", "123456", "jankowalski@mail.com", "Jan", "PL", "Kowalski", "123456789");
        accountsEntity.setVersion(3L);
        accessLevelsEntity = new AccessLevelsEntity(accountsEntity, true);
        accountsEntity.addAccessLevels(accessLevelsEntity);
    }

    @org.junit.jupiter.api.Test
    void getOwnedChats() {
        ChatsEntity chatsEntity = new ChatsEntity();

        assertTrue(accountsEntity.getOwnedChats().isEmpty());
        accountsEntity.getOwnedChats().add(chatsEntity);
        assertFalse(accountsEntity.getOwnedChats().isEmpty());
        assertTrue(accountsEntity.getOwnedChats().contains(chatsEntity));


    }

    @Test
    void getAccessLevels() {
        assertEquals(1, accountsEntity.getAccessLevels().size());
        assertTrue(accountsEntity.getAccessLevels().contains(accessLevelsEntity));
    }

    @Test
    void getId() {
        assertNull(accountsEntity.getId());
    }

    @Test
    void getUsername() {
        assertEquals("Nazwa", accountsEntity.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("123456", accountsEntity.getPassword());
    }

    @Test
    void setPassword() {
        assertNotEquals("123321", accountsEntity.getPassword());
        accountsEntity.setPassword("123321");
        assertEquals("123321", accountsEntity.getPassword());
    }

    @Test
    void getEnabled() {
        assertTrue(accountsEntity.getEnabled());
    }

    @Test
    void setEnabled() {
        assertTrue(accountsEntity.getEnabled());
        accountsEntity.setEnabled(false);
        assertFalse(accountsEntity.getEnabled());
    }

    @Test
    void addAccessLevels() {
        assertEquals(1, accountsEntity.getAccessLevels().size());
        accountsEntity.addAccessLevels(new AccessLevelsEntity(accountsEntity, false));
        assertEquals(2, accountsEntity.getAccessLevels().size());

    }

    @Test
    void getVersion() {
        assertEquals(3L, accountsEntity.getVersion());
    }

    @Test
    void setVersion() {
        assertEquals(3L, accountsEntity.getVersion());
        accountsEntity.setVersion(4L);
        assertEquals(4L, accountsEntity.getVersion());
    }

    @Test
    void getUserId() {
        assertNotNull(accountsEntity.getUserId());
    }

    @Test
    void testEquals() {
        AccountsEntity secondAccountEntity = new AccountsEntity("Nazwa", "123456", "jankowalski@mail.com", "Jan", "PL", "Kowalski", "123456789");
        secondAccountEntity.setVersion(3L);
        secondAccountEntity.addAccessLevels(accessLevelsEntity);

        assertTrue(accountsEntity.equals(accountsEntity));
        assertTrue(accountsEntity.equals(secondAccountEntity));
        assertFalse(accountsEntity.equals(new AccountsEntity("InnaNazwa", "123456", "jankowalski@mail.com", "Jan", "PL", "Kowalski", "123456789")));

    }

    @Test
    void testHashCode() {
        AccountsEntity secondAccountEntity = new AccountsEntity("Nazwa", "123456", "jankowalski@mail.com", "Jan", "PL", "Kowalski", "123456789");
        secondAccountEntity.setVersion(3L);
        secondAccountEntity.addAccessLevels(accessLevelsEntity);

        assertEquals(accountsEntity.hashCode(), secondAccountEntity.hashCode());
        secondAccountEntity.setVersion(4L);
        assertNotEquals(accountsEntity.hashCode(), secondAccountEntity.hashCode());

    }

    @Test
    void getChatMessages() {
        ChatMessagesEntity chatMessagesEntity = new ChatMessagesEntity();

        assertTrue(accountsEntity.getChatMessages().isEmpty());
        accountsEntity.getChatMessages().add(chatMessagesEntity);
        assertFalse(accountsEntity.getChatMessages().isEmpty());
        assertTrue(accountsEntity.getChatMessages().contains(chatMessagesEntity));
        assertEquals(1, accountsEntity.getChatMessages().size());
    }

    @Test
    void getAccountId() {
        ChatUsersEntity chatUsersEntity = new ChatUsersEntity();
        assertTrue(accountsEntity.getAccountId().isEmpty());
        accountsEntity.getAccountId().add(chatUsersEntity);
        assertFalse(accountsEntity.getAccountId().isEmpty());
        assertTrue(accountsEntity.getAccountId().contains(chatUsersEntity));
        assertEquals(1, accountsEntity.getAccountId().size());
    }
}