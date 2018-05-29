package ch.hevs.animeService;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

@Stateful
public class HistoryBean implements History{

	private List<String> consultedAnimes;
	
	public HistoryBean(){
		consultedAnimes = new ArrayList<String>();
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
