package com.socialMedia.service;

import com.socialMedia.dto.OneLtPostResponseDTO;
import com.socialMedia.dto.PostRequestDTO;
import com.socialMedia.dto.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OneLtPostMappingService {

	public List<PostResponseDTO> mapToResponseDTO(final List<OneLtPostResponseDTO> userOneLtPosts) {
		List<PostResponseDTO> mappedOneLtPosts = new ArrayList<>();
		for (OneLtPostResponseDTO userOneLtPost : userOneLtPosts) {
			PostResponseDTO dto = new PostResponseDTO();
			dto.setUserId(userOneLtPost.getUserId());
			dto.setUserName(userOneLtPost.getUserName());
			dto.setPostMessage(userOneLtPost.getPostMessage());
			mappedOneLtPosts.add(dto);
		}
		return mappedOneLtPosts;
	}
}
