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
	@Column (unique = true, length = 50, nullable=false)
	private String username;
	@Column (nullable=false, length = 100)
	@JsonIgnore
	private String password;
	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Article> articles;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if(username != null && !username.equals(""))
			this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}