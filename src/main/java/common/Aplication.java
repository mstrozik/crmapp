package common;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import common.model.entieties.User;
import common.model.entieties.enums.UserRole;
import common.model.service.UserService;

@SpringBootApplication
public class Aplication {

	public static void main(String[] args) {
		SpringApplication.run(Aplication.class, args);
	}

	@Bean
	public CommandLineRunner createUserWithAdminRights(UserService service) {
		return (args) -> {

			User admin = new User(1l, "mateusz", "123");
			admin.setName("admin");
			admin.setSurname("admin");
			admin.setUserRole(UserRole.ADMIN);
			admin.setEmail("mateusz.strozik@ericsson.com");
			service.createUserAccount(admin);

		};
	}
}
