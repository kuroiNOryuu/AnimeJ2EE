package ch.hevs.businessobject;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ANIME")
public class Anime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ANIME_ID", unique = true, nullable = false)
	private Long idAnime;
	
	@Column(nullable = false)
	private String animeName;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private String animeDescription;
	private int episodeDuration;
	private int numberOfEpisodes;

	// Relations Old version with join table and supp element
	//@OneToMany(mappedBy="userAnimeId.anime", cascade = CascadeType.ALL)
	//private Set<UserAnime> userAnimes;
		
	// Relations
	@ManyToOne(cascade = CascadeType.PERSIST)
	@OrderBy("studioName")
	private Studio studio;
	
	// New version without supp element
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<User> users;
	
	// Helper Methods
	public void addUser(User user){
		users.add(user); //do not call directly, use User.addAnime(anime)!
	}
	
	public void removeUser(User user){
		users.remove(user);  //do not call directly, use User.removeAnime(anime)!
	}
	
	// Id Anime
	public Long getIdAnime() {
		return idAnime;
	}
	public void setIdAnime(Long idAnime) {
		this.idAnime = idAnime;
	}
	
	// Anime Name
	public String getAnimeName() {
		return animeName;
	}
	public void setAnimeName(String animeName) {
		this.animeName = animeName;
	}
	
	// Start Date
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	// End Date 
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	// Anime Description
	public String getAnimeDescription() {
		return animeDescription;
	}
	public void setAnimeDescription(String animeDescription) {
		this.animeDescription = animeDescription;
	}
	
	// Episode Duration
	public int getEpisodeDuration() {
		return episodeDuration;
	}
	public void setEpisodeDuration(int episodeDuration) {
		this.episodeDuration = episodeDuration;
	}
	
	// Number of Episodes
	public int getNumberOfEpisodes() {
		return numberOfEpisodes;
	}
	public void setNumberOfEpisodes(int numberOfEpisodes) {
		this.numberOfEpisodes = numberOfEpisodes;
	}
	
	// Studio
	public Studio getStudio() {
		return studio;
	}
	public void setStudio(Studio studio) {
		this.studio = studio;
	}	
	
	// UserAnimes
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	// Constructor
	public Anime(){
		this.users = new HashSet<User>();
	}
	
	// UserAnimes  Old version with join table and supp element
	//public Set<UserAnime> getUserAnimes() {
	//	return userAnimes;
	//}

	//public void setUserAnimes(Set<UserAnime> userAnimes) {
	//	this.userAnimes = userAnimes;
	//}
	
	// Constructor
	//public Anime(){
	//	this.userAnimes = new HashSet<UserAnime>();
	//}

}
