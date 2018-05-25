package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.animeService.AnimeList;
import ch.hevs.businessobject.Anime;
import ch.hevs.businessobject.Studio;

/**
 * TransferBean.java
 * 
 */
@ManagedBean(name="animeApp")
@SessionScoped
public class AnimeAppBean
{
	private AnimeList animeList;
	private List<Anime> animes;
	private List<Studio> studios;
	private List<String> studioNames;
	private Anime anime;  
	private Studio studio;
	private String studioName;
	private boolean renderPopulateButton;
	private long idAnimeToDelete;

	@PostConstruct
	public void initialize() throws NamingException {

		// JNDI to inject reference to AnimeList EJB
		InitialContext ctx = new InitialContext();
		animeList = (AnimeList) ctx.lookup("java:global/Anime_J2EE_JAZS-0.0.1-SNAPSHOT/AnimeListBean!ch.hevs.animeService.AnimeList");    

		getContent();

		//disable populateDb button if DB not empty
		setRenderPopulateButton(isDbNotPopulated());
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

	// studioNames
	public List<String> getStudioNames()
	{
		return studioNames;
	}

	public void setStudioNames(List<String> studioNames)
	{
		this.studioNames = studioNames;
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

	// studioName
	public String getStudioName()
	{
		return studioName;
	}

	public void setStudioName(String studioName)
	{
		this.studioName = studioName;
	}
	
	// idAnimeToDelete
	public long getIdAnimeToDelete() {
		return idAnimeToDelete;
	}

	public void setIdAnimeToDelete(long idAnimeToDelete) {
		this.idAnimeToDelete = idAnimeToDelete;
	}

	// populateDB
	public String populate()
	{
		animeList.populate();
		setRenderPopulateButton(false); //disable populateDb button after click
		getContent();

		return "home";
	}

	// Get necessary content for homepage display (full anime list)
	public void getContent()
	{
		animes = animeList.getAnimes();
		studios = animeList.getStudios();
		studioNames = new ArrayList<String>();
		for(Studio s : studios)
		{
			studioNames.add(s.getStudioName());
		}
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
	private long animeId;

	public String details()
	{
		anime = getAnimeById(animeId);
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
	
	public String removeAnime()
	{
		System.out.println("DEBUG : REMOVE ANIME " + idAnimeToDelete);
		anime = getAnimeById(idAnimeToDelete);		
		//animeList.deleteAnime(anime);	
		return "home";
	}
}