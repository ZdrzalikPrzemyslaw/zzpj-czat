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

class AccessRequestDtoTest {
    private final String LEVEL = "level.user";
    private final String LOGIN = "username";
    AccessRequestDto accessRequestDto = new AccessRequestDto();
    private Validator validator;

    private void makeValidDTO() {
        accessRequestDto.setAccessLevel(LEVEL);
        accessRequestDto.setUsername(LOGIN);
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
        assertEquals(accessRequestDto.getUsername(), LOGIN);
        var validate = validator.validate(accessRequestDto);
        assertTrue(validate.isEmpty());
    }

    @Test
    void usernameNull() {
        accessRequestDto.setUsername(null);
        assertNull(accessRequestDto.getUsername());
        var validate = validator.validate(accessRequestDto);
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
        accessRequestDto.setUsername("1");
        assertEquals(accessRequestDto.getUsername(), "1");
        var validate = validator.validate(accessRequestDto);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.LOGIN_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);

        accessRequestDto.setUsername(CharBuffer.allocate(10000).toString().replace('\0', ' '));
        validate = validator.validate(accessRequestDto);
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
    void getAccessLevel() {
        assertEquals(accessRequestDto.getAccessLevel(), LEVEL);
        var validate = validator.validate(accessRequestDto);
        assertTrue(validate.isEmpty());
    }

    @Test
    void accessLevelNull() {
        accessRequestDto.setAccessLevel(null);
        assertNull(accessRequestDto.getAccessLevel());
        var validate = validator.validate(accessRequestDto);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.ACCESS_LEVEL_NULL)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }


}