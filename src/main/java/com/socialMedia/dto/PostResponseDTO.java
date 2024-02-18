package com.socialMedia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDTO {

	private Long userId;
	private String userName;
	private Long postId;
	private Integer likesCount;
	private String postMessage;
	private String createdAt;
}
