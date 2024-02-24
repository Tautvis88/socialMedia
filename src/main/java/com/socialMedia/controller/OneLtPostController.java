package com.socialMedia.controller;

import com.socialMedia.dto.PostResponseDTO;
import com.socialMedia.service.OneLtPostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OneLtPostController {

	private final OneLtPostService oneLtPostService;

	@Operation(summary = "Get all user Posts from the external API")
	@GetMapping("/getDataFromExternalAPI/{userId}")
	public ResponseEntity<?> getDataFromExternalAPI2(@PathVariable final Long userId) {
		try {
			final var userPostsFromOneLt = oneLtPostService.getAllUserPostsFromOneLt(userId);
			return ResponseEntity.ok().body(userPostsFromOneLt);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
