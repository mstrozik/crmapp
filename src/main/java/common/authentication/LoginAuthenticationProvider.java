package common.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import common.model.service.UserService;
import common.model.entieties.User;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserService service;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = (String) auth.getCredentials();
		User user = service.findByLogin(username);

		if (user == null || !user.getLogin().equalsIgnoreCase(username)) {
			throw new BadCredentialsException("username not found");
		}
		if (!user.getPassword().equals(password)) {
			throw new BadCredentialsException("wrong password");
		}

		return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
