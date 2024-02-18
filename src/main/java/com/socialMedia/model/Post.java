package com.socialMedia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 100)
	private String postMessage;
	private Integer likesCount;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Comment> comments;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@PrePersist
	private void onCreate() {
		createdAt = LocalDateTime.now();
	}

}
