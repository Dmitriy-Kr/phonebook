package phonebook.pojo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum ContactType {
    @JsonProperty("email")
    EMAIL("e-mail"),
    @JsonProperty("phone")
    PHONE("телефон");

    private String name;

    ContactType(String name) {
        this.name = name;
    }
}
