package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestDTOTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void shouldHaveNoViolations() {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setUsername("username");
        dto.setPassword("password123");

        Set<ConstraintViolation<LoginRequestDTO>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldDetectInvalidUsername() {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setUsername("u");
        dto.setPassword("password123");

        Set<ConstraintViolation<LoginRequestDTO>> violations = validator.validate(dto);

        assertEquals(violations.size(), 1);

        ConstraintViolation<LoginRequestDTO> violation = violations.iterator().next();
        assertEquals("Invalid size of username", violation.getMessage());
        assertEquals("username", violation.getPropertyPath().toString());
        assertEquals("u", violation.getInvalidValue());

        dto.setUsername("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");

        Set<ConstraintViolation<LoginRequestDTO>> violations2 = validator.validate(dto);

        assertEquals(violations.size(), 1);

        ConstraintViolation<LoginRequestDTO> violation2 = violations2.iterator().next();
        assertEquals("Invalid size of username", violation2.getMessage());
        assertEquals("username", violation2.getPropertyPath().toString());
        assertEquals("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu", violation2.getInvalidValue());
    }

    @Test
    public void shouldDetectInvalidPassword() {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setUsername("username");
        dto.setPassword("pas");

        Set<ConstraintViolation<LoginRequestDTO>> violations = validator.validate(dto);

        assertEquals(violations.size(), 1);

        ConstraintViolation<LoginRequestDTO> violation = violations.iterator().next();
        assertEquals("Invalid size of password", violation.getMessage());
        assertEquals("password", violation.getPropertyPath().toString());
        assertEquals("pas", violation.getInvalidValue());
    }

}