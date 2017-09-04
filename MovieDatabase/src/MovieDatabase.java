import java.util.*;
import java.io.*;

public class MovieDatabase 
{
	// An arrayList of Movies
	private ArrayList<Movie> movieList;
	
	// An ArrayList of actors
	private ArrayList<Actor> actorList;
	
	/*
	 * Constructor that just creates a new movieList and a new actorList.
	 * At the time of construction, both these lists will be empty
	 */
	public MovieDatabase()
	{
		movieList = new ArrayList<Movie>();
		actorList = new ArrayList<Actor>();
	}

	// getters
	public ArrayList<Movie> getMovieList() {
		return movieList;
	}

	public ArrayList<Actor> getActorList() {
		return actorList;
	}
	
	/*
	 * If movie in database, ignores the request
	 * If movie is new, movie object created and added to the movie list
	 * If any actors happen to be new, they will be added to actor list
	 * @Params
	 * name - name of the movie not currently in database
	 * actors - list of actors in that movie
	 */
	public void addMovie(String name, String[] actors)
	{
		Boolean exists = false;
		int movie_index = 0;
		
		// check if movie exists
		for (int i = 0; i < getMovieList().size(); i++)
		{
			if (getMovieList().get(i).getName().equals(name))
			{
				exists = true;
				movie_index = i;
				break;
			}
		}
		
		if (!exists)
		{
			Movie newMovie = new Movie(name);
			getMovieList().add(newMovie);
			
			for (int i = 0; i < actors.length; i++)
			{
				// check if actor is list
				Boolean actor_exists = false;
				int actor_index = 0;
				
				for (int j = 0; j < getActorList().size(); j++)
				{
					if (getActorList().get(j).getName().equals(actors[i]))
					{
						actor_exists = true;
						actor_index = j;
						break;
					}
					
				}	
				
				if (actor_exists)
				{
					getActorList().get(actor_index).getMovies().add(newMovie);
					newMovie.getActorList().add(getActorList().get(actor_index));
				}
				else
				{
					Actor newActor = new Actor(actors[i]);
					getActorList().add(newActor);
					
					newMovie.getActorList().add(newActor);
					newActor.getMovies().add(newMovie);
				}
			}
		}
		
		else
		{
			// hence movie exists
			
			for (int i = 0; i < actors.length; i++)
			{
				// check if actor is list
				Boolean actor_exists = false;
				int actor_index = 0;
				
				for (int j = 0; j < getActorList().size(); j++)
				{
					if (getActorList().get(j).getName().equals(actors[i]))
					{
						actor_exists = true;
						actor_index = j;
						break;
					}
					
				}	
				
				if (actor_exists)
				{
					// actor exists and movie exists
					Actor actor = getActorList().get(actor_index);
					Movie movie = getMovieList().get(movie_index);
					
					actor.getMovies().add(movie);
					movie.getActorList().add(actor);
				}
				else
				{
					// movie exists but actor does not exist
					
					Actor newActor = new Actor(actors[i]);
					getActorList().add(newActor);
					
					Movie movie = getMovieList().get(movie_index);
					movie.getActorList().add(newActor);
					newActor.getMovies().add(movie);
				}
			}
		}
		
	}
	
	/*
	 * Add a rating for this movie.
	 */
	// Assume that the name argument will definitely a name that is currently in the database
	public void addRating(String name, double rating)
	{
		for (int i = 0; i < getMovieList().size(); i++)
		{
			Movie x = getMovieList().get(i);
			if (x.getName().equals(name))
			{
				x.setRating(rating);
			}
		}
	}
	
	/*
	 * Overwrite the current rating of a movie with this new rating.
	 */
	// Assume that the name argument will definitely be a name that is currently in the database.
	public void updateRating(String name, double rating)
	{
		for (int i = 0; i < getMovieList().size(); i++)
		{
			Movie x = getMovieList().get(i);
			if (x.getName().equals(name))
			{
				x.setRating(rating);
			}
		}
	}
	
	/*
	 * Returns the name of the actor tha has the best average rating in their movies
	 */
	// In case of a tie, return one of the best actors
	public String getBestActor()
	{
		Actor best = null;
		double highest = 0;
		for (int i = 0; i < getActorList().size(); i++)
		{
			Actor x = getActorList().get(i);
			
			// calculate average rating of the actor
			double sum = 0;
			double avg = 0;
			for (Movie m : x.getMovies())
			{
				sum += m.getRating();
			}
			avg = sum / x.getMovies().size();
			
			if (i == 0)
			{
				best = x;
				highest = avg;
				continue;
			}
			
			if (avg > highest)
			{
				best = x;
				highest = avg;
			}
			
		}
		return best.getName();
	}
	
	
	/*
	 * Returns the name of the movie with the highest rating
	 */
	// In case of tie, return one ot the best movies.
	public String getBestMovie()
	{
		Movie best = null;
		double rating = 0;
		
		for (int i = 0; i < getMovieList().size(); i++)
		{
			Movie x = getMovieList().get(i);
			if (i == 0)
			{
				rating = x.getRating();
				best = x;
				continue;
			}
			if (x.getRating() > rating)
			{
				best = x;
				rating = x.getRating();
			}
		}
		return best.getName();
	}
	
