package phonebook.pojo.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contact implements Serializable {

    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private ContactType contactType;

    @JsonProperty("value")
//    @JacksonXmlProperty(localName = "phoneNumber")
    private String value;

    public Contact() {
    }

    public Contact(String id, String name, ContactType contactType, String value) {
        this.id = id;
        this.name = name;
        this.contactType = contactType;
        this.value = value;
    }
}
