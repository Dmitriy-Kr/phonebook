package phonebook.pojo.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RegistrationResponse {
    @JsonProperty("status")
    private String status;
}
