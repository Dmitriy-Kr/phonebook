package phonebook.pojo.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactsItem{

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("type")
	private String type;

	@JsonProperty("value")
	private String value;
}