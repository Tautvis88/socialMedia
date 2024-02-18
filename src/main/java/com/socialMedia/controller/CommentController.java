package com.socialMedia.controller;

import com.socialMedia.dto.CommentRequestDTO;
import com.socialMedia.exception.MandatoryFieldsMissingException;
import com.socialMedia.exception.WrongIdException;
import com.socialMedia.service.CommentService;
import com.socialMedia.validator.CommentRequestValidator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

	private final CommentRequestValidator commentRequestValidator;
	private final CommentService commentService;

	@Operation(summary = "Add new Comment to the Post.")
	@PostMapping("/")
	public ResponseEntity<?> addComment(@RequestBody final CommentRequestDTO commentRequestDTO) {
		try {
			commentRequestValidator.validateCommentAddRequest(commentRequestDTO);
			final var comment = commentService.addComment(commentRequestDTO);
			return ResponseEntity.ok().body(comment);
		} catch (WrongIdException | MandatoryFieldsMissingException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Operation(summary = "Add like to the Comment.")
	@PostMapping("/{id}")
	public ResponseEntity<?> likeComment(@PathVariable final Long id) {
		try {
			commentRequestValidator.validateCommentIdExists(id);
			var likedComment = commentService.likeComment(id);
			return ResponseEntity.ok().body(likedComment);
		} catch (WrongIdException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Operation(summary = "Delete the Comment.")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable final Long id) {
		try {
			commentRequestValidator.validateCommentIdExists(id);
			final var commentsLeft = commentService.deleteComment(id);
			return ResponseEntity.ok().body(commentsLeft);
		} catch (WrongIdException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
