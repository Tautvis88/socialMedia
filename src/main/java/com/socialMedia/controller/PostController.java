package com.socialMedia.controller;

import com.socialMedia.dto.PostRequestDTO;
import com.socialMedia.exception.MandatoryFieldsMissingException;
import com.socialMedia.exception.WrongIdException;
import com.socialMedia.service.PostService;
import com.socialMedia.util.console_colors.CustomLogger;
import com.socialMedia.validator.PostRequestValidator;
import com.socialMedia.validator.UserRequestValidator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	private final PostRequestValidator postRequestValidator;
	private final UserRequestValidator userRequestValidator;
	private final CustomLogger log;

	@Operation(summary = "Get all user Posts.")
	@GetMapping("/{userId}")
	public ResponseEntity<?> getAllPostsByUserId(@PathVariable final Long userId) {
		try {
			userRequestValidator.validateUserIdExists(userId);
			final var userPosts = postService.getAllUserPosts(userId);
			return ResponseEntity.ok().body(userPosts);
		} catch (WrongIdException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Operation(summary = "Create a new Post.")
	@PostMapping("/")
	public ResponseEntity<?> addPost(@RequestBody final PostRequestDTO postRequestDTO) {
		try {
			postRequestValidator.validatePostAddRequest(postRequestDTO);
			final var post = postService.addPost(postRequestDTO);
			return ResponseEntity.ok().body(post);
		} catch (MandatoryFieldsMissingException | WrongIdException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Operation(summary = "Add like to the Post.")
	@PostMapping("/{id}")
	public ResponseEntity<?> likePost(@PathVariable final Long id) {
		try {
			postRequestValidator.validatePostIdExists(id);
			var likedPost = postService.likePost(id);
			return ResponseEntity.ok().body(likedPost);
		} catch (WrongIdException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Operation(summary = "Delete the Post.")
	@DeleteMapping("/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable final Long postId) {
		try {
			postRequestValidator.validatePostIdExists(postId);
			final var postsLeft = postService.deletePost(postId);
			return ResponseEntity.ok().body(postsLeft);
		} catch (WrongIdException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
