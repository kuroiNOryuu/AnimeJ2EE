package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@Column(name = "USER_ID", unique = true, nullable = false)
	private String email;

	private String username;
	
	// Relations
	@OneToMany(mappedBy="userAnimeId.user", cascade = CascadeType.ALL)
	private Set<UserAnime> userAnimes;

	// Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	// Username
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	// UserAnimes
	public Set<UserAnime> getUserAnimes() {
		return userAnimes;
	}

	public void setUserAnimes(Set<UserAnime> userAnimes) {
		this.userAnimes = userAnimes;
	}
	
	// Constructor
	public User(){
		this.userAnimes = new HashSet<UserAnime>();
	}

}
