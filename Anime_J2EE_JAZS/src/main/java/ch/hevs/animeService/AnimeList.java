package ch.hevs.animeService;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Anime;

@Local
public interface AnimeList {

	List<Anime> getAnimeList();	
	Anime getAnimeById(String id);
	void addAnimeToFavorites(String id);
	void removeAnimeFromFavorites(String id);
}
