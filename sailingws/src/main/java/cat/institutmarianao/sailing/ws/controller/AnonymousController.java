package cat.institutmarianao.sailing.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cat.institutmarianao.sailing.ws.exception.ForbiddenException;
import cat.institutmarianao.sailing.ws.model.User;
import cat.institutmarianao.sailing.ws.model.dto.AdminDto;
import cat.institutmarianao.sailing.ws.model.dto.UserDto;
import cat.institutmarianao.sailing.ws.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;

@Tag(name = "Anonymous", description = "Anonymous API")

@RestController
@RequestMapping("/signup")
public class AnonymousController {

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MessageSource messageSource;

	@Operation(summary = "Signup a user", description = "Signups a user in the database. The response is the stored user from the database.")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }, description = "User signuped ok")
	@ApiResponse(responseCode = "500", content = {
			@Content() }, description = "Error signuping the user. See response body for more details")
	@PostMapping
	public @ResponseBody UserDto save(@RequestBody @NotNull UserDto userDto) {

		if (userService.existsById(userDto.getUsername()))
			throw new ConstraintViolationException(messageSource.getMessage("error.UserService.user.already.exists",
					new Object[] { userDto.getUsername() }, LocaleContextHolder.getLocale()), null);

		if (userDto instanceof AdminDto)
			throw new ForbiddenException(
					messageSource.getMessage("error.UserService.admin.create", null, LocaleContextHolder.getLocale()));

		return conversionService.convert(userService.save(convertAndEncodePassword(userDto)), UserDto.class);
	}

	private User convertAndEncodePassword(UserDto userDto) {
		String rawPassword = userDto.getPassword();
		if (rawPassword != null)
			userDto.setPassword(passwordEncoder.encode(rawPassword));
		return conversionService.convert(userDto, User.class);
	}

}
