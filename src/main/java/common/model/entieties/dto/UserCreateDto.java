package common.model.entieties.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import common.model.entieties.enums.UserRole;

public class UserCreateDto {

	private final String message = "Pole nie moze byc puste!";

	@NotNull(message = message)
	@NotEmpty(message = message)
	@NotBlank(message = message)
	private String login;
	@NotNull(message = message)
	@NotEmpty(message = message)
	@NotBlank(message = message)
	private String password;
	@NotNull(message = message)
	@NotEmpty(message = message)
	@NotBlank(message = message)
	private String name;
	@NotNull(message = message)
	@NotEmpty(message = message)
	@NotBlank
	private String surname;
	private String email;
	private UserRole userRole = UserRole.USER;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}
