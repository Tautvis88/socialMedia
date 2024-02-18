package com.socialMedia.service;

import com.socialMedia.dto.PostResponseDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Data
public class CombinedPostService {

	private final PostService postService;
	private final RestTemplate restTemplate;
	private final String externalApiUrl;

	public CombinedPostService(PostService postService, RestTemplate restTemplate, @Value("${external.api.url}") String externalApiUrl) {
		this.postService = postService;
		this.restTemplate = restTemplate;
		this.externalApiUrl = externalApiUrl;
	}

	public List<PostResponseDTO> getAllPostsByUserIdFromDifferentSystems(final Long userId) {
		List<PostResponseDTO> combinedPosts = new ArrayList<>();

		List<PostResponseDTO> currentSystemPosts = postService.getAllUserPosts(userId);
		combinedPosts.addAll(currentSystemPosts);

		var externalSystemPosts = restTemplate.getForObject(externalApiUrl + userId, String.class);
//		combinedPosts.addAll(Arrays.asList(externalSystemPosts));

		return null;
	}
}
