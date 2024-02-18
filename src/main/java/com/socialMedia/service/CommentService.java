package com.socialMedia.service;

import com.socialMedia.dto.CommentRequestDTO;
import com.socialMedia.dto.CommentResponseDTO;
import com.socialMedia.dto.PostResponseDTO;
import com.socialMedia.model.Comment;
import com.socialMedia.model.Post;
import com.socialMedia.repository.CommentRepository;
import com.socialMedia.repository.PostRepository;
import com.socialMedia.util.console_colors.CustomLogger;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class CommentService {

	private final CommentMappingService commentMappingService;
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final CustomLogger log;


	public CommentResponseDTO addComment(final CommentRequestDTO commentRequestDTO) {
		Comment comment = commentMappingService.mapToEntityClass(commentRequestDTO);
		commentRepository.save(comment);
		log.info("Comment was saved successfully!");

		Optional<Comment> savedComment = commentRepository.findById(comment.getId());
		if (savedComment.isPresent()) {
			return commentMappingService.mapToResponseDTO(savedComment.get());
		} else {
			log.error("Failed to retrieve the newly added post.");
			return null;
		}
	}

	public CommentResponseDTO likeComment(final Long id) {
		Comment comment = commentRepository.findById(id).get();
		comment.setLikesCount(comment.getLikesCount() + 1);
		commentRepository.save(comment);
		return commentMappingService.mapToResponseDTO(comment);
	}

	public List<CommentResponseDTO> deleteComment(final Long commentId) {
		Comment comment = commentRepository.findById(commentId).get();
		Long postId = comment.getPost().getId();
		commentRepository.deleteById(commentId);
		log.info("Comment ID = " + commentId + " was deleted from the DB successfully!");
		return getAllPostComments(postId);
	}

	private List<CommentResponseDTO> getAllPostComments(final Long postId) {
		Post post = postRepository.findById(postId).get();
		return commentMappingService.mapToResponseDTO(post.getComments());
	}
}
