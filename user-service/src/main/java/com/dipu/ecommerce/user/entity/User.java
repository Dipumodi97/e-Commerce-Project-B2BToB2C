package com.dipu.ecommerce.user.entity;

import java.time.Instant;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(nullable = false , unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	private String phone;
	private String roles;
	
	/**
	 *  Instant me time-zone confusion nhi rehta hai
	 *  ye UTC based hote hai,isliye
	 *  LocalDate , LocalDateTime , Timestamp ke jagah hum Instant  use karte hai
	 */
	private Instant createdAt;
	private Instant updatedAt;

	// createdAt and updatedAt ke liye pre-Persist set kar dete hai
	@PrePersist
	public void prePersist() {
		createdAt = Instant.now();
		updatedAt = createdAt;
	}

	@PreUpdate 
	// jaise hi data update hoga, update hone se pahle value change ho jayegi. 
	public void preUpdate() {
		updatedAt = Instant.now();
		updatedAt = createdAt;
	}

}
