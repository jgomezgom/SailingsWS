package cat.institutmarianao.sailing.ws.model.dto;

import java.io.Serializable;

import cat.institutmarianao.sailing.ws.model.User.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClientDto extends UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MIN_FULL_NAME = 3;
	public static final int MAX_FULL_NAME = 100;

	/* Validation */
	@NotBlank
	protected String fullName;

	/* Validation */
	@NotBlank
	protected String phone;

	public ClientDto(String username, String password, String fullName, String phone) {
		super(username, password);
		this.fullName = fullName;
		this.phone = phone;
		role = Role.ADMIN;
	}
}
