package com.codingdojo.bookclub.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
// Default Attributes--------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (updatable = false)
	@DateTimeFormat(pattern = "MM/dd/yyyy JJ:mm:ss")
	private Date createdAt;
	@DateTimeFormat(pattern = "MM/dd/yyyy JJ:mm:ss")
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

// Object Specific Attributes-------------------------------
    @NotBlank(message="First Name is required!")
    @Size(min=2, message="First Name must be at least 2 characters")
    private String firstName;
    
    @NotBlank(message="Last Name is required!")
    @Size(min=2, message="Last name must be at least 2 characters")
    private String lastName;
    
    @NotEmpty(message="Email is required!")
    @Email(message="Please enter a valid email!")
    private String email;
    
    @NotEmpty(message="Password is required!")
    @Size(min=8, message="Password must be at least 8 characters")
    private String password;
    
    @Transient
    @NotEmpty(message="Confirm Password is required!")
    @Size(min=8, message="Confirm Password must be at least 8 characters")
    private String confirm;

// Table Relationships--------------------------------------
    @OneToMany(mappedBy="creator", fetch=FetchType.LAZY)
	private List<Book> myBooks;
    
// Constructors---------------------------------------------
    public User() {}

// Getters and Setters--------------------------------------
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
 	public String getFirstName() {
 		return firstName;
 	}
 	public void setFirstName(String firstName) {
 		this.firstName = firstName;
 	}
 	public String getLastName() {
 		return lastName;
 	}
 	public void setLastName(String lastName) {
 		this.lastName = lastName;
 	}
 	public String getEmail() {
 		return email;
 	}
 	public void setEmail(String email) {
 		this.email = email;
 	}
 	public String getPassword() {
 		return password;
 	}
 	public void setPassword(String password) {
 		this.password = password;
 	}
 	public String getConfirm() {
 		return confirm;
 	}
 	public void setConfirm(String confirm) {
 		this.confirm = confirm;
 	}
	public List<Book> getMyBooks() {
		return myBooks;
	}
	public void setMyBooks(List<Book> myBooks) {
		this.myBooks = myBooks;
	}
 	
}
