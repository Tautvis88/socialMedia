package com.socialMedia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDTO {

	@JsonProperty("userId")
	private Long userId;

	@JsonProperty("userName")
	private String userName;

	@JsonProperty("postId")
	private Long postId;
	private Integer likesCount;

	@JsonProperty("postMessage")
	private String postMessage;

	private String createdAt;
}
