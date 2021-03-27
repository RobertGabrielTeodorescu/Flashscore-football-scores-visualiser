package entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	private String id_user;
	
	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String type;

	@Column
	private String email;


	public User(String username) {
		this.username = username;
	}
	
	public User() {}


	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
