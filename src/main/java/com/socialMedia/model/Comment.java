package com.socialMedia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 50)
	private String commentMessage;
	private Integer likesCount;
	@ManyToOne
	@JoinColumn(name = "post_id")
	@JsonBackReference
	private Post post;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@PrePersist
	private void onCreate() {
		createdAt = LocalDateTime.now();
	}
}
