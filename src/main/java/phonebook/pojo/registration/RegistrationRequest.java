package phonebook.pojo.registration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Setter
@Accessors(chain = true)
public class RegistrationRequest{

	@JsonProperty("password")
	private String password;

	@JsonProperty("date_born")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateBorn;

	@JsonProperty("login")
	private String login;
}