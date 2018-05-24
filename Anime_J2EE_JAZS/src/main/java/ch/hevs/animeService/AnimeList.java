package ch.hevs.animeService;

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
	List<Anime> getAnimes();
	void updateAnime(Anime a);
	void deleteAnime(Anime a);
	
	// STUDIOS
	void saveStudio(Studio s);
	Studio getStudioById(long idStudio);
	List<Studio> getStudios();
	void updateStudio(Studio s);
	void deleteStudio(Studio s);
	
	// USER
	void saveUser(User u);
	
	// USER ANIME
	void addAnimeToFavorites(String id);
	void removeAnimeFromFavorites(String id);

	// POPULATE
	void populate();
	
}
