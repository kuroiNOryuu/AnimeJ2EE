package ch.hevs.managedbeans;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Query;

import ch.hevs.animeService.AnimeList;
import ch.hevs.animeService.DatabaseDefaultContent;
import ch.hevs.businessobject.Anime;
import ch.hevs.dummyContent.DummyAnime;
import ch.hevs.dummyContent.DummyContentGenerator;

/**
 * TransferBean.java
 * 
 */

public class AnimeAppBean
{
    private List<Anime> animes;
    private Anime anime;
    private AnimeList animeList;   
    private String message = "ràs";
    
    private DummyContentGenerator content;
    private List<DummyAnime> dummyAnimes;
    
    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to AnimeList EJB
    	InitialContext ctx = new InitialContext();
		animeList = (AnimeList) ctx.lookup("java:global/Anime_J2EE_JAZS-0.0.1-SNAPSHOT/AnimeListBean!ch.hevs.animeService.AnimeList");    	
			
    	// get animes : depuis la bdd -> <ui:repeat value="#{animeAppBean.animes}" var="anime"> dans home.xhtml
		//animes = animeList.getAnimeList();
		
		// get dummy animes : depuis DummyContentGen. -> <ui:repeat value="#{animeAppBean.dummyAnimes}" var="anime"> dans home.xhtml
		try {
			content = new DummyContentGenerator();
			dummyAnimes = content.getDummyAnimes();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    }
    
    // animes
    public List<Anime> getAnimes()
    {
    	return animes;
    }
    
    public void setAnimes(List<Anime> animes)
    {
    	this.animes = animes;
    }
    
    // dummy animes
    public List<DummyAnime> getDummyAnimes()
    {
    	return dummyAnimes;
    }
    
    public void setDummyAnimes(List<DummyAnime> dummyAnimes)
    {
    	this.dummyAnimes = dummyAnimes;
    }
    
    // anime
    public Anime getAnime()
    {
    	return anime;
    }
    
    public void setAnime(Anime anime)
    {
    	this.anime = anime;
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
    
    // go to details page
    public String details(long id)
    {
    	anime = getAnimeById(id);
    	return "details";
    }
    
    public Anime getAnimeById(long id) {
		return animeList.getAnimeById(id);
	}
}