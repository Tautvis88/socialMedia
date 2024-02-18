package com.socialMedia.validator;

import com.socialMedia.dto.PostRequestDTO;
import com.socialMedia.exception.MandatoryFieldsMissingException;
import com.socialMedia.exception.WrongIdException;
import com.socialMedia.repository.PostRepository;
import com.socialMedia.util.console_colors.CustomLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostRequestValidator {

	private final PostRepository postRepository;
	private final UserRequestValidator userRequestValidator;
	private final CustomLogger log;


	public void validatePostAddRequest(final PostRequestDTO postRequestDTO) throws WrongIdException, MandatoryFieldsMissingException {
		if (postRequestDTO == null || postRequestDTO.getMessage() == null || postRequestDTO.getMessage().isBlank()) {
			log.error("Request was empty or mandatory post message field is missing.");
			throw new MandatoryFieldsMissingException("Mandatory post message field is missing!");
		}
		userRequestValidator.validateUserIdExists(postRequestDTO.getUserId());
	}

	public void validatePostIdExists(final Long id) throws WrongIdException {
		if (!postRepository.existsById(id)) {
			log.error("Post ID does not exist in the DB.");
			throw new WrongIdException("Post ID does not exist in the database!");
		}
	}
}
