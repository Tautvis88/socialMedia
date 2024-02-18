package com.socialMedia.service;

import com.socialMedia.dto.PostRequestDTO;
import com.socialMedia.dto.PostResponseDTO;
import com.socialMedia.model.Post;
import com.socialMedia.model.User;
import com.socialMedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.socialMedia.util.DateTimeFormatUtil.format;

@Service
@RequiredArgsConstructor
public class PostMappingService {

	private final UserRepository userRepository;

	public Post mapToEntityClass(final PostRequestDTO postRequestDTO) {
		Long userId = postRequestDTO.getUserId();
		Optional<User> user = userRepository.findById(userId);    // o jei naudočiau userService.getById()??
		return Post.builder()
				.postMessage(postRequestDTO.getMessage())
				.user(user.get())        // kodėl get braukia?
				.likesCount(0)
				.build();
	}

	public List<PostResponseDTO> mapToResponseDTO(final List<Post> allPosts) {
		List<PostResponseDTO> mappedPosts = new ArrayList<>();

		for (Post post : allPosts) {
			PostResponseDTO dto = new PostResponseDTO();
			dto.setUserId(post.getUser().getId());
			dto.setUserName(post.getUser().getName());
			dto.setPostId(post.getId());
			dto.setLikesCount(post.getLikesCount());
			dto.setPostMessage(post.getPostMessage());
			dto.setCreatedAt(format(post.getCreatedAt()));
			mappedPosts.add(dto);
		}
		return mappedPosts;
	}

	public PostResponseDTO mapToResponseDTO(final Post post) {
		PostResponseDTO dto = new PostResponseDTO();
		dto.setUserId(post.getUser().getId());
		dto.setUserName(post.getUser().getName());
		dto.setPostId(post.getId());
		dto.setLikesCount(post.getLikesCount());
		dto.setPostMessage(post.getPostMessage());
		dto.setCreatedAt(format(post.getCreatedAt()));
		return dto;
	}

}
