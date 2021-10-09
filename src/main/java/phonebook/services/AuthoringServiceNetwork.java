package phonebook.services;

import phonebook.pojo.login.LoginRequest;
import phonebook.pojo.login.LoginResponse;
import phonebook.pojo.registration.RegistrationRequest;
import phonebook.pojo.registration.RegistrationResponse;
import phonebook.services.networkservices.NetworkService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class AuthoringServiceNetwork implements AuthorizingService {
    private final String URL = "https://mag-contacts-api.herokuapp.com";
    private NetworkService networkService = new NetworkService();
    static String token;
    private boolean isAuthoring;

    @Override
    public boolean login(String login, String password) {
        String loginURL = URL + "/login";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin(login);
        loginRequest.setPassword(password);

        try {

            LoginResponse loginResponse =
                    networkService.postRequest(loginURL,
                            Map.of("Accept", "application/json",
                                    "Content-Type", "application/json"),
                            loginRequest,
                            LoginResponse.class);

            token = loginResponse.getMessage();

        } catch (RuntimeException e) {
            isAuthoring = false;
            throw e;
        }

        return isAuthoring = true;
    }

    @Override
    public boolean registration(String login, String password, String birthDay) {
        String loginURL = URL + "/register";

        RegistrationRequest registrationRequest = new RegistrationRequest();

        try {
            registrationRequest
                    .setLogin(login)
                    .setPassword(password)
                    .setDateBorn(new SimpleDateFormat("yyyy-MM-dd").parse(birthDay));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {

            RegistrationResponse registrationResponse =
                    networkService.postRequest(loginURL,
                            Map.of("Accept", "application/json",
                                    "Content-Type", "application/json"),
                            registrationRequest,
                            RegistrationResponse.class);

        } catch (RuntimeException e) {
            throw e;
        }

        return true;
    }

    @Override
    public boolean isAuthoring() {
        return isAuthoring;
    }
}
