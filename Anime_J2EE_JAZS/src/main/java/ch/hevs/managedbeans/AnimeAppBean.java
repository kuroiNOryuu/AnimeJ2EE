package ch.hevs.managedbeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ch.hevs.animeService.AnimeList;
import ch.hevs.animeService.History;
import ch.hevs.businessobject.Anime;
import ch.hevs.businessobject.HistoryItem;
import ch.hevs.businessobject.Studio;

/**
 * AnimeAppBean.java
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
	private boolean favoritesContainsSomething;

	// History management
	private List<HistoryItem> consultedAnimes;
	private int nbOfConsultedAnimes;
	private String lastConsultedAnime;
	private boolean fromHome;

	@PostConstruct
	public void initialize() throws NamingException {

		// JNDI to inject reference to EJBs
		InitialContext ctx = new InitialContext();
		animeList = (AnimeList) ctx.lookup("java:global/Anime_J2EE_JAZS-0.0.1-SNAPSHOT/AnimeListBean!ch.hevs.animeService.AnimeList");
		history = (History) ctx.lookup("java:global/Anime_J2EE_JAZS-0.0.1-SNAPSHOT/HistoryBean!ch.hevs.animeService.History");

		getContent();
		setFavoritesContainsSomething(false);

		//disable populateDb button if DB not empty
		setRenderPopulateButton(isDbNotPopulated());
	}

	// Getters & setters ------------------------------------------------------------------------------------------------------------------>
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

	// idAnimeToAdd
	public Long getIdAnimeToAdd() {
		return idAnimeToAdd;
	}

	public void setIdAnimeToAdd(Long idAnimeToAdd) {
		this.idAnimeToAdd = idAnimeToAdd;
	}

	// animeNameToAdd
	public String getAnimeNameToAdd() {
		return animeNameToAdd;
	}

	public void setAnimeNameToAdd(String animeNameToAdd) {
		this.animeNameToAdd = animeNameToAdd;
	}

	// animeStartDateToAdd
	public Date getAnimeStartDateToAdd() {
		return animeStartDateToAdd;
	}

	public void setAnimeStartDateToAdd(Date animeStartDateToAdd) {
		this.animeStartDateToAdd = animeStartDateToAdd;
	}

	// animeEndDateToAdd
	public Date getAnimeEndDateToAdd() {
		return animeEndDateToAdd;
	}

	public void setAnimeEndDateToAdd(Date animeEndDateToAdd) {
		this.animeEndDateToAdd = animeEndDateToAdd;
	}

	// animeDescriptionToAdd
	public String getAnimeDescriptionToAdd() {
		return animeDescriptionToAdd;
	}

	public void setAnimeDescriptionToAdd(String animeDescriptionToAdd) {
		this.animeDescriptionToAdd = animeDescriptionToAdd;
	}

	// animeEpisodeDurationToAdd
	public int getAnimeEpisodeDurationToAdd() {
		return animeEpisodeDurationToAdd;
	}

	public void setAnimeEpisodeDurationToAdd(int animeEpisodeDurationToAdd) {
		this.animeEpisodeDurationToAdd = animeEpisodeDurationToAdd;
	}

	// animeNumberOfEpisodesToAdd
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

	// favoritesIsEmpty
	public boolean isFavoritesContainsSomething() {
		return favoritesContainsSomething;
	}

	public void setFavoritesContainsSomething(boolean favoritesContainsSomething) {
		this.favoritesContainsSomething = favoritesContainsSomething;
	}

	// consultedAnimes
	public List<HistoryItem> getConsultedAnimes() {
		return consultedAnimes;
	}

	public void setConsultedAnimes(List<HistoryItem> consultedAnimes) {
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

	// fromHome
	public boolean isFromHome() {
		return fromHome;
	}

	public void setFromHome(boolean fromHome) {
		this.fromHome = fromHome;
	}

	// Methods ------------------------------------------------------------------------------------------------------------------------------>
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
	public String details(){		
		anime = animeList.getAnimeById(animeId);
		updateConsultedAnimes(anime.getAnimeName());
		return "details";
	}

	// Go to add anime page
	public String addAnime(){		
		animeToAdd = new Anime();
		
		// Clear input fields
		studioName = null;
		animeDescriptionToAdd = null;
		animeNameToAdd = null;
		animeEndDateToAdd = null;
		animeEpisodeDurationToAdd = 0;
		animeNumberOfEpisodesToAdd = 0;
		animeStartDateToAdd = null;

		return "addForm";
	}

	// Save anime to DB and come back to home page
	public String saveNewAnime(){
		// Set animeToAdd with user input
		studio = animeList.getStudioByName(studioName);
		animeToAdd.setStudio(studio);
		animeToAdd.setAnimeDescription(animeDescriptionToAdd);
		animeToAdd.setAnimeName(animeNameToAdd);
		animeToAdd.setEndDate(animeEndDateToAdd);
		animeToAdd.setEpisodeDuration(animeEpisodeDurationToAdd);
		animeToAdd.setNumberOfEpisodes(animeNumberOfEpisodesToAdd);
		animeToAdd.setStartDate(animeStartDateToAdd);

		// Save to DB
		animeList.saveAnime(animeToAdd);

		// Redirect to home
		getContent();
		return "home";
	}

	// Remove anime from DB and refresh home page
	public String removeAnime(){
		anime = animeList.getAnimeById(idAnimeToDelete);		
		animeList.deleteAnime(anime);	
		getContent();
		return "home";
	}

	// Add an anime to favorites list and display favorites page
	public String addAnimeToFavorites(){
		animeList.addAnimeToFavorites(idFavoriteAnime);
		favoritesAnimes = (List<Anime>) animeList.getUserAnimes();
		setFavoritesContainsSomething(true);

		return "favorites";
	}

	// Remove anime from favorites list and refresh view
	public String removeAnimeFromFavorites(){	
		// Remove anime
		animeList.removeAnimeFromFavorites(idAnimeToRemoveFromFavorites);
		
		// If list is empty, adapt display
		if (favoritesAnimes.size() == 1)
			setFavoritesContainsSomething(false);
				
		favoritesAnimes = (List<Anime>) animeList.getUserAnimes();

		return "favorites";
	}

	// Go to favorites.xhtml
	public String showFavoritesList(){
		favoritesAnimes = (List<Anime>) animeList.getUserAnimes();

		// If list is empty, adapt display
		if (favoritesAnimes.get(0) != null)
			setFavoritesContainsSomething(true);

		return "favorites";
	}

	// Add anime to consulted animes list
	public void updateConsultedAnimes(String name){
		Date now = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd hh:mm");

		HistoryItem item = new HistoryItem(name, ft.format(now));
		history.addConsultedAnimes(item);

	}

	// Go back from details to previous page (home or favorites)
	public String back(){
		if(fromHome == true){
			updateHistory();
			return "home";
		}

		updateHistory();
		return "favorites";

	}

	// Update needed variables in order to display a summary on home page
	public void updateHistory()	{
		consultedAnimes = history.getConsultedAnimes();
		nbOfConsultedAnimes = consultedAnimes.size();
		if(nbOfConsultedAnimes > 0)
			lastConsultedAnime = consultedAnimes.get(consultedAnimes.size()-1).getName();
		else
			lastConsultedAnime = "none";
	}
}