package common.model.entieties.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class UserRegisterDto {

	private final String message = "Pole nie moze byc puste!";

	@NotNull(message = message)
	@NotEmpty(message = message)
	@NotBlank(message = message)
	private String login;
	@NotNull(message = message)
	@NotEmpty(message = message)
	@NotBlank(message = message)
	private String name;
	@NotNull(message = message)
	@NotEmpty(message = message)
	@NotBlank(message = message)
	private String surname;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
