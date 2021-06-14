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

class ChangeChatNameRequestDTOTest {
    private final String NAME = "name";
    ChangeChatNameRequestDTO changeChatNameRequestDTO = new ChangeChatNameRequestDTO();
    private Validator validator;

    private void makeValidDTO() {
        changeChatNameRequestDTO.setName(NAME);
    }

    @BeforeEach
    void setUp() {
        makeValidDTO();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUsername() {
        assertEquals(changeChatNameRequestDTO.getName(), NAME);
        var validate = validator.validate(changeChatNameRequestDTO);
        assertTrue(validate.isEmpty());
    }

    @Test
    void usernameNull() {
        changeChatNameRequestDTO.setName(null);
        assertNull(changeChatNameRequestDTO.getName());
        var validate = validator.validate(changeChatNameRequestDTO);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.CHAT_NAME_NULL)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }

    @Test
    void usernameInvalidSize() {
        changeChatNameRequestDTO.setName("");
        assertEquals(changeChatNameRequestDTO.getName(), "");
        var validate = validator.validate(changeChatNameRequestDTO);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.CHAT_NAME_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);

        changeChatNameRequestDTO.setName(CharBuffer.allocate(10000).toString().replace('\0', ' '));
        validate = validator.validate(changeChatNameRequestDTO);
        assertFalse(validate.isEmpty());
        correctMessage[0] = false;
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.CHAT_NAME_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }
}