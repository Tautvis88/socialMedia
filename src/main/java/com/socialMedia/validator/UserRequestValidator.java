package com.socialMedia.validator;

import com.socialMedia.dto.UserRequestDTO;
import com.socialMedia.exception.MandatoryFieldsMissingException;
import com.socialMedia.exception.WrongIdException;
import com.socialMedia.repository.UserRepository;
import com.socialMedia.util.console_colors.CustomLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRequestValidator {

	private final UserRepository userRepository;
	private final CustomLogger log;

	public void validateUserRequest(final UserRequestDTO userRequestDTO) throws MandatoryFieldsMissingException {
		if (isRequestInvalid(userRequestDTO)) {
			log.error("Request was empty or mandatory name field is missing.");
			throw new MandatoryFieldsMissingException("Mandatory name field is missing!");
		}
	}

	public void validateUserIdExists(final Long id) throws WrongIdException {
		if (userRepository.existsById(id)) {
			return;
		}
		log.error("User ID does not exist in the DB");
		throw new WrongIdException("ID does not exist in the database!");
	}

	private boolean isRequestInvalid(final UserRequestDTO userRequestDTO) {
		return userRequestDTO == null
				|| userRequestDTO.getName() == null
				|| userRequestDTO.getName().isBlank()
				|| userRequestDTO.getAge() == null;
	}
}
