package ch.hevs.businessobject;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

//@Entity
//@Table(name = "USER_ANIME")
//@AssociationOverrides({
//	@AssociationOverride(name = "userAnimeId.anime", 
//		joinColumns = @JoinColumn(name = "ANIME_ID")),
//	@AssociationOverride(name = "userAnimeId.user", 
//		joinColumns = @JoinColumn(name = "USER_ID")) })
public class UserAnime {
//	Old version with join table and supp element
	
//	// Id composed by user and anime
//	@EmbeddedId
//	private UserAnimeId userAnimeId;
//	
//	// additional field
//	private int numberOfEpisodesViewed;
//
//	// userAnimeId
//	public UserAnimeId getUserAnimeId() {
//		return userAnimeId;
//	}
//
//	public void setUserAnimeId(UserAnimeId userAnimeId) {
//		this.userAnimeId = userAnimeId;
//	}
//	
//	// User
//	@Transient
//	public User getUser() {
//		return getUserAnimeId().getUser();
//	}
//
//	public void setUser(User user) {
//		getUserAnimeId().setUser(user);
//	}
//
//	// Anime
//	@Transient
//	public Anime getAnime() {
//		return getUserAnimeId().getAnime();
//	}
//
//	public void setAnime(Anime anime) {
//		getUserAnimeId().setAnime(anime);
//	}
//
//	// Number of Episodes Viewed
//	public int getNumberOfEpisodesViewed() {
//		return numberOfEpisodesViewed;
//	}
//
//	public void setNumberOfEpisodesViewed(int numberOfEpisodesViewed) {
//		this.numberOfEpisodesViewed = numberOfEpisodesViewed;
//	}

}
