package phonebook.pojo.transactions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class StatusResponse {

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("status")
    private String status;
}
