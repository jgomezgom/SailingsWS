package cat.institutmarianao.sailing.ws.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cat.institutmarianao.sailing.ws.model.User;
import cat.institutmarianao.sailing.ws.model.User.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface UserService {

	Page<User> findAll(Role[] roles, String fullName, Pageable pageable);

	User getByUsername(@NotBlank String username);

	User save(@NotNull @Valid User user);

	User update(@NotNull @Valid User user);

	void deleteByUsername(@NotBlank String username);

	boolean existsById(@NotBlank String username);
}