package cat.institutmarianao.sailing.ws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.sailing.ws.exception.NotFoundException;
import cat.institutmarianao.sailing.ws.model.Client;
import cat.institutmarianao.sailing.ws.model.User;
import cat.institutmarianao.sailing.ws.model.User.Role;
import cat.institutmarianao.sailing.ws.repository.UserRepository;
import cat.institutmarianao.sailing.ws.service.UserService;
import cat.institutmarianao.sailing.ws.specifications.UserWithFullName;
import cat.institutmarianao.sailing.ws.specifications.UserWithRole;
import cat.institutmarianao.sailing.ws.validation.groups.OnUserCreate;
import cat.institutmarianao.sailing.ws.validation.groups.OnUserUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll(Role[] roles, String fullName) {
		Specification<User> spec = Specification.where(new UserWithRole(roles)).and(new UserWithFullName(fullName));
		return userRepository.findAll(spec);
	}

	@Override
	public User getByUsername(@NotBlank String username) {
		return userRepository.findById(username).orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean existsById(@NotBlank String username) {
		return userRepository.existsById(username);
	}

	@Override
	@Validated(OnUserCreate.class)
	public User save(@NotNull @Valid User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	@Validated(OnUserUpdate.class)
	public User update(@NotNull @Valid User user) {
		User dbUser = getByUsername(user.getUsername());

		if (user.getPassword() != null)
			dbUser.setPassword(user.getPassword());

		if (user instanceof Client client && dbUser instanceof Client dbClient) {
			if (client.getFullName() != null)
				dbClient.setFullName(client.getFullName());
			if (client.getPhone() != null)
				dbClient.setPhone(client.getPhone());
		}

		return userRepository.saveAndFlush(dbUser);
	}

	@Override
	public void deleteByUsername(@NotBlank String username) {
		userRepository.deleteById(username);
	}

}
