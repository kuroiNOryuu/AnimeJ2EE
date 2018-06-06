package ch.hevs.animeService;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.HistoryItem;

@Local
public interface History {
	
	void addConsultedAnimes(HistoryItem item);
	List<HistoryItem> getConsultedAnimes();
}
