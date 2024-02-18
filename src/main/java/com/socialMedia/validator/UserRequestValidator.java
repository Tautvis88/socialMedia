package com.socialMedia.validator;

import com.socialMedia.dto.UserRequestDTO;
import com.socialMedia.exception.MandatoryFieldsMissingException;
import com.socialMedia.exception.WrongIdException;
import com.socialMedia.model.User;
import com.socialMedia.repository.UserRepository;
import com.socialMedia.util.console_colors.CustomLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRequestValidator {

	private final UserRepository userRepository;
	private final CustomLogger log;

	public void validateUserRequest(final UserRequestDTO userRequestDTO) throws MandatoryFieldsMissingException {
		if (userRequestDTO == null
				|| userRequestDTO.getName() == null
				|| userRequestDTO.getName().isBlank()
				|| userRequestDTO.getAge() == null) {
			log.error("Request was empty or mandatory name field is missing.");
			throw new MandatoryFieldsMissingException("Mandatory name field is missing!");
		}
	}

	public void validateUserIdExists(final Long id) throws WrongIdException {
		if (!userRepository.existsById(id)) {
			log.error("User ID does not exist in the DB");
			throw new WrongIdException("ID does not exist in the database!");
		}
	}


}
