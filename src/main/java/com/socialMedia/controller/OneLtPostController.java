package com.socialMedia.controller;

import com.socialMedia.service.OneLtPostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class OneLtPostController {

	private final OneLtPostService oneLtPostService;
	//TODO: padaryti, kad paimtų ne tik user Id, bet gal ir userName? ar gal kad patikrintų, kad userName sutampa? paklausti
//	@Operation(summary = "Get all user Posts from the external API")
//	@GetMapping("/getDataFromExternalAPI/{userId}")
//	public ResponseEntity<?> getDataFromExternalAPI(@PathVariable final Long userId) {
//		try {
//			final var userPostsFromOneLt = oneLtPostService.getAllUserPostsFromOneLt2(userId);
//			return ResponseEntity.ok().body(userPostsFromOneLt);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching data from external API");
//		}
//	}

	@Operation(summary = "Get all user Posts from the external API")
	@GetMapping("/getDataFromExternalAPI/{userId}")
	public ResponseEntity<String> getDataFromExternalAPI2(@PathVariable final Long userId) {
		try {
			ResponseEntity<String> userPostsFromOneLt2 = oneLtPostService.getAllUserPostsFromOneLt2(userId);
			System.out.println(userPostsFromOneLt2);
			return userPostsFromOneLt2;
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching data from external API");
		}
	}
}
