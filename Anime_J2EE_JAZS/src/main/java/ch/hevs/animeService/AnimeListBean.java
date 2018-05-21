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
	
//	@PersistenceContext(name = "animePU")
//	private EntityManager em;
//
//	public Account getAccount(String accountDescription, String lastnameOwner) {
//		Query query = em.createQuery("FROM Account a WHERE a.description=:description AND a.owner.lastname=:lastname");
//		query.setParameter("description", accountDescription);
//		query.setParameter("lastname", lastnameOwner);
//		
//		return (Account) query.getSingleResult();
//	}
//	
//	public List<Account> getAccountListFromClientLastname(String lastname) {
//		return (List<Account>) em.createQuery("SELECT c.accounts FROM Client c where c.lastname=:lastname").setParameter("lastname", lastname).getResultList();
//	}
//
//	public void transfer(Account srcAccount, Account destAccount, int amount) {
//		
//		em.persist(srcAccount);
//		em.persist(destAccount);
//		srcAccount.debit(amount);
//		destAccount.credit(amount);
//	}
//
//	public List<Client> getClients() {
//		return em.createQuery("FROM Client").getResultList();
//	}
//	
//	public Client getClient(long clientid) {
//		return (Client) em.createQuery("FROM Client c where c.id=:id").setParameter("id", clientid).getSingleResult();
//	}
}
