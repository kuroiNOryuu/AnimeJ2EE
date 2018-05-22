package ch.hevs.dummyContent;

public class DummyStudio {

	private int idStudio;	
	private String studioName;
	
	public DummyStudio(int idStudio, String studioName) {

		this.idStudio = idStudio;
		this.studioName = studioName;
	}

	public int getIdStudio() {
		return idStudio;
	}

	public void setIdStudio(int idStudio) {
		this.idStudio = idStudio;
	}

	public String getStudioName() {
		return studioName;
	}

	public void setStudioName(String studioName) {
		this.studioName = studioName;
	}
}
