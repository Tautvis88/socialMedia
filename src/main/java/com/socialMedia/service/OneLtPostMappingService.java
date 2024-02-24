package com.socialMedia.service;

import com.socialMedia.dto.OneLtPostResponseDTO;
import com.socialMedia.dto.PostRequestDTO;
import com.socialMedia.dto.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.socialMedia.util.JsonStringParser.parseJsonStringToList;

@Service
@RequiredArgsConstructor
public class OneLtPostMappingService {

	public List<PostResponseDTO> mapToResponseDTO(final String jsonString) {
		return parseJsonStringToList(jsonString);
	}
}
