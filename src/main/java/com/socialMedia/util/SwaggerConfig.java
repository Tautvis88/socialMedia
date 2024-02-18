package com.socialMedia.util;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <a href="http://localhost:1111/swagger-ui/index.html">http://localhost:1111/swagger-ui/index.html</a>
 */
@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI apiInfo() {
		return new OpenAPI().info(new Info().title("Your API Documentation").version("1.0.0"));
	}

	@Bean
	public GroupedOpenApi httpApi() {
		return GroupedOpenApi.builder()
				.group("http")
				.pathsToMatch("/**")
				.build();
	}
}
