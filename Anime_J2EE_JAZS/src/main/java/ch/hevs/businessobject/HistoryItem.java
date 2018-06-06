package ch.hevs.businessobject;

public class HistoryItem {
	
	/**
	 * POJO used to display navigation history with date and time
	 */
	private String name;
	private String time;
	
	public HistoryItem(String name, String time) {
		this.name = name;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
