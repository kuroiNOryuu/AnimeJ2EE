package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {
	
	@Id
	@Column(name = "USER_ID", unique = true, nullable = false)
	private String email;

	private String username;
	
	// Relations

	// Old version with join table and supp element
	//@OneToMany(mappedBy="userAnimeId.user", cascade = CascadeType.ALL)
	//private Set<UserAnime> userAnimes;

	// New version without supp element
	@ManyToMany
	private Set<Anime> userAnimes;
	
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
	public Set<Anime> getUserAnimes() {
		return userAnimes;
	}

	public void setUserAnimes(Set<Anime> userAnimes) {
		this.userAnimes = userAnimes;
	}	
	
	// Contructor
	public User(){
		this.userAnimes = new HashSet<Anime>();
	}

	// UserAnimes Old version with join table and supp element
	// public Set<UserAnime> getUserAnimes() {
	//	return userAnimes;
	//}

	//public void setUserAnimes(Set<UserAnime> userAnimes) {
	//	this.userAnimes = userAnimes;
	//}
	
	// Constructor Old version with join table and supp element
	//public User(){
	//	this.userAnimes = new HashSet<UserAnime>();
	//}

}
