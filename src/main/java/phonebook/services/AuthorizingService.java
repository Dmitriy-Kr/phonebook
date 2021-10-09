package phonebook.services;

public interface AuthorizingService {
    boolean login(String login, String password);
    boolean registration(String login, String password, String birthDay);
    boolean isAuthoring();
}
