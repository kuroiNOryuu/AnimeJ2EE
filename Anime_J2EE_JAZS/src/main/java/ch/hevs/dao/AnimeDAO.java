package ch.hevs.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ch.hevs.businessobject.Anime;
import ch.hevs.exception.AnimeException;

@Stateful
public class AnimeDAO {

	private static final String JPQL_SELECT_BY_IDANIME = "SELECT a FROM Anime a WHERE a.idAnime=:idAnime";
	private static final String PARAM_IDANIME = "idAnime";
	private static final String JPQL_SELECT_ALL_ANIMES = "SELECT a FROM Anime a";
	
	
	// Manager that handles connection with DB
	@PersistenceContext(name = "animePU", type=PersistenceContextType.EXTENDED)
	static private EntityManager em;

	// Create new Anime
	public static void save(Anime a) throws AnimeException {
		
        try {
            em.persist( a );
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }

	}
	
	// Get Anime by its ID
	public static Anime getAnime(long idAnime) throws AnimeException {
		Anime result = new Anime();
		
        Query query = em.createQuery( JPQL_SELECT_BY_IDANIME );
        query.setParameter( PARAM_IDANIME, idAnime );

        try {
            result = (Anime) query.getSingleResult();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }
		return result;

	}

	// Get All Animes
	public static List<Anime> getAnimes() throws AnimeException {
		List<Anime> result = new ArrayList<Anime>();
		
        Query query = em.createQuery( JPQL_SELECT_ALL_ANIMES );

        try {
            result = query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }
		
		return result;

	}

	// Remove an Anime
	public static void delete(Anime a) throws AnimeException {

        try {
            em.remove( a ); 
    		System.out.println("Anime " + a.getAnimeName() + " (" + a.getIdAnime() + ") deleted");
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }

	}

	// Update a Anime
	public static void update(Anime a) throws AnimeException {

		try {
			Anime anime = em.find(Anime.class, a.getIdAnime());
			em.getTransaction().begin();
			anime.setAnimeName(a.getAnimeName());
			em.getTransaction().commit();
    		System.out.println("Anime " + a.getAnimeName() + " (" + a.getIdAnime() + ") modified");
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }
		
	}
}
