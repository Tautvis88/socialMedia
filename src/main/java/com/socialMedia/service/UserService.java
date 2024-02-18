package com.socialMedia.service;

import com.socialMedia.dto.UserRequestDTO;
import com.socialMedia.dto.UserResponseDTO;
import com.socialMedia.exception.NoUsersFoundException;
import com.socialMedia.model.User;
import com.socialMedia.repository.UserRepository;
import com.socialMedia.util.console_colors.CustomLogger;
import lombok.Data;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class UserService {

	private final UserRepository userRepository;
	private final CacheManager cacheManager;
	private final Cache userCache;
	private final UserMappingService userMappingService;
	private final CustomLogger log;

	public UserService(UserRepository userRepository, CacheManager cacheManager, UserMappingService userMappingService,
					   CustomLogger log) {
		this.userRepository = userRepository;
		this.cacheManager = cacheManager;
		this.userCache = cacheManager.getCache("userCache");
		this.userMappingService = userMappingService;
		this.log = log;
	}

	public List<User> getAllUsers() throws NoUsersFoundException {
		log.info("Looking for users in the DB...");
		var users = userRepository.findAll();

		if (users.isEmpty()) {
			log.error("No users were found in the DB!");
			throw new NoUsersFoundException("No users were found!");
		}
		log.info(users.size() + " users were found in the DB.");
		return users;
	}

	public User getUserById(final Long id) {
		log.info("Looking for user ID = " + id);
		if (userCache != null && userCache.get(id) != null) {
			log.info("User was found in the cache. Skipping DB call!");
			return (User) userCache.get(id).get();
		}
		var user = userRepository.findById(id);

		if (userCache != null) {
			userCache.put(id, user.get());
		}
		return user.get();
	}

	public List<UserResponseDTO> addUser(final UserRequestDTO userRequestDTO) {
		User user = userMappingService.mapToEntityClass(userRequestDTO);
		userRepository.save(user);
		log.info("User was saved successfully!");
		var allUsers = userRepository.findAll();
		return userMappingService.mapToResponseDTO(allUsers);
	}

	public List<UserResponseDTO> deleteUser(final Long id) {
		userRepository.deleteById(id);
		log.info("User was deleted from the database successfully!");
		var allUsers = userRepository.findAll();
		return userMappingService.mapToResponseDTO(allUsers);
	}
}
