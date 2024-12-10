package cat.institutmarianao.sailing.ws.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import cat.institutmarianao.sailing.ws.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/* JSON annotations */
/*
 * Maps JSON data to Employee, Technician or Supervisor instance depending on
 * property role
 */

/* Lombok */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "role", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = AdminDto.class, name = User.ADMIN),
		@JsonSubTypes.Type(value = ClientDto.class, name = User.CLIENT) })
public abstract class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/* Validation */
	/* Lombok */
	@NonNull
	@EqualsAndHashCode.Include
	protected String username;

	/* Validation */
	/* Lombok */
	@NonNull
	protected String password;

	/* Validation */
	protected User.Role role;
}
