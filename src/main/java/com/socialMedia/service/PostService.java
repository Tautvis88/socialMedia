package com.socialMedia.service;

import com.socialMedia.dto.PostRequestDTO;
import com.socialMedia.dto.PostResponseDTO;
import com.socialMedia.model.Post;
import com.socialMedia.model.User;
import com.socialMedia.repository.PostRepository;
import com.socialMedia.repository.UserRepository;
import com.socialMedia.util.console_colors.CustomLogger;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class PostService {

	private final PostRepository postRepository;
	private final PostMappingService postMappingService;
	private final UserRepository userRepository;
	private final CustomLogger log;

	public PostResponseDTO addPost(final PostRequestDTO postRequestDTO) {
		Post post = postMappingService.mapToEntityClass(postRequestDTO);
		postRepository.save(post);
		log.info("Post was saved successfully!");

		Optional<Post> savedPost = postRepository.findById(post.getId());
		if (savedPost.isPresent()) {
			return postMappingService.mapToResponseDTO(savedPost.get());
		} else {
			log.error("Failed to retrieve the newly added post.");
			return null;
		}
	}

	public PostResponseDTO likePost(final Long id) {
		Post post = postRepository.findById(id).get();
		post.setLikesCount(post.getLikesCount() + 1);
		postRepository.save(post);
		return postMappingService.mapToResponseDTO(post);
	}

	public List<PostResponseDTO> deletePost(final Long id) {
		Optional<Post> post = postRepository.findById(id);
		Long userId = post.get().getUser().getId();
		postRepository.deleteById(id);
		log.info("Post ID = " + id + " was deleted from the DB successfully!");
		return getAllUserPosts(userId);
	}

	public List<PostResponseDTO> getAllUserPosts(final Long id) {
		Optional<User> user = userRepository.findById(id);
		return postMappingService.mapToResponseDTO(user.get().getPosts());
	}
}
