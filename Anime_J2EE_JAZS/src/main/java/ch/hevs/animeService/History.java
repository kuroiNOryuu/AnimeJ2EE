package ch.hevs.animeService;

import java.util.List;

import javax.ejb.Local;

@Local
public interface History {
	
	void addConsultedAnimes(String name);
	List<String> getConsultedAnimes();
}
