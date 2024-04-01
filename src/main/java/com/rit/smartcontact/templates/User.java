package com.rit.smartcontact.templates;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank(message = "Name field is required..") // server-side validation for blank
	@Column(nullable = false, length = 50) // database validation
	private String name;

	@Email(message = "Please enter a valid email.") // server-side bean validation to validate email
	@Column(unique = true, length = 30)
	private String email;

	@Column(length = 15)
	private String password;

	@Column(length = 20)
	private String role;

	@Column(length = 300)
	private String about;
	private String ImageUrl;
	private boolean enabled;

	// to establish relation b/w User & Contact
	// if we add/delete user, all contacts of this user will be added/deleted
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	List<Contact> contacts = new ArrayList<>();

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", about=" + about + ", ImageUrl=" + ImageUrl + ", enabled=" + enabled + "]";
	}

}
