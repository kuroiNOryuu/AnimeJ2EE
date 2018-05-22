package ch.hevs.animeService;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ch.hevs.businessobject.Anime;

@Stateless
public class AnimeListBean implements AnimeList {

	@PersistenceContext(name = "animePU")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Anime> getAnimeList() {
		return (List<Anime>) em.createQuery("from Anime").getResultList();
	}

	@Override
	public Anime getAnimeById(long id) {
		Query query = em.createQuery("FROM Anime a WHERE a.id=:id");
		query.setParameter("id", id);
		
		return (Anime) query.getSingleResult();
	}

	@Override
	public void addAnimeToFavorites(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAnimeFromFavorites(String id) {
		// TODO Auto-generated method stub
		
	}
}
