package com.socialMedia.service;

import com.socialMedia.dto.CommentRequestDTO;
import com.socialMedia.dto.CommentResponseDTO;
import com.socialMedia.model.Comment;
import com.socialMedia.model.Post;
import com.socialMedia.model.User;
import com.socialMedia.repository.PostRepository;
import com.socialMedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.socialMedia.util.DateTimeFormatUtil.format;

@Service
@RequiredArgsConstructor
public class CommentMappingService {

	private final UserRepository userRepository;
	private final PostRepository postRepository;

	public Comment mapToEntityClass(final CommentRequestDTO commentRequestDTO) {
		Long userId = commentRequestDTO.getUserId();
		Optional<User> user = userRepository.findById(userId);
		Long postId = commentRequestDTO.getPostId();
		Optional<Post> post = postRepository.findById(postId);
		return Comment.builder()
				.commentMessage(commentRequestDTO.getMessage())
				.likesCount(0)
				.post(post.get())
				.user(user.get())
				.build();
	}

	public CommentResponseDTO mapToResponseDTO(final Comment comment) {
		CommentResponseDTO dto = new CommentResponseDTO();
		dto.setCommentId(comment.getId());
		dto.setCommentMessage(comment.getCommentMessage());
		dto.setCommentLikes(comment.getLikesCount());
		dto.setCreatedAt(format(comment.getCreatedAt()));
		return dto;
	}

	public List<CommentResponseDTO> mapToResponseDTO(final List<Comment> comments) {
		List<CommentResponseDTO> mappedComments = new ArrayList<>();

		for (Comment comment : comments) {
			CommentResponseDTO dto = new CommentResponseDTO();
			dto.setCommentId(comment.getId());
			dto.setCommentMessage(comment.getCommentMessage());
			dto.setCommentLikes(comment.getLikesCount());
			dto.setCreatedAt(format(comment.getCreatedAt()));
			mappedComments.add(dto);
		}
		return mappedComments;
	}
}
