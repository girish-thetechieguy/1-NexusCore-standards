package com.thetechieguy.user_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.ZonedDateTime;

@Entity
@Table(name = "USERS", schema = "nexus_schema")
@Where(clause = "is_active = true") // Soft delete filter
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(nullable = false, length = 50, unique = true)
	private String username;

	@Column(nullable = false, length = 255, unique = true)
	private String email;

	@Column(name = "password_hash", nullable = false, length = 255)
	private String password;

	@Column(name = "first_name", length = 100)
	private String firstName;

	@Column(name = "last_name", length = 100)
	private String lastName;

	@Column(name = "is_active", nullable = false)
	@Builder.Default
	private Boolean active = true;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private ZonedDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private ZonedDateTime updatedAt;

	@Column(name = "created_by", length = 100, updatable = false)
	private String createdBy;

	@Column(name = "updated_by", length = 100)
	private String updatedBy;

	// Business methods
	public void deactivate() {
		this.active = false;
		this.updatedAt = ZonedDateTime.now();
	}

	public void updatePassword(String newPasswordHash, String updatedByUser) {
		this.password = newPasswordHash;
		this.updatedBy = updatedByUser;
		this.updatedAt = ZonedDateTime.now();
	}
}


