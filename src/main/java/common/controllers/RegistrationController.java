package common.controllers;

import java.util.Properties;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import common.model.entieties.dto.UserRegisterDto;
import common.model.service.EmailManager;
import common.model.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	UserService userService;

	@Autowired
	private EmailManager emailManager;

	private static final int TIMEOUT = 5000;
	private static final String adminMail = "mateusz.strozik@ericsson.com";

	@RequestMapping(value = "/user/registration", method = RequestMethod.POST)
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserRegisterDto userRegisterDto,
			BindingResult result) {
		if (!result.hasErrors()) {
			if (userService.userExists(userRegisterDto.getLogin())) {
				return new ModelAndView("registration", "user", userRegisterDto).addObject("reason",
						"Login " + userRegisterDto.getName() + " juz istnieje");
			}

			String contentOfMessage = getEmailRegisterMessage(userRegisterDto.getLogin(), userRegisterDto.getName(),
					userRegisterDto.getSurname(), userRegisterDto.getEmail());

			JavaMailSender mailSender = getJavaMailSender();
			String[] destination = { adminMail };
			emailManager.setMailSender(mailSender);
			emailManager.prepeareMessage("NexusTeamApplication", destination, "Registration Request", contentOfMessage);
			emailManager.send();

			return new ModelAndView("successRegister");
		}
		return new ModelAndView("registration", "user", userRegisterDto);
	}

	@RequestMapping(value = "/user/registration", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new UserRegisterDto());
		return "registration";
	}

	private String getEmailRegisterMessage(String login, String name, String surname, String email) {
		StringBuilder sb = new StringBuilder();
		sb.append("<p>Hi,<br />Please register me in artex application.</p>");
		sb.append("<p>Login: ");
		sb.append(login);
		sb.append("</p><p>Name: ");
		sb.append(name);
		sb.append("</p><p>Surname: ");
		sb.append(surname);
		sb.append("</p><p>Email: ");
		sb.append(email);
		sb.append("</p>");
		return sb.toString();
	}

	private JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setUsername("teamnexusapplication@gmail.com");
		mailSender.setPassword("neutrinoteam");
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(465);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.ssl.enable", true);
		props.put("mail.debug", true);
		props.put("mail.smtp.connectiontimeout", TIMEOUT);
		props.put("mail.smtp.timeout", TIMEOUT);
		props.put("mail.smtp.writetimeout", TIMEOUT);

		return mailSender;
	}
}
