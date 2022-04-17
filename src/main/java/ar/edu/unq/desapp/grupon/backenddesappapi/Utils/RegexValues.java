package ar.edu.unq.desapp.grupon.backenddesappapi.Utils;

import org.springframework.beans.factory.annotation.Value;

public class RegexValues {

    @Value("${email.validation}")
    private static String EMAIL_REGEX;

    private static String PASSWORD_REGEX;

    public static String getEmailRegex() {
        return EMAIL_REGEX;
    }

    public static String getPasswordRegex() {
        return PASSWORD_REGEX;
    }
}
