package ch.hevs.animeService;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Anime;
import ch.hevs.businessobject.Studio;

@Local
public interface AnimeList {

	List<Anime> getAnimes();	
	Anime getAnimeById(long id);
	List<Studio> getStudios();
	void addAnimeToFavorites(String id);
	void removeAnimeFromFavorites(String id);
}
