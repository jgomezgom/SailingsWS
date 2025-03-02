package cat.institutmarianao.sailing.ws.model;

import java.io.Serializable;

import cat.institutmarianao.sailing.ws.validation.groups.OnUserCreate;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/* JPA annotations */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)

/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/* Values for role - MUST be constants (can not be enums) */
	public static final String ADMIN = "ADMIN";
	public static final String CLIENT = "CLIENT";

	public enum Role {
		ADMIN, CLIENT
	}

	public static final int MIN_USERNAME = 2;
	public static final int MAX_USERNAME = 25;
	public static final int MIN_PASSWORD = 10;

	/* Validation */
	/* JPA */
	@Id
	/* Lombok */
	@EqualsAndHashCode.Include
	@NotBlank
	@Size(min = MIN_USERNAME, max = MAX_USERNAME)
	protected String username;

	/* Validation */
	/* JPA */
	@NotBlank(groups =  OnUserCreate.class )
	@Size(min = MIN_PASSWORD)
	@Column(nullable = false)
	protected String password;

	/* Validation */
	/* JPA */
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, insertable = false, updatable = false)
	protected Role role;
}
