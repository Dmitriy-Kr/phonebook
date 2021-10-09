package phonebook.pojo.transactions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FindRequest{

	@JsonProperty("name")
	private String name;

	@JsonProperty("value")
	private String value;
}