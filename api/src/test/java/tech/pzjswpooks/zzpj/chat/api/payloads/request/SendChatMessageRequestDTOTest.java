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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SendChatMessageRequestDTOTest {

    SendChatMessageRequestDTO sendChatMessageRequestDTO = new SendChatMessageRequestDTO();
    private Validator validator;

    public SendChatMessageRequestDTOTest() {

    }

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void nullTest() {
        sendChatMessageRequestDTO.setMessage(null);
        var validate = validator.validate(sendChatMessageRequestDTO);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.MESSAGE_EMPTY)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }

    @Test
    public void tooSmall() {
        sendChatMessageRequestDTO.setMessage("");
        var validate = validator.validate(sendChatMessageRequestDTO);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.MESSAGE_SIZE_INVALID)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }

    @Test
    public void tooBig() {
        sendChatMessageRequestDTO.setMessage(CharBuffer.allocate(10000).toString().replace('\0', ' '));
        var validate = validator.validate(sendChatMessageRequestDTO);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.MESSAGE_SIZE_INVALID)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }

    @Test
    public void correct() {
        sendChatMessageRequestDTO.setMessage("message");
        var validate = validator.validate(sendChatMessageRequestDTO);
        assertTrue(validate.isEmpty());

    }


    @AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getMessage() {
        sendChatMessageRequestDTO.setMessage("message");
        assertEquals(sendChatMessageRequestDTO.getMessage(), "message");
    }
}