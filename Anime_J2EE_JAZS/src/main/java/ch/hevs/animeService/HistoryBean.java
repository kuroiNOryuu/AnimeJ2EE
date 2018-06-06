package ch.hevs.animeService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;

import ch.hevs.businessobject.HistoryItem;

@Stateful
public class HistoryBean implements History{

	private List<HistoryItem> consultedAnimes;
	
	@PostConstruct
	public void initialize(){
		consultedAnimes = new ArrayList<HistoryItem>();
	}
	
	@PreDestroy
	public void clear(){
		consultedAnimes.clear();
	}
	
	@Override
	public void addConsultedAnimes(HistoryItem item) {
		consultedAnimes.add(item);	
	}

	@Override
	public List<HistoryItem> getConsultedAnimes() {
		return consultedAnimes;
	}

}
