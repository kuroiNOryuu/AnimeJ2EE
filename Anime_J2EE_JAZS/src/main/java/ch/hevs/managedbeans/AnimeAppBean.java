package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ch.hevs.animeService.AnimeList;
import ch.hevs.animeService.History;
import ch.hevs.businessobject.Anime;
import ch.hevs.businessobject.Studio;

/**
 * TransferBean.java
 * 
 */

public class AnimeAppBean
{
	// EJB
	private AnimeList animeList;
	private History history;
	
	// Display
	private List<Anime> animes;
	private List<Studio> studios;
	private List<String> studioNames;
	private Anime anime;  
	private long animeId;
	private Studio studio;
	private String studioName;
	private boolean renderPopulateButton;
	
	// Add anime
	private Anime animeToAdd;
	private Long idAnimeToAdd;	
	private String animeNameToAdd;
	private Date animeStartDateToAdd;
	private Date animeEndDateToAdd;
	private String animeDescriptionToAdd;
	private int animeEpisodeDurationToAdd;
	private int animeNumberOfEpisodesToAdd;
	
	// Delete anime
	private long idAnimeToDelete;
	private long idFavoriteAnime;
	
	// Favorites list
	private List<Anime> favoritesAnimes;
	private long idAnimeToRemoveFromFavorites;
	
	// History management
	private List<String> consultedAnimes;
	private int nbOfConsultedAnimes;
	private String lastConsultedAnime;

