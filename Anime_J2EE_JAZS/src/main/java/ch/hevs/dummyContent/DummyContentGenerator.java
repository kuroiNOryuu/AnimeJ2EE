package ch.hevs.dummyContent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.hevs.dummyContent.DummyAnime;

public class DummyContentGenerator {
	
	private List<DummyAnime> dummyAnimes = new ArrayList<DummyAnime>();
	private List<DummyStudio> dummyStudios = new ArrayList<DummyStudio>();
	private Date startDate;
	private Date endDate;
	
	public DummyContentGenerator() throws ParseException
	{
		// Create Studios
	    DummyStudio studio1 = new DummyStudio(000001L,"Arms");
	    DummyStudio studio2 = new DummyStudio(000002L,"Madhouse");
	    
	    dummyStudios.add(studio1);
	    dummyStudios.add(studio2);
	    
	 // Create Animes
	    
	    startDate = new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2004-07-25" );
	    endDate = new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2004-10-17" );
	    DummyAnime anime1 = new DummyAnime(000005L, "Elfen Lied", startDate, endDate,"from dummy content", 25, 13, studio1);
	    
	    startDate = new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2006-10-4" );
	    endDate = new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2007-06-27" );
	    DummyAnime anime2 = new DummyAnime(000006L,"Death Note", startDate,	endDate, "from dummy content", 23, 37, studio2);
	    
	    startDate = new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2014-04-09" );
	    endDate = new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2014-06-25" );
	    DummyAnime anime3 = new DummyAnime(000007L, "No Game, No Life", startDate, endDate, "from dummy content", 23, 12, studio2);
	    
	    dummyAnimes.add(anime1);
	    dummyAnimes.add(anime2);
	    dummyAnimes.add(anime3);
	}

	public List<DummyAnime> getDummyAnimes() {
		return dummyAnimes;
	}

	public void setDummyAnimes(List<DummyAnime> dummyAnimes) {
		this.dummyAnimes = dummyAnimes;
	}

	public List<DummyStudio> getDummyStudios() {
		return dummyStudios;
	}

	public void setDummyStudios(List<DummyStudio> dummyStudios) {
		this.dummyStudios = dummyStudios;
	} 
	
	
}
