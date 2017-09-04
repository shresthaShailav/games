import java.util.Random;
import java.util.Scanner;


public class WhackAMole 
{
	public int score, molesLeft, attemptsLeft;
	public char[][] moleGrid;
	
	
	// Specify the number of Attempts and grid Dimensions
	WhackAMole(int numAttempts, int gridDimensions)
	{
		//m
		attemptsLeft = numAttempts;
		score = 0;
		molesLeft = 0;
		moleGrid = new char[gridDimensions][gridDimensions];
		for (int i = 0; i < gridDimensions; i++)
		{
			for (int j = 0; j < gridDimensions; j++)
			{
				moleGrid [i][j] = '*';
			}
		}
	}
	
	// Given a location, place the mole in that location.
	public boolean place(int x, int y)
	{
		if(moleGrid[x][y] != 'M')
		{
			moleGrid[x][y] = 'M';
			molesLeft++;
			return true;			
		}
		else
			return false;
	}
	
	
	// Given a location, place a whack at that location
	public void whack(int x, int y)
	{
		if (moleGrid[x][y] == 'M')
		{
			moleGrid[x][y] = 'W';
			molesLeft--;
			score++;
		}
		attemptsLeft--;
	}
	
	// Print Grid to user without showing the moles
	public void printGridToUser()
	{
		for(int i = 0; i < moleGrid.length; i++)
		{
			for(int j = 0; j < moleGrid.length; j++)
			{
				if(moleGrid[i][j] == 'W')
					System.out.printf("W");
				else
					System.out.printf("*");
			}
			System.out.println();
		}
		
	}
	
	// Print the Grid completely
	public void printGrid()	
	{
		System.out.println();
		for(int i = 0; i < moleGrid.length; i++)
		{
			for(int j = 0; j < moleGrid.length; j++)
			{
				if(moleGrid[i][j] == 'W')
					System.out.printf("W");
				else if(moleGrid[i][j] == 'M')
					System.out.printf("M");
				else
					System.out.printf("*");
			}
			System.out.println();
		}	
	}
	
	public static void main(String[] args) 
	{
		// Instantiate object
		WhackAMole game = new WhackAMole(50, 10);
		
		// place 10 moles randomly
		Random rand = new Random();
		while(game.molesLeft < 10)
			game.place(rand.nextInt(10), rand.nextInt(10));

		// prompt location for whack
		Scanner scan = new Scanner(System.in);
		while(true)
		{
			// display game board and stats
			game.printGridToUser();
			System.out.printf("\nAttempts left = %d\nScore = %d\nMoles left = %d",  game.attemptsLeft, game.score, game.molesLeft);
			System.out.printf("\nx: ");
			int x = scan.nextInt();
			System.out.printf("y: ");
			int y = scan.nextInt();
			
			// case : user gives up
			if(x == (-1) && y == (-1))
			{
				game.printGrid();
				break;
			}
			// case : user continues with the board
			if((x >= 0 && x < 10) && (y >= 0 && y < 10))
			{
				game.whack(x, y);
			}
			
			// case : user wins the board
			if(game.molesLeft == 0)
			{
				game.printGrid();
				System.out.println("You WON!");
				break;
			}
			
			// case : num of attempts end
			if(game.attemptsLeft == 0)
			{
				game.printGrid();
				System.out.println("Sorry! No more attempts");
				break;
			}
			
		}  
		scan.close();

	}

}

