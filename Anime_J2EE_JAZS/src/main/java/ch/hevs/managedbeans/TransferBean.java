package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.animeService.AnimeList;
import ch.hevs.animeService.DatabaseDefaultContent;
import ch.hevs.businessobject.Anime;

/**
 * TransferBean.java
 * 
 */

public class TransferBean
{
    private List<Anime> animes;
    private List<String> animeNames;
    private AnimeList animeList;   
    private String message = "ràs";
    
    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to AnimeList EJB
    	InitialContext ctx = new InitialContext();
		animeList = (AnimeList) ctx.lookup("java:global/Anime_J2EE_JAZS-0.0.1-SNAPSHOT/AnimeListBean!ch.hevs.animeService.AnimeList");    	
			
    	// get animes
		animes = animeList.getAnimeList();
		this.animeNames = new ArrayList<String>();
		for (Anime anime : animes) {
			this.animeNames.add(anime.getAnimeName());
		}
    }
    
    // animes
    public List<Anime> getAnimeList()
    {
    	return animes;
    }
    
    public void setAnimeList(List<Anime> animes)
    {
    	this.animes = animes;
    }
    
    // message
    public String getMessage()
    {
    	return message;
    }
    
    public void setMessage(String message)
    {
    	this.message = message;
    }   
    
    // populateDB
    public String populate()
    {
    	DatabaseDefaultContent.populate();
    	setMessage("DB populated");
    	return "home";
    }

    public List<Anime> getAnimes() {
		return animes;
    }
    
    public List<String> getAnimeNames() {
    	return animeNames;
    }
}