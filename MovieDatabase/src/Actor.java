import java.util.*;

public class Actor 
{
	// full name of the actor or actress
	private String name;
	// An ArrayList that has movies that this actor has acted in
	private ArrayList<Movie> movies;

	// constructor
	public Actor()
	{
		movies = new ArrayList<Movie>();
	}
	public Actor(String name) 
	{
		this.name = name;
		movies = new ArrayList<Movie>();
	}

	// getters and setters
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}

	public ArrayList<Movie> getMovies() 
	{
		return movies;
	}
	
	public void setMovies(ArrayList<Movie> movies) 
	{
		this.movies = movies;
	}


}
