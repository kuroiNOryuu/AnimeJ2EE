package ch.hevs.managedbeans;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Query;

import ch.hevs.animeService.AnimeList;
import ch.hevs.animeService.DatabaseDefaultContent;
import ch.hevs.businessobject.Anime;
import ch.hevs.businessobject.Studio;
import ch.hevs.dummyContent.DummyAnime;
import ch.hevs.dummyContent.DummyContentGenerator;
import ch.hevs.dummyContent.DummyStudio;

/**
 * TransferBean.java
 * 
 */

public class AnimeAppBean
{
	private AnimeList animeList;
    private List<Anime> animes;
    private List<Studio> studios;
    private Anime anime;  
    private Studio studio;
    private String message = null;
    
    // temp
    private DummyContentGenerator content;
    private List<DummyAnime> dummyAnimes;
    private List<DummyStudio> dummyStudios;
    private DummyAnime dummyAnime;
    
    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// JNDI to inject reference to AnimeList EJB
    	InitialContext ctx = new InitialContext();
		animeList = (AnimeList) ctx.lookup("java:global/Anime_J2EE_JAZS-0.0.1-SNAPSHOT/AnimeListBean!ch.hevs.animeService.AnimeList");    	
			
    	// get animes : depuis la bdd -> <ui:repeat value="#{animeAppBean.animes}" var="anime"> dans home.xhtml
		//animes = animeList.getAnimes();
		//studios = animeList.getStudios();
		
		
		try {
			content = new DummyContentGenerator();
			// get dummy animes : depuis DummyContentGen. -> <ui:repeat value="#{animeAppBean.dummyAnimes}" var="anime"> dans home.xhtml
			dummyAnimes = content.getDummyAnimes();
			// get dummy studios : depuis DummyContentGen. -> <ui:repeat value="#{animeAppBean.dummyAnimes}" var="anime"> dans home.xhtml
			dummyStudios = content.getDummyStudios();
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
    
 // dummy animes
    public List<DummyStudio> getDummyStudios()
    {
    	return dummyStudios;
    }
    
    public void setDummyStudios(List<DummyStudio> dummyStudios)
    {
    	this.dummyStudios = dummyStudios;
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
    
    // studio
    public Studio getStudio()
    {
    	return studio;
    }
    
    public void setStudio(Studio studio)
    {
    	this.studio = studio;
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
    //this managed property will read value from request parameter pageId
    @ManagedProperty(value = "#{param.animeId}")
    //private long animeId;
    private int animeId;
    
    public String details()
    {
    	// from DB -> <h:panelGroup layout="block" value="#{animeAppBean.anime}" var="anime"> in details.xhtml
    	//anime = getAnimeById(animeId);
    	
    	// from dummy content -> <h:panelGroup layout="block" value="#{animeAppBean.dummyAnime}" var="anime"> in details.xhtml
    	dummyAnime = content.getDummyAnimes().get(animeId);
    	
    	return "details";
    }
    
    public Anime getAnimeById(long id) {
		return animeList.getAnimeById(id);
	}
    
    public String addAnime()
    {
    	return "addForm";
    }
    
    public String saveNewAnime()
    {
    	// Save data to DB
    	return "home";
    }
}