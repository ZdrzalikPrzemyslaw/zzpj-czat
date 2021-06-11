package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.pzjswpooks.zzpj.chat.api.common.I18n;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.nio.CharBuffer;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationRequestDtoTest {
    private Validator validator;

    RegistrationRequestDto registrationRequestDto = new RegistrationRequestDto();

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        makeCorrectDTO();
    }

    @AfterEach
    void tearDown() {
    }

    private String EMAIL = "hello@email.com";
    private String PASSWORD = "P@ssw0rd";
    private String LOGIN = "username";
    private String NAME = "Przemyslaw";
    private String SURNAME = "Zdrzalik";
    private String LANGUAGE = "PL";
    private String PHONE = "1234567890";

    void makeCorrectDTO() {
        registrationRequestDto.setEmail("hello@email.com");
        registrationRequestDto.setUsername(LOGIN);
        registrationRequestDto.setFirstName(NAME);
        registrationRequestDto.setLastName(SURNAME);
        registrationRequestDto.setLanguage(LANGUAGE);
        registrationRequestDto.setPassword(PASSWORD);
        registrationRequestDto.setPhoneNumber(PHONE);
    }

    @Test
    void getUsername() {
        assertEquals(registrationRequestDto.getUsername(), LOGIN);
        var validate = validator.validate(registrationRequestDto);
        assertTrue(validate.isEmpty());
    }

    @Test
    void usernameNull() {
        registrationRequestDto.setUsername(null);
        assertNull(registrationRequestDto.getUsername());
        var validate = validator.validate(registrationRequestDto);
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
        registrationRequestDto.setUsername("1");
        assertEquals(registrationRequestDto.getUsername(), "1");
        var validate = validator.validate(registrationRequestDto);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.LOGIN_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);

        registrationRequestDto.setUsername(CharBuffer.allocate(10000).toString().replace('\0', ' '));
        validate = validator.validate(registrationRequestDto);
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
        assertEquals(registrationRequestDto.getPassword(), PASSWORD);
        var validate = validator.validate(registrationRequestDto);
        assertTrue(validate.isEmpty());
    }

    @Test
    void passwordNull() {
        registrationRequestDto.setPassword(null);
        assertNull(registrationRequestDto.getPassword());
        var validate = validator.validate(registrationRequestDto);
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
        registrationRequestDto.setPassword("1");
        assertEquals(registrationRequestDto.getPassword(), "1");
        var validate = validator.validate(registrationRequestDto);
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
    void getEmail() {
        assertEquals(registrationRequestDto.getEmail(), EMAIL);
        var validate = validator.validate(registrationRequestDto);
        assertTrue(validate.isEmpty());
    }

    @Test
    void emailNull() {
        registrationRequestDto.setEmail(null);
        assertNull(registrationRequestDto.getEmail());
        var validate = validator.validate(registrationRequestDto);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.EMAIL_NULL)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }

    @Test
    void emailInvalidSize() {
        registrationRequestDto.setEmail("1");
        assertEquals(registrationRequestDto.getEmail(), "1");
        var validate = validator.validate(registrationRequestDto);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.EMAIL_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);

        registrationRequestDto.setEmail(CharBuffer.allocate(10000).toString().replace('\0', ' '));
        validate = validator.validate(registrationRequestDto);
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
        assertEquals(registrationRequestDto.getFirstName(), NAME);
        var validate = validator.validate(registrationRequestDto);
        assertTrue(validate.isEmpty());
    }

    @Test
    void firstNameNull() {
        registrationRequestDto.setFirstName(null);
        assertNull(registrationRequestDto.getFirstName());
        var validate = validator.validate(registrationRequestDto);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.FIRST_NAME_NULL)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }

    @Test
    void firstNameInvalidSize() {
        registrationRequestDto.setFirstName("");
        assertEquals(registrationRequestDto.getFirstName(), "");
        var validate = validator.validate(registrationRequestDto);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.FIRST_NAME_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);

        registrationRequestDto.setFirstName(CharBuffer.allocate(10000).toString().replace('\0', ' '));
        validate = validator.validate(registrationRequestDto);
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
        assertEquals(registrationRequestDto.getLastName(), SURNAME);
        var validate = validator.validate(registrationRequestDto);
        assertTrue(validate.isEmpty());
    }

    @Test
    void lastNameNull() {
        registrationRequestDto.setLastName(null);
        assertNull(registrationRequestDto.getLastName());
        var validate = validator.validate(registrationRequestDto);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.LAST_NAME_NULL)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }

    @Test
    void lastNameInvalidSize() {
        registrationRequestDto.setLastName("");
        assertEquals(registrationRequestDto.getLastName(), "");
        var validate = validator.validate(registrationRequestDto);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.LAST_NAME_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);

        registrationRequestDto.setLastName(CharBuffer.allocate(10000).toString().replace('\0', ' '));
        validate = validator.validate(registrationRequestDto);
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
        assertEquals(registrationRequestDto.getLanguage(), LANGUAGE);
        var validate = validator.validate(registrationRequestDto);
        assertTrue(validate.isEmpty());
    }

    @Test
    void languageNull() {
        registrationRequestDto.setLanguage(null);
        assertNull(registrationRequestDto.getLanguage());
        var validate = validator.validate(registrationRequestDto);
        assertTrue(validate.isEmpty());
    }

    @Test
    void getPhoneNumber() {
        assertEquals(registrationRequestDto.getPhoneNumber(), PHONE);
        var validate = validator.validate(registrationRequestDto);
        assertTrue(validate.isEmpty());
    }

    @Test
    void phoneNumberNull() {
        registrationRequestDto.setPhoneNumber(null);
        assertNull(registrationRequestDto.getPhoneNumber());
        var validate = validator.validate(registrationRequestDto);
        assertTrue(validate.isEmpty());
    }

    @Test
    void phoneNumberInvalidSize() {
        registrationRequestDto.setPhoneNumber("1");
        assertEquals(registrationRequestDto.getPhoneNumber(), "1");
        var validate = validator.validate(registrationRequestDto);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.PHONE_NUMBER_INVALID_SIZE)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);

        registrationRequestDto.setPhoneNumber(CharBuffer.allocate(20).toString().replace('\0', ' '));
        validate = validator.validate(registrationRequestDto);
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