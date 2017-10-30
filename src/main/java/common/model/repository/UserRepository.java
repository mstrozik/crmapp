package common.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import common.model.entieties.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByLogin(String login);

	List<User> findAll();

	User findById(Long id);

}