	@PostConstruct
	public void initialize() throws NamingException {

		// JNDI to inject reference to EJBs
		InitialContext ctx = new InitialContext();
		animeList = (AnimeList) ctx.lookup("java:global/Anime_J2EE_JAZS-0.0.1-SNAPSHOT/AnimeListBean!ch.hevs.animeService.AnimeList");
		history = (History) ctx.lookup("java:global/Anime_J2EE_JAZS-0.0.1-SNAPSHOT/HistoryBean!ch.hevs.animeService.History");
		
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
	
	public Long getIdAnimeToAdd() {
		return idAnimeToAdd;
	}

	public void setIdAnimeToAdd(Long idAnimeToAdd) {
		this.idAnimeToAdd = idAnimeToAdd;
	}

	public String getAnimeNameToAdd() {
		return animeNameToAdd;
	}

	public void setAnimeNameToAdd(String animeNameToAdd) {
		this.animeNameToAdd = animeNameToAdd;
	}

	public Date getAnimeStartDateToAdd() {
		return animeStartDateToAdd;
	}

	public void setAnimeStartDateToAdd(Date animeStartDateToAdd) {
		this.animeStartDateToAdd = animeStartDateToAdd;
	}

	public Date getAnimeEndDateToAdd() {
		return animeEndDateToAdd;
	}

	public void setAnimeEndDateToAdd(Date animeEndDateToAdd) {
		this.animeEndDateToAdd = animeEndDateToAdd;
	}

	public String getAnimeDescriptionToAdd() {
		return animeDescriptionToAdd;
	}

	public void setAnimeDescriptionToAdd(String animeDescriptionToAdd) {
		this.animeDescriptionToAdd = animeDescriptionToAdd;
	}

	public int getAnimeEpisodeDurationToAdd() {
		return animeEpisodeDurationToAdd;
	}

	public void setAnimeEpisodeDurationToAdd(int animeEpisodeDurationToAdd) {
		this.animeEpisodeDurationToAdd = animeEpisodeDurationToAdd;
	}

	public int getAnimeNumberOfEpisodesToAdd() {
		return animeNumberOfEpisodesToAdd;
	}

	public void setAnimeNumberOfEpisodesToAdd(int animeNumberOfEpisodesToAdd) {
		this.animeNumberOfEpisodesToAdd = animeNumberOfEpisodesToAdd;
	}

	// idAnimeToDelete
	public long getIdAnimeToDelete() {
		return idAnimeToDelete;
	}

	public void setIdAnimeToDelete(long idAnimeToDelete) {
		this.idAnimeToDelete = idAnimeToDelete;
	}
	
	// animeToAdd
	public Anime getAnimeToAdd() {
		return animeToAdd;
	}

	public void setAnimeToAdd(Anime animeToAdd) {
		this.animeToAdd = animeToAdd;
	}

	// idFavoriteAnime
	public long getIdFavoriteAnime() {
		return idFavoriteAnime;
	}

	public void setIdFavoriteAnime(long idFavoriteAnime) {
		this.idFavoriteAnime = idFavoriteAnime;
	}

	// favoritesAnimes
	public List<Anime> getFavoritesAnimes() {
		return favoritesAnimes;
	}

	public void setFavoritesAnimes(List<Anime> favoritesAnimes) {
		this.favoritesAnimes = favoritesAnimes;
	}

	// AnimeId
	public long getAnimeId() {
		return animeId;
	}

	public void setAnimeId(long animeId) {
		this.animeId = animeId;
	}

	// idAnimeToRemoveFromFavorites
	public long getIdAnimeToRemoveFromFavorites() {
		return idAnimeToRemoveFromFavorites;
	}

	public void setIdAnimeToRemoveFromFavorites(long idAnimeToRemoveFromFavorites) {
		this.idAnimeToRemoveFromFavorites = idAnimeToRemoveFromFavorites;
	}

	// consultedAnimes
	public List<String> getConsultedAnimes() {
		return consultedAnimes;
	}

	public void setConsultedAnimes(List<String> consultedAnimes) {
		this.consultedAnimes = consultedAnimes;
	}

	// nbOfConsultedAnimes
	public int getNbOfConsultedAnimes() {
		return nbOfConsultedAnimes;
	}

	public void setNbOfConsultedAnimes(int nbOfConsultedAnimes) {
		this.nbOfConsultedAnimes = nbOfConsultedAnimes;
	}

	// lastConsultedAnime
	public String getLastConsultedAnime() {
		return lastConsultedAnime;
	}

	public void setLastConsultedAnime(String lastConsultedAnime) {
		this.lastConsultedAnime = lastConsultedAnime;
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
		System.out.println("DEBUG : GET CONTENT");
		animes = animeList.getAnimes();
		studios = animeList.getStudios();
		studioNames = new ArrayList<String>();
		for(Studio s : studios)
		{
			studioNames.add(s.getStudioName());
		}
		
		updateHistory();
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
	public String details()
	{		
		anime = getAnimeById(animeId);
		updateConsultedAnimes(anime.getAnimeName());
		return "details";
	}

	public Anime getAnimeById(long id) 
	{
		return animeList.getAnimeById(id);
	}

	public String addAnime()
	{
		return "addForm";
	}

	public String saveNewAnime()
	{
		// Set animeToAdd with user input
		animeToAdd = new Anime();
		studio = animeList.getStudioByName(studioName);
		System.out.println("DEBUG : ADD NEW ANIME -> " + studio.getStudioName());
		animeToAdd.setStudio(studio);
		animeToAdd.setAnimeDescription(animeDescriptionToAdd);
		animeToAdd.setAnimeName(animeNameToAdd);
		animeToAdd.setEndDate(animeEndDateToAdd);
		animeToAdd.setEpisodeDuration(animeEpisodeDurationToAdd);
		animeToAdd.setNumberOfEpisodes(animeNumberOfEpisodesToAdd);
		animeToAdd.setStartDate(animeStartDateToAdd);
		
		System.out.println("DEBUG : SAVE ANIME = " + animeToAdd.getAnimeName() + ", "+ animeToAdd.getStudio().getStudioName());
		animeList.saveAnime(animeToAdd);
		return "home";
	}
	
	public String removeAnime()
	{
		System.out.println("DEBUG : REMOVE ANIME " + idAnimeToDelete);
		anime = getAnimeById(idAnimeToDelete);		
		animeList.deleteAnime(anime);	
		return "home";
	}
	
	public String addAnimeToFavorites()
	{
		animeList.addAnimeToFavorites(idFavoriteAnime);
		favoritesAnimes = (List<Anime>) animeList.getUserAnimes();
		return "favorites";
	}
	
	public String removeAnimeFromFavorites()
	{
		System.out.println("DEBUG : REMOVE ANIME FROM FAVORITES : " + idAnimeToRemoveFromFavorites);		
		animeList.removeAnimeFromFavorites(idAnimeToRemoveFromFavorites);
		// Update screen
		return "favorites";
	}
	
	public String showFavoritesList()
	{
		favoritesAnimes = (List<Anime>) animeList.getUserAnimes();
		return "favorites";
	}
	
	public void updateConsultedAnimes(String name){
		history.addConsultedAnimes(name);
		System.out.println("DEBUG : LAST ANIME = " + consultedAnimes.get(consultedAnimes.size()-1));
	}
	
	public String backHome(){
		updateHistory();
		return "home";
	}
	
	public void updateHistory()
	{
		consultedAnimes = history.getConsultedAnimes();
		nbOfConsultedAnimes = consultedAnimes.size();
		if(nbOfConsultedAnimes > 0)
			lastConsultedAnime = consultedAnimes.get(consultedAnimes.size()-1);
	}
}