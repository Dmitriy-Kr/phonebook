package phonebook.pojo.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import phonebook.pojo.entity.Contact;

import java.util.List;

@Getter
public class GetAllResponse {

	@JsonProperty("contacts")
	private List<Contact> contacts;

	@JsonProperty("status")
	private String status;
}