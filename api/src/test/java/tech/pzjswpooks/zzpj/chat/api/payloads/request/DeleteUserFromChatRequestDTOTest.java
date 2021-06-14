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

class DeleteUserFromChatRequestDTOTest {
    DeleteUserFromChatRequestDTO deleteUserFromChatRequestDTO = new DeleteUserFromChatRequestDTO();

    private final String USERNAME = "username";
    private Validator validator;

    void makeCorrectDTO() {
        deleteUserFromChatRequestDTO.setUsername(USERNAME);
    }

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        makeCorrectDTO();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUsername() {
        assertEquals(deleteUserFromChatRequestDTO.getUsername(), USERNAME);
        var validate = validator.validate(deleteUserFromChatRequestDTO);
        assertTrue(validate.isEmpty());
    }

    @Test
    void usernameNull() {
        deleteUserFromChatRequestDTO.setUsername(null);
        assertNull(deleteUserFromChatRequestDTO.getUsername());
        var validate = validator.validate(deleteUserFromChatRequestDTO);
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
        deleteUserFromChatRequestDTO.setUsername("1");
        assertEquals(deleteUserFromChatRequestDTO.getUsername(), "1");
        var validate = validator.validate(deleteUserFromChatRequestDTO);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.LOGIN_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);

        deleteUserFromChatRequestDTO.setUsername(CharBuffer.allocate(10000).toString().replace('\0', ' '));
        validate = validator.validate(deleteUserFromChatRequestDTO);
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