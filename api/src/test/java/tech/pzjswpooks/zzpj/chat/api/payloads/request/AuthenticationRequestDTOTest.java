package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.pzjswpooks.zzpj.chat.api.common.I18n;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.nio.CharBuffer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthenticationRequestDTOTest {
    AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO();

    private final String PASSWORD = "P@ssw0rd";
    private final String LOGIN = "username";
    private Validator validator;

    private void makeValidDTO() {
        authenticationRequestDTO.setPassword(PASSWORD);
        authenticationRequestDTO.setUsername(LOGIN);
    }

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        makeValidDTO();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUsername() {
        assertEquals(authenticationRequestDTO.getUsername(), LOGIN);
        var validate = validator.validate(authenticationRequestDTO);
        assertTrue(validate.isEmpty());
    }

    @Test
    void usernameNull() {
        authenticationRequestDTO.setUsername(null);
        assertNull(authenticationRequestDTO.getUsername());
        var validate = validator.validate(authenticationRequestDTO);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.LOGIN_NULL)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }

    @Test
    void usernameInvalidSize() {
        authenticationRequestDTO.setUsername("1");
        assertEquals(authenticationRequestDTO.getUsername(), "1");
        var validate = validator.validate(authenticationRequestDTO);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.LOGIN_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);

        authenticationRequestDTO.setUsername(CharBuffer.allocate(10000).toString().replace('\0', ' '));
        validate = validator.validate(authenticationRequestDTO);
        assertFalse(validate.isEmpty());
        correctMessage[0] = false;
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.LOGIN_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }

    @Test
    void getPassword() {
        assertEquals(authenticationRequestDTO.getPassword(), PASSWORD);
        var validate = validator.validate(authenticationRequestDTO);
        assertTrue(validate.isEmpty());
    }

    @Test
    void passwordNull() {
        authenticationRequestDTO.setPassword(null);
        assertNull(authenticationRequestDTO.getPassword());
        var validate = validator.validate(authenticationRequestDTO);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.PASSWORD_NULL)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }

    @Test
    void passwordInvalidSize() {
        authenticationRequestDTO.setPassword("1");
        assertEquals(authenticationRequestDTO.getPassword(), "1");
        var validate = validator.validate(authenticationRequestDTO);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.PASSWORD_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }

    @Test
    void toCredential() {
        var x = authenticationRequestDTO.toCredential();
        assertEquals(x.getPasswordAsString(), PASSWORD);
        assertEquals(x.getCaller(), LOGIN);
    }
}