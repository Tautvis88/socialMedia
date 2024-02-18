package com.socialMedia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OneLtPostResponseDTO {

	private Long userId;
	private String userName;
	private Long postId;
	private String postMessage;

}
