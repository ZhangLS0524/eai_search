package com.EAI.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class ProjectApplication {

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "Hello, World!";
	}

	@GetMapping("/health")
	public ResponseEntity<Map<String, Object>> health() {
		Map<String, Object> response = new HashMap<>();
		
		try (Connection connection = dataSource.getConnection()) {
			response.put("status", "UP");
			response.put("database", "Connected");
			response.put("message", "Application is healthy");
			return ResponseEntity.ok(response);
		} catch (SQLException e) {
			response.put("status", "DOWN");
			response.put("database", "Disconnected");
			response.put("error", e.getMessage());
			response.put("message", "Database connection failed");
			return ResponseEntity.status(503).body(response);
		}
	}
}
