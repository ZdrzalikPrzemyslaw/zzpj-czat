package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.pzjswpooks.zzpj.chat.api.common.I18n;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchUserRequestDtoTest {

    private final SearchUserRequestDto searchUserRequestDto = new SearchUserRequestDto();
    private Validator validator;

    public SearchUserRequestDtoTest() {

    }

    @Test
    public void nullTest() {
        searchUserRequestDto.setFilter(null);
        var validate = validator.validate(searchUserRequestDto);
        assertFalse(validate.isEmpty());
        final boolean[] correctMessage = {false};
        validate.forEach(sendChatMessageRequestDTOConstraintViolation -> {
            if (sendChatMessageRequestDTOConstraintViolation.getMessage().equals(I18n.FILTER_EMPTY)) {
                correctMessage[0] = true;
            }
        });
        assertTrue(correctMessage[0]);
    }


    @BeforeEach
    void setUpBeforeEach() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getFilter() {
        searchUserRequestDto.setFilter("message");
        var validate = validator.validate(searchUserRequestDto);
        assertTrue(validate.isEmpty());
    }
}