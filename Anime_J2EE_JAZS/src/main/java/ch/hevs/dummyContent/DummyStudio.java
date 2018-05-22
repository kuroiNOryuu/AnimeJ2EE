package ch.hevs.dummyContent;

public class DummyStudio {

	private Long idStudio;	
	private String studioName;
	
	public DummyStudio(Long idStudio, String studioName) {

		this.idStudio = idStudio;
		this.studioName = studioName;
	}

	public Long getIdStudio() {
		return idStudio;
	}

	public void setIdStudio(Long idStudio) {
		this.idStudio = idStudio;
	}

	public String getStudioName() {
		return studioName;
	}

	public void setStudioName(String studioName) {
		this.studioName = studioName;
	}
}
