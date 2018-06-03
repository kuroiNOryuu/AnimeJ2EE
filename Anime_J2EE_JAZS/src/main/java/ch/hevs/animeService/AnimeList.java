package ch.hevs.animeService;

import java.util.HashSet;
import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Anime;
import ch.hevs.businessobject.Studio;
import ch.hevs.businessobject.User;

@Local
public interface AnimeList {
	
	// ANIMES
	void saveAnime(Anime a);
	Anime getAnimeById(long id);
	Anime getAnimeByName(String animeName);
	List<Anime> getAnimes();
	void updateAnime(Anime a);
	void deleteAnime(Anime a);
	
	// STUDIOS
	void saveStudio(Studio s);
	Studio getStudioById(long idStudio);
	Studio getStudioByName(String studioName);
	List<Studio> getStudios();
	void updateStudio(Studio s);
	void deleteStudio(Studio s);
	
	// USER
	void saveUser(User u);
	User getUserById(String email);
	User getUserByName(String userName);
	List<User> getUsers();
	
	// USER ANIME
	void addAnimeToFavorites(Long animeId);
	void removeAnimeFromFavorites(Long animeId);
	List<Anime> getUserAnimes();

	// POPULATE
	void populate();
	
}
