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

class EditAccountRequestDTOTest {
    EditAccountRequestDTO editAccountRequestDTO = new EditAccountRequestDTO();
    private Validator validator;
    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        makeCorrectDTO();
    }

    private String EMAIL = "hello@email.com";
    private String NAME = "Przemyslaw";
    private String SURNAME = "Zdrzalik";
    private String LANGUAGE = "PL";
    private String PHONE = "1234567890";

    void makeCorrectDTO() {
        editAccountRequestDTO.setLanguage(LANGUAGE);
        editAccountRequestDTO.setEmail(EMAIL);
        editAccountRequestDTO.setFirstName(NAME);
        editAccountRequestDTO.setLastName(SURNAME);
        editAccountRequestDTO.setPhoneNumber(PHONE);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getEmail() {
        assertEquals(editAccountRequestDTO.getEmail(), EMAIL);
        var validate = validator.validate(editAccountRequestDTO);
        assertTrue(validate.isEmpty());
    }

    @Test
    void emailInvalidSize() {
        editAccountRequestDTO.setEmail("1");
        assertEquals(editAccountRequestDTO.getEmail(), "1");
        var validate = validator.validate(editAccountRequestDTO);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.EMAIL_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);

        editAccountRequestDTO.setEmail(CharBuffer.allocate(10000).toString().replace('\0', ' '));
        validate = validator.validate(editAccountRequestDTO);
        assertFalse(validate.isEmpty());
        correctMessage[0] = false;
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.EMAIL_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }

    @Test
    void getFirstName() {
        assertEquals(editAccountRequestDTO.getFirstName(), NAME);
        var validate = validator.validate(editAccountRequestDTO);
        assertTrue(validate.isEmpty());
    }

    @Test
    void firstNameInvalidSize() {
        editAccountRequestDTO.setFirstName("");
        assertEquals(editAccountRequestDTO.getFirstName(), "");
        var validate = validator.validate(editAccountRequestDTO);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.FIRST_NAME_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);

        editAccountRequestDTO.setFirstName(CharBuffer.allocate(10000).toString().replace('\0', ' '));
        validate = validator.validate(editAccountRequestDTO);
        assertFalse(validate.isEmpty());
        correctMessage[0] = false;
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.FIRST_NAME_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }

    @Test
    void getLastName() {
        assertEquals(editAccountRequestDTO.getLastName(), SURNAME);
        var validate = validator.validate(editAccountRequestDTO);
        assertTrue(validate.isEmpty());
    }

    @Test
    void lastNameInvalidSize() {
        editAccountRequestDTO.setLastName("");
        assertEquals(editAccountRequestDTO.getLastName(), "");
        var validate = validator.validate(editAccountRequestDTO);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.LAST_NAME_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);

        editAccountRequestDTO.setLastName(CharBuffer.allocate(10000).toString().replace('\0', ' '));
        validate = validator.validate(editAccountRequestDTO);
        assertFalse(validate.isEmpty());
        correctMessage[0] = false;
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.LAST_NAME_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }

    @Test
    void getLanguage() {
        assertEquals(editAccountRequestDTO.getLanguage(), LANGUAGE);
        var validate = validator.validate(editAccountRequestDTO);
        assertTrue(validate.isEmpty());
    }

    @Test
    void getPhoneNumber() {
        assertEquals(editAccountRequestDTO.getPhoneNumber(), PHONE);
        var validate = validator.validate(editAccountRequestDTO);
        assertTrue(validate.isEmpty());
    }

    @Test
    void phoneNumberInvalidSize() {
        editAccountRequestDTO.setPhoneNumber("1");
        assertEquals(editAccountRequestDTO.getPhoneNumber(), "1");
        var validate = validator.validate(editAccountRequestDTO);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.PHONE_NUMBER_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);

        editAccountRequestDTO.setPhoneNumber(CharBuffer.allocate(20).toString().replace('\0', ' '));
        validate = validator.validate(editAccountRequestDTO);
        assertFalse(validate.isEmpty());
        correctMessage[0] = false;
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.PHONE_NUMBER_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }
}