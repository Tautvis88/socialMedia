package com.socialMedia.controller;

import com.socialMedia.dto.UserRequestDTO;
import com.socialMedia.exception.MandatoryFieldsMissingException;
import com.socialMedia.exception.NoUsersFoundException;
import com.socialMedia.exception.WrongIdException;
import com.socialMedia.model.User;
import com.socialMedia.service.UserService;
import com.socialMedia.util.console_colors.CustomLogger;
import com.socialMedia.validator.UserRequestValidator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final UserRequestValidator userRequestValidator;
	private final CustomLogger log;

	@Operation(summary = "Get all Users, Posts and Comments.")
	@GetMapping("/")
	public ResponseEntity<?> findAll() {
		try {
			var users = userService.getAllUsers();
			return ResponseEntity.status(HttpStatus.OK).body(users);
		} catch (NoUsersFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			log.error("Unexpected exception happened!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected exception!");
		}
	}

	@Operation(summary = "Create a new User.")
	@PostMapping("/")
	public ResponseEntity<?> addUser(@RequestBody final UserRequestDTO userRequestDTO) {
		try {
			userRequestValidator.validateUserRequest(userRequestDTO);
			final var doctors = userService.addUser(userRequestDTO);
			return ResponseEntity.ok().body(doctors);
		} catch (MandatoryFieldsMissingException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Operation(summary = "Delete the User.")
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable final Long userId) {
		try {
			userRequestValidator.validateUserIdExists(userId);
			final var usersLeft = userService.deleteUser(userId);
			return ResponseEntity.ok().body(usersLeft);
		} catch (WrongIdException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
