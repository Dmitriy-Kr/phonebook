package phonebook.pojo.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ErrorResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("error")
    private String error;
}
