package phonebook.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator {
    private static Matcher matcher;
    private static final String PHONE_PATTERN = "\\+\\d{12}";
    private static Pattern pattern = Pattern.compile(PHONE_PATTERN);

    public static boolean phoneValidate(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
