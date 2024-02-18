package com.socialMedia.validator;

import com.socialMedia.dto.CommentRequestDTO;
import com.socialMedia.exception.MandatoryFieldsMissingException;
import com.socialMedia.exception.WrongIdException;
import com.socialMedia.repository.CommentRepository;
import com.socialMedia.util.console_colors.CustomLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentRequestValidator {

	private final PostRequestValidator postRequestValidator;
	private final CommentRepository commentRepository;
	private final CustomLogger log;

	public void validateCommentAddRequest(final CommentRequestDTO commentRequestDTO) throws WrongIdException, MandatoryFieldsMissingException {
		if (commentRequestDTO == null || commentRequestDTO.getMessage() == null || commentRequestDTO.getMessage().isBlank()) {
			log.error("Request was empty or mandatory comment message field is missing.");
			throw new MandatoryFieldsMissingException("Mandatory comment message field is missing!");
		}
		postRequestValidator.validatePostIdExists(commentRequestDTO.getPostId());
	}

	public void validateCommentIdExists(final Long id) throws WrongIdException {
		if (!commentRepository.existsById(id)) {
			log.error("Comment ID does not exist in the DB.");
			throw new WrongIdException("Comment ID does not exist in the database!");
		}
	}
}
