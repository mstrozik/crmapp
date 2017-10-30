package common.model.entieties.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class UserLoginDto {

	private final String message = "Pole nie moze byc puste!";

	@NotNull(message = message)
	@NotEmpty(message = message)
	@NotBlank(message = message)
	private String login;
	@NotNull(message = message)
	@NotEmpty(message = message)
	@NotBlank(message = message)
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
