package ch.hevs.dummyContent;

import java.util.Date;

public class DummyAnime {

	private Long idAnime;	
	private String animeName;
	private Date startDate;
	private Date endDate;
	private String animeDescription;
	private int episodeDuration;
	private int numberOfEpisodes;
	private DummyStudio studio;
	
	public DummyAnime()
	{
		
	}
	
	public DummyAnime(Long idAnime, String animeName, Date startDate, Date endDate, String animeDescription,
			int episodeDuration, int numberOfEpisodes, DummyStudio studio) 
	{
		this.idAnime = idAnime;
		this.animeName = animeName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.animeDescription = animeDescription;
		this.episodeDuration = episodeDuration;
		this.numberOfEpisodes = numberOfEpisodes;
		this.studio = studio;
	}

	public Long getIdAnime() {
		return idAnime;
	}

	public void setIdAnime(Long idAnime) {
		this.idAnime = idAnime;
	}

	public String getAnimeName() {
		return animeName;
	}

	public void setAnimeName(String animeName) {
		this.animeName = animeName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAnimeDescription() {
		return animeDescription;
	}

	public void setAnimeDescription(String animeDescription) {
		this.animeDescription = animeDescription;
	}

	public int getEpisodeDuration() {
		return episodeDuration;
	}

	public void setEpisodeDuration(int episodeDuration) {
		this.episodeDuration = episodeDuration;
	}

	public int getNumberOfEpisodes() {
		return numberOfEpisodes;
	}

	public void setNumberOfEpisodes(int numberOfEpisodes) {
		this.numberOfEpisodes = numberOfEpisodes;
	}

	public DummyStudio getStudio() {
		return studio;
	}

	public void setStudio(DummyStudio studio) {
		this.studio = studio;
	}
	
	
}
