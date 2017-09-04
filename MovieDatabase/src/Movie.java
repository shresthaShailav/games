import java.util.*;

public class Movie 
{
	// name of the movie
	private String name;

	// An array list of Actors in the movie
	private ArrayList<Actor> actorList;
	
	// a freshness rating from rotten tomatoes
	private double rating;
	
	// constructor
	public Movie(String name)
	{
		this.name = name;
		actorList = new ArrayList<Actor>();
	}
	
	// getters and setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Actor> getActorList() 
	{
		return actorList;
	}


	public void setActorList(ArrayList<Actor> actorList) 
	{
		this.actorList = actorList;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	
}
