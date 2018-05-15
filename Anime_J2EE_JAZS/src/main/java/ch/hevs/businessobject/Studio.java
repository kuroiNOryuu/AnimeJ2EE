package ch.hevs.businessobject;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Studio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idStudio;
	
	private String studioName;
	
	// Relations
	@OneToMany(mappedBy="studio")
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

}
