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

class CreateChatRequestDtoTest {
    private final String NAME = "name";
    CreateChatRequestDto createChatRequestDto = new CreateChatRequestDto();
    private Validator validator;

    private void makeValidDTO() {
        createChatRequestDto.setName(NAME);
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
    void getName() {
    }

    @Test
    void testGetName() {
        assertEquals(createChatRequestDto.getName(), NAME);
        var validate = validator.validate(createChatRequestDto);
        assertTrue(validate.isEmpty());
    }

    @Test
    void nameNull() {
        createChatRequestDto.setName(null);
        assertNull(createChatRequestDto.getName());
        var validate = validator.validate(createChatRequestDto);
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
    void nameInvalidSize() {
        createChatRequestDto.setName("");
        assertEquals(createChatRequestDto.getName(), "");
        var validate = validator.validate(createChatRequestDto);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.CHAT_NAME_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);

        createChatRequestDto.setName(CharBuffer.allocate(10000).toString().replace('\0', ' '));
        validate = validator.validate(createChatRequestDto);
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