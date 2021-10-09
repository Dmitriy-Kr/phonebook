package phonebook.pojo.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LoginResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("token")
    private String message;
}
