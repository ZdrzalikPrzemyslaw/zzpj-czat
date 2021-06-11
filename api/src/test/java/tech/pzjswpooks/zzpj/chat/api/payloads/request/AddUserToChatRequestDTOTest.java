package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.pzjswpooks.zzpj.chat.api.common.I18n;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.nio.CharBuffer;

import static org.junit.jupiter.api.Assertions.*;

class AddUserToChatRequestDTOTest {
    AddUserToChatRequestDTO addUserToChatRequestDTO = new AddUserToChatRequestDTO();
    private String LOGIN = "username";

    private void makeValidDTO() {
        addUserToChatRequestDTO.setUsername(LOGIN);
    }

    private Validator validator;

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
        assertEquals(addUserToChatRequestDTO.getUsername(), LOGIN);
        var validate = validator.validate(addUserToChatRequestDTO);
        assertTrue(validate.isEmpty());
    }

    @Test
    void usernameNull() {
        addUserToChatRequestDTO.setUsername(null);
        assertNull(addUserToChatRequestDTO.getUsername());
        var validate = validator.validate(addUserToChatRequestDTO);
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
        addUserToChatRequestDTO.setUsername("1");
        assertEquals(addUserToChatRequestDTO.getUsername(), "1");
        var validate = validator.validate(addUserToChatRequestDTO);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.LOGIN_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);

        addUserToChatRequestDTO.setUsername(CharBuffer.allocate(10000).toString().replace('\0', ' '));
        validate = validator.validate(addUserToChatRequestDTO);
        assertFalse(validate.isEmpty());
        correctMessage[0] = false;
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.LOGIN_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }
}