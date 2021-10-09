package phonebook.pojo.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class LoginRequest {
    @JsonProperty("password")
    private String password;

    @JsonProperty("login")
    private String login;
}
