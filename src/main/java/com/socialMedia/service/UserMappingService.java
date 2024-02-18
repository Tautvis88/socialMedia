package com.socialMedia.service;


import com.socialMedia.dto.UserRequestDTO;
import com.socialMedia.dto.UserResponseDTO;
import com.socialMedia.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.socialMedia.util.DateTimeFormatUtil.format;

@Service
public class UserMappingService {

	public User mapToEntityClass(final UserRequestDTO userRequestDTO) {
		return User.builder()
				.name(userRequestDTO.getName())
				.age(userRequestDTO.getAge())
				.build();
	}

	public List<UserResponseDTO> mapToResponseDTO(List<User> allUsers) {
		List<UserResponseDTO> mappedUsers = new ArrayList<>();

		for (User user : allUsers) {
			UserResponseDTO dto = new UserResponseDTO();
			dto.setId(user.getId());
			dto.setUserName(user.getName());
			dto.setUserAge(user.getAge());
			dto.setIsAdult(user.getAge() >= 18);
			dto.setCreatedAt(format(user.getCreatedAt()));
			mappedUsers.add(dto);
		}
		return mappedUsers;
	}

}
