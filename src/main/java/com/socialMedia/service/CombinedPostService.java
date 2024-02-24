package com.socialMedia.service;

import com.socialMedia.dto.PostRequestDTO;
import com.socialMedia.dto.PostResponseDTO;
import com.socialMedia.util.JsonStringParser;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.socialMedia.util.JsonStringParser.parseJsonStringToList;

@Service
@Data
public class CombinedPostService {

	private final PostService postService;
	private final RestTemplate restTemplate;
	private final String externalApiUrl;
	private final OneLtPostService oneLtPostService;

	public CombinedPostService(PostService postService, RestTemplate restTemplate,
							   @Value("${external.api.url}") String externalApiUrl, OneLtPostService oneLtPostService) {
		this.postService = postService;
		this.restTemplate = restTemplate;
		this.externalApiUrl = externalApiUrl;
		this.oneLtPostService = oneLtPostService;
	}

	public List<PostResponseDTO> getAllPostsByUserIdFromDifferentSystems(final Long userId) {

		var currentSystemPosts = postService.getAllUserPosts(userId);
		var externalSystemPosts = oneLtPostService.getAllUserPostsFromOneLt(userId);

		currentSystemPosts.addAll(externalSystemPosts);

		return currentSystemPosts;
	}

}
