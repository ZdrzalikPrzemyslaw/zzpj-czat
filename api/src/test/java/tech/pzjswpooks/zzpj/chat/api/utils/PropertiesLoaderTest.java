package tech.pzjswpooks.zzpj.chat.api.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertiesLoaderTest {

    private static final PropertiesLoader propertiesLoader = new PropertiesLoader();

    @BeforeAll
    static void beforeAll() {
        propertiesLoader.loadProperties();
    }

    @Test
    void getConfirmationJwtSecret() {
        assertEquals(propertiesLoader.getConfirmationJwtSecret(), "46FF3152C49AC8443DC739C711792C6F5EE38D6A06441A8CE355793DA0DD5C7");
    }

    @Test
    void getConfirmationJwtExpiration() {
        assertEquals(propertiesLoader.getConfirmationJwtExpiration(), 86400000);
    }

    @Test
    void getAnonymousUserName() {
        assertEquals(propertiesLoader.getAnonymousUserName(), "anonymous");
    }
}