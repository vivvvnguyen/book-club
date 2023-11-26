package com.codingdojo.bookclub.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//@Entity connects class to database
@Entity

//Table name always needs to be lowercase and plural
//@Table specifies table name in the database
@Table(name="books")

public class Book {
// Basic class object attributes---------------------------
	// Id will be primary key and auto-increment
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// This prevents createdAt column from being updated after creation
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	// CreatedAt and UpdatedAt, ensure that createdAt date does not change after creation and updatedAt is updated
	@PrePersist
	protected void onCreated() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

// Object specific attributes------------------------------
	@NotBlank(message="Title Required")
	@Size(min=2, message="Title must be at least 2 characters.")
	private String title;
	
	@NotBlank(message="Author Required")
	@Size(min=2, message="Author must be at least 2 characters")
	private String author;
	
	@NotBlank(message="Thoughts Required")
	@Size(min=2, message="Your thoughts must be at least 2 characters")
	private String thoughts;

// Table Relationship Attribute-------------------------------	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User creator;
	
// Empty Bean Constructor and Override Constructor------------
	public Book() {}
	
// Getters and Setters----------------------------------------
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getThoughts() {
		return thoughts;
	}
	public void setThoughts(String thoughts) {
		this.thoughts = thoughts;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	
}
