package phonebook.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static Matcher matcher;
    private static final String EMAIL_PATTERN = "[A-Za-z0-9+_.-]+@.+";
    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean emailValidate(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
