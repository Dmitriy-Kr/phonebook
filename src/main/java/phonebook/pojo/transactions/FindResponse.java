package phonebook.pojo.transactions;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import phonebook.pojo.entity.Contact;

@Getter
public class FindResponse{

	@JsonProperty("contacts")
	private List<Contact> contacts;

	@JsonProperty("status")
	private String status;
}