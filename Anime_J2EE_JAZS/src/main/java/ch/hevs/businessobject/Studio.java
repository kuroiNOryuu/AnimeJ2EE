package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Studio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STUDIO_ID", unique = true, nullable = false)
	private Long idStudio;
	
	@Column(unique = true, nullable = false)
	private String studioName;
	
	// Relations
	@OneToMany(mappedBy="studio", cascade = CascadeType.ALL)
	private Set<Anime> animes;
	
	// IdStudio
	public Long getIdStudio() {
		return idStudio;
	}

	public void setIdStudio(Long idStudio) {
		this.idStudio = idStudio;
	}

	// StudioName
	public String getStudioName() {
		return studioName;
	}

	public void setStudioName(String studioName) {
		this.studioName = studioName;
	}
	
	// Constructor
	public Studio(){
		this.animes = new HashSet<Anime>();
	}
	
	public Studio(String studioName){
		this.studioName = studioName;
		this.animes = new HashSet<Anime>();
	}

}
