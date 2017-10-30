package common.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import common.model.entieties.User;
import common.model.entieties.dto.UserCreateDto;
import common.model.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	public User findByLogin(String login) {
		return repository.findByLogin(login);
	}

	public void deleteById(Long id) {
		repository.delete(id);
	}

	public void deleteUser(User user) {
		repository.delete(user);
	}

	public User findById(Long id) {
		return repository.findById(id);
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User updateUser(User registered) {
		return repository.save(registered);
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return repository.findByLogin(login);
	}

	public User createUserAccount(UserCreateDto userCreateDto) {
		if (userExists(userCreateDto.getLogin())) {
			return null;
		}
		User user = new User();
		user.setLogin(userCreateDto.getLogin());
		user.setName(userCreateDto.getName());
		user.setSurname(userCreateDto.getSurname());
		user.setPassword(userCreateDto.getPassword());
		user.setEmail(userCreateDto.getEmail());
		user.setUserRole(userCreateDto.getUserRole());
		return repository.save(user);
	}

	public User createUserAccount(User user) {
		if (userExists(user.getLogin())) {
			return null;
		}
		return repository.save(user);
	}

	public boolean userExists(String login) {
		User user = repository.findByLogin(login);
		if (user != null) {
			return true;
		}
		return false;
	}

}
