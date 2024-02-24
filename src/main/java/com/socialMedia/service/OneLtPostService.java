package com.socialMedia.service;

import com.socialMedia.dto.PostResponseDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
		var userPostsFromOneLt = restTemplate.getForObject(externalApiUrl + userId, String.class);

		return oneLtPostMappingService.mapToResponseDTO(userPostsFromOneLt);
	}
}
