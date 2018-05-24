package ch.hevs.managedbeans;

import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ch.hevs.animeService.AnimeList;
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
	private boolean renderPopulateButton;

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
		animes = animeList.getAnimes();
		studios = animeList.getStudios();
		//disable populateDb button if DB not empty
		setRenderPopulateButton(isDbNotPopulated());

		//    			try {
		//    				content = new DummyContentGenerator();
		//    				// get dummy animes : depuis DummyContentGen. -> <ui:repeat value="#{animeAppBean.dummyAnimes}" var="anime"> dans home.xhtml
		//    				dummyAnimes = content.getDummyAnimes();
		//    				// get dummy studios : depuis DummyContentGen. -> <ui:repeat value="#{animeAppBean.dummyAnimes}" var="anime"> dans home.xhtml
		//    				dummyStudios = content.getDummyStudios();	
		//    				
		//    				 //disable populateDb button if DB not empty
		//    				setRenderPopulateButton(isDbNotPopulated());
		//    			} catch (ParseException e) {
		//    				// TODO Auto-generated catch block
		//    				e.printStackTrace();
		//    			}	
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

	// studios
	public List<Studio> getStudios()
	{
		return studios;
	}

	public void setStudios(List<Studio> studios)
	{
		this.studios = studios;
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

	// populateDB
	public String populate()
	{
		animeList.populate();
		setRenderPopulateButton(false); //disable populateDb button after click

		// get animes : depuis la bdd -> <ui:repeat value="#{animeAppBean.animes}" var="anime"> dans home.xhtml
		animes = animeList.getAnimes();
		studios = animeList.getStudios();

		//    			try {
		//    				content = new DummyContentGenerator();
		//    				// get dummy animes : depuis DummyContentGen. -> <ui:repeat value="#{animeAppBean.dummyAnimes}" var="anime"> dans home.xhtml
		//    				dummyAnimes = content.getDummyAnimes();
		//    				// get dummy studios : depuis DummyContentGen. -> <ui:repeat value="#{animeAppBean.dummyAnimes}" var="anime"> dans home.xhtml
		//    				dummyStudios = content.getDummyStudios();	
		//    				
		//    				 //disable populateDb button if DB not empty
		//    				setRenderPopulateButton(isDbNotPopulated());
		//    			} catch (ParseException e) {
		//    				// TODO Auto-generated catch block
		//    				e.printStackTrace();
		//    			}		
		return "home";
	}

	// Check if DB populated. Populated = don't render populate db button
	public boolean isDbNotPopulated(){

		List<Anime> testAnime = animeList.getAnimes();
		if(testAnime.size() > 0)
			return false;

		return true;
	}

	// RenderPopulateButton
	public boolean isRenderPopulateButton() {
		return renderPopulateButton;
	}

	public void setRenderPopulateButton(boolean renderPopulateButton) {
		this.renderPopulateButton = renderPopulateButton;
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