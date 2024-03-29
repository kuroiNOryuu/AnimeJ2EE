package ch.hevs.animeService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
//import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ch.hevs.businessobject.Anime;
import ch.hevs.businessobject.Studio;
import ch.hevs.businessobject.User;
import ch.hevs.exception.AnimeException;

@Stateless
public class AnimeListBean implements AnimeList {

	@PersistenceContext(name = "animePU")
	private EntityManager em;
	
	@Resource
	private SessionContext ctx;
	
	/*JPQL Queries*/
	//Anime
	private static final String JPQL_SELECT_BY_IDANIME = "SELECT a FROM Anime a WHERE a.idAnime=:idAnime";
	private static final String PARAM_IDANIME = "idAnime";
	private static final String JPQL_SELECT_BY_ANIMENAME = "SELECT a FROM Anime a WHERE a.animeName=:animeName";
	private static final String PARAM_ANIMENAME = "animeName";
	private static final String JPQL_SELECT_ALL_ANIMES = "SELECT a FROM Anime a ORDER BY a.animeName";
	
	//Studio
	private static final String JPQL_SELECT_BY_IDSTUDIO = "SELECT s FROM Studio s WHERE s.idStudio=:idStudio";
	private static final String PARAM_IDSTUDIO = "idStudio";
	private static final String JPQL_SELECT_BY_STUDIONAME = "SELECT s FROM Studio s WHERE s.studioName=:studioName";
	private static final String PARAM_STUDIONAME = "studioName";
	private static final String JPQL_SELECT_ALL_STUDIOS = "SELECT s FROM Studio s ORDER BY s.studioName";

	//User
	private static final String JPQL_SELECT_BY_USER_EMAIL = "SELECT u FROM User u WHERE u.email=:userEmail";
	private static final String PARAM_USER_EMAIL = "userEmail";
	private static final String JPQL_SELECT_BY_USERNAME = "SELECT u FROM User u WHERE u.username=:userName";
	private static final String PARAM_USERNAME = "userName";
	private static final String JPQL_SELECT_ALL_USERS = "SELECT u FROM User u";
	
	//UserAnimes
	private static final String JPQL_SELECT_ALL_USERANIMES = "SELECT DISTINCT ua FROM User u LEFT JOIN u.userAnimes ua WHERE u.email=:userEmail ORDER BY ua.animeName";

	
	// ANIMES
	// Create new Anime
	public void saveAnime(Anime a) throws AnimeException {

        try {
            em.merge( a );
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }

	}
	