	public static void main(String args[])
	{
		// create a new instance of the MovieDatavase
		MovieDatabase db1 = new MovieDatabase();
		
		// Add all the movies in the file movies.txt	

		try 
		{
			File movies = new File("movies.txt");
			Scanner scanner = new Scanner(movies);
			while (scanner.hasNextLine())
			{
				// get each line
				String line = scanner.nextLine();
				
				// recover comma separated values
				ArrayList<String> list = new ArrayList<String>(Arrays.asList(line.split("\\s*,\\s*")));
				
				// add to database
				
				
				String actor_name = list.get(0);
				Actor newActor = new Actor(actor_name);
				Boolean ActorExists = false;
				int actor_index = 0;
				
				// check if actor is in actorlist
				for (int i = 0; i < db1.getActorList().size(); i++)
				{
					if (newActor.getName().equals(db1.getActorList().get(i).getName()))
					{
						ActorExists = true;
						actor_index = i;
						break;
					}
				}
				
				if (!ActorExists)
				{
					// actor does not exist. hence add actor and movies
					db1.getActorList().add(newActor);
					
					// for each movie
					for (int i = 1; i < list.size(); i++)
					{	
						// add to database if new
						Movie newMovie = new Movie(list.get(i));
						Boolean movieExists = false;
						int movie_index = 0;

						
						// check if movie is already in database
						for (int j = 0; j < db1.getMovieList().size(); j++)
						{
							if (newMovie.getName().equals(db1.getMovieList().get(j).getName()))
							{
								// movie already exists
								movieExists = true;
								movie_index = j;
								break;
							}
						}
						
						
	
						if (!movieExists)
						{
							// actor as well as movie does not exist
							newMovie.getActorList().add(newActor);
							newActor.getMovies().add(newMovie);
							db1.getMovieList().add(newMovie);

						}
						else
						{
							// movie exists but actor does not exist
							newActor.getMovies().add(db1.getMovieList().get(movie_index));
							db1.getMovieList().get(movie_index).getActorList().add(newActor);

						}
					}	
				}
				else 
				{
					// actor was already added to the list
					
					// for each movie
					for (int i = 1; i < list.size(); i++)
					{
						// add to database if new
						Movie newMovie = new Movie(list.get(i));
						Boolean movieExists = false;
						int movie_index = 0;
						
						// check if movie already exists in database
						for (int j = 0; j < db1.getMovieList().size(); j++)
						{
							if (db1.getMovieList().get(j).getName().equals(newMovie.getName()))
							{
								movieExists = true;
								movie_index = j;
								break;
							}
						}
						
						if (!movieExists)
						{
							// actor already exist but movies does not
							newMovie.getActorList().add(newActor);
							db1.getActorList().get(actor_index).getMovies().add(newMovie);
							db1.getMovieList().add(newMovie);
						}
						else
						{
							// actor and movies already exist
							Actor actor = db1.getActorList().get(actor_index);
							Movie movie = db1.getMovieList().get(movie_index);
							actor.getMovies().add(movie);
							movie.getActorList().add(actor);
						}
					}
					
				}

			}
			scanner.close();

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		
		// Go through the ratings of the movies in the 	/*file ratings.txt and add the ratings for the movies
		
		try 
		{
			File rating = new File("ratings.txt");
			Scanner scanner = new Scanner(rating);
			
			while (scanner.hasNextLine())
			{
				// read each line
				String line = scanner.nextLine();
				
				// extract movie name and rating
				ArrayList<String> list = new ArrayList<String>(Arrays.asList(line.split("\t")));
				
				for (int i = 0; i < db1.getMovieList().size(); i++)
				{

					if (db1.getMovieList().get(i).getName().equals(list.get(0)))
					{
						// add rating to the movie
						double rate = Double.parseDouble(list.get(1));
						db1.getMovieList().get(i).setRating(rate);
						break;
					}
				}
				
				
			}
		scanner.close();	
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return;
		}
		
		// Now call the methods that you created and print out the name of the best actor and the name of the highest rated movie
		System.out.println(db1.getBestActor());
		System.out.println(db1.getBestMovie());
	}
	

}