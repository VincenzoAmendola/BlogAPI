package it.rdev.blog.api.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column (unique = true, nullable=false)
	private String username;
	@Column (nullable=false)
	@JsonIgnore
	private String password;
	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Article> articles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}