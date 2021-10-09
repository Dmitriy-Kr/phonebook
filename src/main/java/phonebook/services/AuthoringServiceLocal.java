package phonebook.services;


public class AuthoringServiceLocal implements AuthorizingService{

    private String login;
    private String password;
    private boolean isAuthoring;

    public AuthoringServiceLocal(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean login(String login, String password) {
        return isAuthoring = this.login.equals(login) && this.password.equals(password);
    }

    @Override
    public boolean registration(String login, String password, String birthDay) {
        throw new  RuntimeException("Регистрация на локальном компьютере не поддерживается");
    }

    @Override
    public boolean isAuthoring() {
        return isAuthoring;
    }
}