	// Get Anime by its ID
	public Anime getAnimeById(long idAnime) throws AnimeException {
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
	
	// Get Anime by its name
	public Anime getAnimeByName(String animeName) throws AnimeException {
		Anime result = new Anime();
		
        Query query = em.createQuery( JPQL_SELECT_BY_ANIMENAME );
        query.setParameter( PARAM_ANIMENAME, animeName );

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
	public List<Anime> getAnimes() throws AnimeException {
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
	public void deleteAnime(Anime a) throws AnimeException {

		try {
            em.remove( em.find(Anime.class, a.getIdAnime()) ); 
    		System.out.println("Anime " + a.getAnimeName() + " (" + a.getIdAnime() + ") deleted");
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }

	}

	// Update a Anime
	public void updateAnime(Anime a) throws AnimeException {

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
	
	// STUDIOS
	// Create new Studio
	public void saveStudio(Studio s) throws AnimeException {
	
        try {
            em.persist( s );
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }

	}
	
	// Get Studio by its ID
	public Studio getStudioById(long idStudio) throws AnimeException {
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
	
	// Get Studio by its NAME
	public Studio getStudioByName(String studioName) throws AnimeException {
		Studio result = new Studio();
		
        Query query = em.createQuery( JPQL_SELECT_BY_STUDIONAME );
        query.setParameter( PARAM_STUDIONAME, studioName );

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
	public List<Studio> getStudios() throws AnimeException {
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
	public void deleteStudio(Studio s) throws AnimeException {

        try {
        	em.find(Studio.class, s);
            em.remove( s ); // Cascade in Studio Entity will remove any Anime having that studio
    		System.out.println("Studio " + s.getStudioName() + " (" + s.getIdStudio() + ") deleted");
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }

	}

	// Update a Studio
	public void updateStudio(Studio s) throws AnimeException {

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
	
	//USERS
	// Create new User
	public void saveUser(User u) throws AnimeException {
		
        try {
            em.persist( u );
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }
		
	}
	
	
	// Get User by its ID
	public User getUserById(String userEmail) throws AnimeException {
		User result = new User();
		
        Query query = em.createQuery( JPQL_SELECT_BY_USER_EMAIL );
        query.setParameter( PARAM_USER_EMAIL, userEmail );

        try {
            result = (User) query.getSingleResult();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }
		return result;

	}
	
	// Get User by its name
	public User getUserByName(String userName) throws AnimeException {
		User result = new User();
		
        Query query = em.createQuery( JPQL_SELECT_BY_USERNAME );
        query.setParameter( PARAM_USERNAME, userName );

        try {
            result = (User) query.getSingleResult();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }
		return result;

	}
	
	// Get All Users
	public List<User> getUsers() throws AnimeException {
		List<User> result = new ArrayList<User>();
		
        Query query = em.createQuery( JPQL_SELECT_ALL_USERS );

        try {
            result = query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }
		
		return result;

	}

	
	// USER ANIMES
	// Add Anime to user list
	@Override
	public void addAnimeToFavorites(Long animeId) throws AnimeException {

		try {
			User user = getUserByName(ctx.getCallerPrincipal().toString());
			Anime anime = getAnimeById(animeId);
			user.addAnime(anime); //Adds anime to User and user to Anime
			saveUser(user);
			saveAnime(anime);
		} catch ( Exception e ) {
			throw new AnimeException( e );
		}
		
	}
	
	// Remove Anime to user list
	@Override
	public void removeAnimeFromFavorites(Long animeId) throws AnimeException {
		
		try {
			User user = getUserByName(ctx.getCallerPrincipal().toString());
			Anime anime = getAnimeById(animeId);
			user.removeAnime(anime); //Removes anime from User and user from Anime
			saveUser(user);
			saveAnime(anime);
		} catch ( Exception e ) {
			throw new AnimeException( e );
		}
		
	}
	
	// Get User Animes
	public List<Anime> getUserAnimes() throws AnimeException {
		List<Anime> result = new ArrayList<Anime>();
		
        Query query = em.createQuery( JPQL_SELECT_ALL_USERANIMES );
        query.setParameter( PARAM_USER_EMAIL, getUserByName(ctx.getCallerPrincipal().toString()).getEmail());

        try {
            result = query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new AnimeException( e );
        }
        
        return result;

	}
	
	//POPULATE
	public void populate() {

		try {
			
			// Create Users
			User user1 = new User("john@doe.net", "johndoe");
			User user2 = new User("jane@doe.net", "janedoe");			
			
			// Create Studios
	        Studio studio1 = new Studio("Arms");
	        Studio studio2 = new Studio("Madhouse");
	        Studio studio3 = new Studio("AIC Plus+");
	        Studio studio4 = new Studio("Production I.G");
	        
	        // Persist Users and Studios
			em.persist(user1);
			em.persist(user2);
			em.persist(studio1);
			em.persist(studio2);
			em.persist(studio3);
			em.persist(studio4);

	        // Create Animes
	        Anime anime1 = new Anime();
	        Anime anime2 = new Anime();
	        Anime anime3 = new Anime();
	        Anime anime4 = new Anime();
	        Anime anime5 = new Anime();
	        Anime anime6 = new Anime();

	        anime1.setAnimeName("Elfen Lied");
	        anime1.setAnimeDescription("Lucy is a special breed of human referred to as \"Diclonius,\" " +
	                "born with a short pair of horns and invisible telekinetic hands that lands her as a victim of inhumane scientific experimentation by the government. " +
	                "However, once circumstances present her an opportunity to escape, Lucy, corrupted by the confinement and torture, " +
	                "unleashes a torrent of bloodshed as she escapes her captors.\n" + "\n" +
	                "During her breakout, she receives a crippling head injury that leaves her with a split personality: " +
	                "someone with the mentality of a harmless child possessing limited speech capacity. " +
	                "In this state of instability, she stumbles upon two college students, Kouta and his cousin Yuka, " +
	                "who unknowingly take an injured fugitive into their care, unaware of her murderous tendencies. " +
	                "This act of kindness will change their lives, as they soon find themselves dragged into the shadowy world of government secrecy and conspiracy.\n");
	        anime1.setStartDate(new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2004-07-25" ));
	        anime1.setEndDate(new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2004-10-17" ));
	        anime1.setStudio(studio1);
	        anime1.setNumberOfEpisodes(13);
	        anime1.setEpisodeDuration(25);

	        anime2.setAnimeName("Death Note");
	        anime2.setAnimeDescription("A shinigami, as a god of death, can kill any person�provided " +
	                "they see their victim's face and write their victim's name in a notebook called a Death Note. " +
	                "One day, Ryuk, bored by the shinigami lifestyle and interested in seeing how a human " +
	                "would use a Death Note, drops one into the human realm.\n" +
	                "\n" +
	                "High school student and prodigy Light Yagami stumbles upon the Death Note and�since " +
	                "he deplores the state of the world�tests the deadly notebook by writing a criminal's name in it. " +
	                "When the criminal dies immediately following his experiment with the Death Note, " +
	                "Light is greatly surprised and quickly recognizes how devastating the power that has fallen into his hands could be. \n" +
	                "\n" +
	                "With this divine capability, Light decides to extinguish all criminals in order to build a new world " +
	                "where crime does not exist and people worship him as a god. Police, however, " +
	                "quickly discover that a serial killer is targeting criminals and, consequently, " +
	                "try to apprehend the culprit. To do this, the Japanese investigators count on the assistance of the best detective in the world: " +
	                "a young and eccentric man known only by the name of L.");
	        anime2.setStartDate(new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2006-10-4" ));
	        anime2.setEndDate(new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2007-06-27"));
	        anime2.setStudio(studio2);
	        anime2.setNumberOfEpisodes(37);
	        anime2.setEpisodeDuration(23);


	        anime3.setAnimeName("No Game, No Life");
	        anime3.setAnimeDescription("No Game No Life is a surreal comedy that follows Sora and Shiro, " +
	                "shut-in NEET siblings and the online gamer duo behind the legendary username \"Blank.\" " +
	                "They view the real world as just another lousy game; however, a strange e-mail challenging them to a chess match changes everything�" +
	                "the brother and sister are plunged into an otherworldly realm where they meet Tet, the God of Games.\n" +
	                "\n" +
	                "The mysterious god welcomes Sora and Shiro to Disboard, a world where all forms of conflict�" +
	                "from petty squabbles to the fate of whole countries�are settled not through war, but by way of high-stake games. " +
	                "This system works thanks to a fundamental rule wherein each party must wager something they deem to be of equal value to the other party's wager. " +
	                "In this strange land where the very idea of humanity is reduced to child's play, " +
	                "the indifferent genius gamer duo of Sora and Shiro have finally found a real reason to keep playing games: " +
	                "to unite the sixteen races of Disboard, defeat Tet, and become the gods of this new, gaming-is-everything world.");
	        anime3.setStartDate(new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2014-04-09"));
	        anime3.setEndDate(new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2014-06-25"));
	        anime3.setStudio(studio2);
	        anime3.setNumberOfEpisodes(12);
	        anime3.setEpisodeDuration(23);


	        anime4.setAnimeName("Date a Live");
	        anime4.setAnimeDescription("\"Before the world ends, kill me or kiss me.\"\n" + "\n" +
	                "Thirty years before the events of Date A Live, an enormous explosion devastates east Asia and kills 150 million people. " +
	                "This is the first known \"Spacequake\", an inexplicable natural disaster that has since become commonplace. Fast forward to the future. " +
	                "High school second year Shidou Itsuka lives alone with his cute little sister while their parents are away.\n" +
	                "\n" +
	                "What do these things have to do with each other? While rushing to save his sister from a sudden Spacequake, " +
	                "Shidou is caught in the blast and, in the midst of the chaos, finds a mysterious girl. It turns out that this girl is actually a Spirit, " +
	                "a powerful being from another world whose arrival devastates the surrounding area. Thankfully, Shidou is rescued by an anti-Spirit strike team... " +
	                "led by his little sister?!\n" +
	                "\n" +
	                "This vicious task force is locked and loaded, ready to exterminate Spirits with extreme prejudice. " +
	                "But this violent method is not for Shidou. He discovers the one way to neutralize these Spirits peacefully: make them fall in love. " +
	                "Now, it's up to Shidou to save the world by dating those who threaten to destroy it!");
	        anime4.setStartDate(new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2013-04-06"));
	        anime4.setEndDate(new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2013-06-22"));
	        anime4.setStudio(studio3);
	        anime4.setNumberOfEpisodes(12);
	        anime4.setEpisodeDuration(23);


	        anime5.setAnimeName("Seirei no Moribito");
	        anime5.setAnimeDescription("On the precipice of a cataclysmic drought, the Star Readers of the Shin Yogo Empire must devise a plan to avoid widespread famine. " +
	                "It is written in ancient myths that the first emperor, along with eight warriors, " +
	                "slew a water demon to avoid a great drought and save the land that was to become Shin Yogo. " +
	                "If a water demon was to appear once more, its death could bring salvation. However, " +
	                "the water demon manifests itself within the body of the emperor's son, Prince Chagum�by the emperor's order, " +
	                "Chagum is to be sacrificed to save the empire.\n" +
	                "\n" +
	                "Meanwhile, a mysterious spear-wielding mercenary named Balsa arrives in Shin Yogo on business. " +
	                "After saving Chagum from a thinly veiled assassination attempt, she is tasked by Chagum's mother to protect him from the emperor and his hunters. " +
	                "Bound by a sacred vow she once made, Balsa accepts.\n" +
	                "\n" +
	                "Seirei no Moribito follows Balsa as she embarks on her journey to protect Chagum, exploring the beauty of life, nature, family, " +
	                "and the bonds that form between strangers.\n");
	        anime5.setStartDate(new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2007-04-07"));
	        anime5.setEndDate(new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2007-09-29"));
	        anime5.setStudio(studio4);
	        anime5.setNumberOfEpisodes(26);
	        anime5.setEpisodeDuration(25);


	        anime6.setAnimeName("Guilty Crown");
	        anime6.setAnimeDescription("Japan, 2039. Ten years after the outbreak of the \"Apocalypse Virus,\" " +
	                "an event solemnly regarded as \"Lost Christmas,\" the once proud nation has fallen under the rule of the GHQ, " +
	                "an independent military force dedicated to restoring order. Funeral Parlor, a guerilla group led by the infamous Gai Tsutsugami, " +
	                "act as freedom fighters, offering the only resistance to GHQ's cruel despotism.\n" +
	                "\n" +
	                "Inori Yuzuriha, a key member of Funeral Parlor, runs into the weak and unsociable Shuu Ouma during a crucial operation, " +
	                "which results in him obtaining the \"Power of Kings\"�an ability which allows the wielder to draw out the manifestations of an individual's personality, or \"voids.\" " +
	                "Now an unwilling participant in the struggle against GHQ, Shuu must learn to control his newfound power if he is to help take back Japan once and for all.\n" +
	                "\n" +
	                "Guilty Crown follows the action-packed story of a young high school student who is dragged into a war, " +
	                "possessing an ability that will help him uncover the secrets of the GHQ, Funeral Parlor, and Lost Christmas. " +
	                "However, he will soon learn that the truth comes at a far greater price than he could have ever imagined.\n");
	        anime6.setStartDate(new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2011-10-14"));
	        anime6.setEndDate(new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2012-03-23"));
	        anime6.setStudio(studio4);
	        anime6.setNumberOfEpisodes(22);
	        anime6.setEpisodeDuration(24);

			em.persist(anime1);
			em.persist(anime2);
			em.persist(anime3);
			em.persist(anime4);
			em.persist(anime5);
			em.persist(anime6);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
