package com.sb.book.check24.validator;

import com.sb.book.check24.to.LoginInformation;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class LoginValidatorTest {

    @Test
    void validateEMailAddress() {
        LoginValidator loginValidator = new LoginValidator();

        LoginInformation loginInformation = new LoginInformation();
        loginInformation.setEmailAddress("asdk@Su.com");
        loginInformation.setPassword("GoodWay");

        Errors errors = new BeanPropertyBindingResult(loginInformation, "loginInformation");
        loginValidator.validate(loginInformation, errors);

        assertFalse(errors.hasErrors());

    }

    @Test
    void validateEmailAddressWithTest() {
        LoginValidator loginValidator = new LoginValidator();

        LoginInformation loginInformation = new LoginInformation();
        loginInformation.setEmailAddress("asdk@test.com");
        loginInformation.setPassword("GoodWay");

        Errors errors = new BeanPropertyBindingResult(loginInformation, "loginInformation");
        loginValidator.validate(loginInformation, errors);

        assertTrue(errors.hasErrors());

    }

    @Test
    void validateIncorrectEmailAddress() {
        LoginValidator loginValidator = new LoginValidator();

        LoginInformation loginInformation = new LoginInformation();
        loginInformation.setEmailAddress("asdk@.com");
        loginInformation.setPassword("GoodWay");

        Errors errors = new BeanPropertyBindingResult(loginInformation, "loginInformation");
        loginValidator.validate(loginInformation, errors);

        assertTrue(errors.hasErrors());
    }
}