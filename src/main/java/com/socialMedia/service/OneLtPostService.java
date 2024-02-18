package com.socialMedia.service;

import com.socialMedia.controller.OneLtPostController;
import com.socialMedia.dto.OneLtPostResponseDTO;
import com.socialMedia.dto.PostResponseDTO;
import com.socialMedia.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@Data
public class OneLtPostService {

	private final RestTemplate restTemplate;
	private final String externalApiUrl;
	private final OneLtPostMappingService oneLtPostMappingService;

	public OneLtPostService(RestTemplate restTemplate, @Value("${external.api.url}") String externalApiUrl,
							OneLtPostMappingService oneLtPostMappingService) {
		this.restTemplate = restTemplate;
		this.externalApiUrl = externalApiUrl;
		this.oneLtPostMappingService = oneLtPostMappingService;
	}

	public List<PostResponseDTO> getAllUserPostsFromOneLt(final Long userId) {
		List<OneLtPostResponseDTO> userPostsFromOneLt =
				Collections.singletonList(restTemplate.getForEntity(externalApiUrl + userId, OneLtPostResponseDTO.class).getBody());
		return oneLtPostMappingService.mapToResponseDTO(userPostsFromOneLt);
	}

	public ResponseEntity<String> getAllUserPostsFromOneLt2(final Long userId) {
		return restTemplate.getForEntity(externalApiUrl + userId, String.class);
	}
}
