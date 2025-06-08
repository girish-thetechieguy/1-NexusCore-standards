package com.thetechieguy.user_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	@Email(message = "Invalid email format")
	private String email;

	@Column(nullable = false)
	@Size(min = 2, max = 50, message = "Name must be between 2-50 characters")
	private String name;

	@Column(nullable = false)
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$",
			message = "Password must be 8+ chars with letters and numbers")
	private String password;
}


