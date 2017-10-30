package common.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import common.model.entieties.dto.UserCreateDto;
import common.model.service.SecurityService;
import common.model.service.UserService;

@Controller
public class AddUserController {

	@Autowired
	UserService userService;

	@Autowired
	SecurityService securityService;

	@RequestMapping(value = "/user/adduser", method = RequestMethod.GET)
	public String showAddUser(Model model) {
		model.addAttribute("user", new UserCreateDto());
		return "addUser";
	}

	@RequestMapping(value = "/user/adduser", method = RequestMethod.POST)
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserCreateDto userCreateDto,
			BindingResult result) {
		if (!result.hasErrors()) {
			if (userService.userExists(userCreateDto.getLogin())) {
				return new ModelAndView("addUser", "user", userCreateDto).addObject("reason",
						"Login " + userCreateDto.getName() + " juz istnieje");
			}
			userService.createUserAccount(userCreateDto);
			return new ModelAndView("successUserCreated");
		}
		return new ModelAndView("addUser", "user", userCreateDto);
	}
}
