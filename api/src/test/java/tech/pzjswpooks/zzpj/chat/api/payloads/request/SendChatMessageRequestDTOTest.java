package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import org.junit.Before;
import org.junit.Test;
import tech.pzjswpooks.zzpj.chat.api.common.I18n;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import java.nio.CharBuffer;
import java.util.Set;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class SendChatMessageRequestDTOTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public SendChatMessageRequestDTOTest() {

    }

    @Test
    public void nullTest() {
        SendChatMessageRequestDTO sendChatMessageRequestDTO = new SendChatMessageRequestDTO();
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
        SendChatMessageRequestDTO sendChatMessageRequestDTO = new SendChatMessageRequestDTO();
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
        SendChatMessageRequestDTO sendChatMessageRequestDTO = new SendChatMessageRequestDTO();
        sendChatMessageRequestDTO.setMessage(CharBuffer.allocate(10000).toString().replace( '\0', ' ' ));
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
        SendChatMessageRequestDTO sendChatMessageRequestDTO = new SendChatMessageRequestDTO();
        sendChatMessageRequestDTO.setMessage("message");
        var validate = validator.validate(sendChatMessageRequestDTO);
        assertTrue(validate.isEmpty());

    }

}