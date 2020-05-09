package com.sb.book.check24.validator;

import com.sb.book.check24.to.LoginInformation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return LoginInformation.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LoginInformation information = (LoginInformation) o;


        if (!isValidEmailAddress(information.getEmailAddress())) {
            errors.rejectValue("emailAddress", "Correct.EmailAddress", "Please verify Correct Email Address.");
        }

        if (isTestEmailAddress(information.getEmailAddress())) {
            errors.rejectValue("emailAddress", "No.Test.mail", "No test mail allowed.");
        }

    }

    private boolean isTestEmailAddress(String emailAddress) {
        String splitDomains = emailAddress.substring(emailAddress.indexOf("@") + 1, emailAddress.length());
        return splitDomains.equalsIgnoreCase("test.com");
    }

    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}