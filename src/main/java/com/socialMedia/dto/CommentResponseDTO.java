package com.socialMedia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDTO {

	private Long commentId;
	private String commentMessage;
	private Integer commentLikes;
	private String createdAt;
}
