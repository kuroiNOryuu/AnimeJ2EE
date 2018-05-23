package ch.hevs.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ch.hevs.businessobject.Studio;
import ch.hevs.exception.AnimeException;

@Stateful
public class StudioDAO {

	private static final String JPQL_SELECT_BY_IDSTUDIO = "SELECT s FROM Studio s WHERE s.idStudio=:idStudio";
	private static final String PARAM_IDSTUDIO = "idStudio";
	private static final String JPQL_SELECT_ALL_STUDIOS = "SELECT s FROM Studio s";
	
	
	// Manager that handles connection with DB
	@PersistenceContext(name = "animePU", type=PersistenceContextType.EXTENDED)
	private static EntityManager em;

	// Create new Studio
	public static void save(Studio s) throws AnimeException {
		
        try {
            em.persist( s );
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }

	}
	
	// Get Studio by its ID
	public static Studio getStudio(long idStudio) throws AnimeException {
		Studio result = new Studio();
		
        Query query = em.createQuery( JPQL_SELECT_BY_IDSTUDIO );
        query.setParameter( PARAM_IDSTUDIO, idStudio );

        try {
            result = (Studio) query.getSingleResult();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }
		return result;

	}

	// Get All Studios
	public static List<Studio> getStudios() throws AnimeException {
		List<Studio> result = new ArrayList<Studio>();
		
        Query query = em.createQuery( JPQL_SELECT_ALL_STUDIOS );

        try {
            result = query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }
		
		return result;

	}

	// Remove a Studio
	public static void delete(Studio s) throws AnimeException {

        try {
            em.remove( s ); // Cascade in Studio Entity will remove any Anime having that studio
    		System.out.println("Studio " + s.getStudioName() + " (" + s.getIdStudio() + ") deleted");
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }

	}

	// Update a Studio
	public static void update(Studio s) throws AnimeException {

		try {
			Studio studio = em.find(Studio.class, s.getIdStudio());
			em.getTransaction().begin();
			studio.setStudioName(s.getStudioName());
			em.getTransaction().commit();
			System.out.println("Studio " + s.getStudioName() + " (" + s.getIdStudio() + ") modified");
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }
		
	}
}
