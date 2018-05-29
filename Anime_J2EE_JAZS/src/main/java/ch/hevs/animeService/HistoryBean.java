package ch.hevs.animeService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;

@Stateful
public class HistoryBean implements History{

	private List<String> consultedAnimes;
	
	@PostConstruct
	public void initialize(){
		consultedAnimes = new ArrayList<String>();
	}
	
	@PreDestroy
	public void clear(){
		consultedAnimes.clear();
	}
	
	@Override
	public void addConsultedAnimes(String name) {
		consultedAnimes.add(name);	
	}

	@Override
	public List<String> getConsultedAnimes() {
		return consultedAnimes;
	}

}
