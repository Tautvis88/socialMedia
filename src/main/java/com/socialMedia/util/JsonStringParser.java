package com.socialMedia.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialMedia.dto.PostResponseDTO;

import java.io.IOException;
import java.util.List;

public class JsonStringParser {

	public static List<PostResponseDTO> parseJsonStringToList(String jsonString) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(jsonString, new TypeReference<List<PostResponseDTO>>() {});
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
