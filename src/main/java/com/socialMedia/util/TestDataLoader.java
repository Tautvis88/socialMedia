package com.socialMedia.util;

import com.socialMedia.model.Comment;
import com.socialMedia.model.Post;
import com.socialMedia.model.User;
import com.socialMedia.repository.CommentRepository;
import com.socialMedia.repository.PostRepository;
import com.socialMedia.repository.UserRepository;
import com.socialMedia.util.console_colors.CustomLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class TestDataLoader implements CommandLineRunner {

	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;
	private final CustomLogger log;

	/**
	 * <a href="http://localhost:1111/h2-console">http://localhost:1111/h2-console</a>
	 * <br><br>
	 * SELECT * FROM USER_NAME;<br>
	 * SELECT * FROM POST;<br>
	 * SELECT * FROM COMMENT;
	 */
	@Override
	public void run(String... args) throws Exception {
		log.info("CREATING TEST DATA:");

		// Create Users
		var user1 = User.builder()
				.name("John Doe")
				.age(30)
				.build();
		var user2 = User.builder()
				.name("Jane Smith")
				.age(25)
				.build();
		var user3 = User.builder()
				.name("Alex Johnson")
				.age(28)
				.build();

		// Create Posts
		var post1 = Post.builder()
				.likesCount(100)
				.postMessage("My first post")
				.user(user1)
				.build();
		var post2 = Post.builder()
				.likesCount(50)
				.postMessage("My second post")
				.user(user2)
				.build();
		var post3 = Post.builder()
				.likesCount(50)
				.postMessage("My third post")
				.user(user3)
				.build();

		user1.setPosts(Arrays.asList(post1));
		user2.setPosts(Arrays.asList(post2));
		user3.setPosts(Arrays.asList(post3));

		// Create Comments
		var comment1 = Comment.builder()
				.likesCount(10)
				.commentMessage("Great post!")
				.post(post1)
				.user(user1)
				.build();
		var comment2 = Comment.builder()
				.likesCount(5)
				.commentMessage("Really enjoyed this.")
				.post(post2)
				.user(user2)
				.build();
		var comment3 = Comment.builder()
				.likesCount(3)
				.commentMessage("Interesting perspective.")
				.post(post3)
				.user(user3)
				.build();

		user1.setComments(Arrays.asList(comment1));
		user2.setComments(Arrays.asList(comment2));
		user3.setComments(Arrays.asList(comment3));

		post1.setComments(Arrays.asList(comment1));
		post2.setComments(Arrays.asList(comment2));
		post3.setComments(Arrays.asList(comment3));

		log.info("Saving test users to the database...");
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
		log.info("TEST USERS CREATION COMPLETED.");

		log.info("Saving test posts to the database...");
		postRepository.saveAll(Arrays.asList(post1, post2, post3));
		log.info("TEST POSTS CREATION COMPLETED.");

		log.info("Saving test comments to the database...");
		commentRepository.saveAll(Arrays.asList(comment1, comment2, comment3));
		log.info("TEST COMMENTS CREATION COMPLETED.");

		log.info("DATA CREATION COMPLETED.");

	}
}
