package com.socialMedia.controller;

import com.socialMedia.exception.WrongIdException;
import com.socialMedia.service.CombinedPostService;
import com.socialMedia.validator.UserRequestValidator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/multi-source-posts")
@RequiredArgsConstructor
public class CombinedPostController {

	private final CombinedPostService combinedPostService;
	private final UserRequestValidator userRequestValidator;


	@Operation(summary = "Get all posts by user ID from different systems")
	@GetMapping("/{userId}")
	public ResponseEntity<?> getAllPostsByUserIdFromDifferentSystems(@PathVariable final Long userId) {
		try {
			userRequestValidator.validateUserIdExists(userId);
			final var posts = combinedPostService.getAllPostsByUserIdFromDifferentSystems(userId);
			return ResponseEntity.ok().body(posts);
		} catch (WrongIdException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
