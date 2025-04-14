package com.example.agri;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

	@Value("${default.server.url:http://localhost:8087}")
	private String defaultUrl;

	@Bean
	public OpenAPI customOpenAPI() {

		// Server configuration
		Server devServer = new Server();
		devServer.setUrl(defaultUrl);
		devServer.setDescription("Development server URL");

		// API Information
		Info apiInfo = new Info()
				.title("BookHealthServiceOnline API")
				.version("1.0")
				.description("API documentation for BookHealthServiceOnline");

		// Security configuration for JWT
		Components components = new Components()
				.addSecuritySchemes("bearer-jwt",
						new SecurityScheme()
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT"));

		SecurityRequirement securityRequirement = new SecurityRequirement()
				.addList("bearer-jwt");

		// Return OpenAPI configuration
		return new OpenAPI()
				.info(apiInfo)
				.servers(List.of(devServer))
				.components(components)
				.addSecurityItem(securityRequirement);
	}
}
