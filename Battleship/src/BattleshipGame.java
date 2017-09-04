import java.util.Scanner;
public class BattleshipGame {
	
	public Scanner scanner;
	
	private Ocean ocean;
	
	BattleshipGame() {
		ocean = new Ocean();
		scanner = new Scanner(System.in);
	}
	
	/*
	 * Accepts 5 shots from user
	 * iput format should look like : 1, 1; 0, 3; 7, 3; 9, 11; 12, 17
	 */
	public int[] getShotsFromUser() {
		int[] shot = new int[10];
		
		System.out.println("Enter shots : ");
		String input = this.scanner.nextLine();
		try {			
			// remove all spaces
			input = input.replaceAll("\\s","");
			// replace commas and semi-colons with hyphen
			input = input.replaceAll(";", "-");
			input = input.replaceAll(",", "-");
			
			// split the strings at hyphens
			String[] x = input.split("-");

			for (int i = 0; i < 10; i++)
			{
				int n = Integer.parseInt(x[i]);
				if (n >= 0 && n <= 19)
					shot[i] = n;
				else
					shot[0] = -1;
			}
			return shot;	
		}
		catch (Exception e)
		{
			// intialize shots
			System.out.println("Error! Format : 1, 1; 0, 3; 7, 3; 9, 11; 12, 17");
			for (int i = 0; i < 10; i++)
				shot[i] = -1;
		}
		return shot;

	}
	
	/*
	 * We’ll play this game on a 20x20 ocean. This is 
larger than the ocean in the traditional battleship game.
	 * 
	 * 
	 * The computer places these 13 ships on the ocean in such a way that no ships are immediately adjacent to each other, either horizontally, vertically, or diagonally.

The human player does not know where the ships are. The initial display of the ocean shows a 20 by 20 array of locations, all the same.

The human player tries to hit the ships, by calling out a row and column number. The computer responds with one bit of information 
	saying ”hit” or ”miss.” When a ship is hit but not sunk, the program does not provide any information about what kind of a ship was hit. 
	However, when a ship is hit and sinks, the program prints out a message ”You just sank a ship-type.” 
After each shot, the computer redisplays the ocean with the new information.

A ship is 
”sunk” when every square of the ship has been hit. 
Thus, it takes 8 hits to sink a battleship but only 6 to sink a 
cruiser. The objective is to sink the fleet with as few shots as 
possible.

When all ships have been sunk, the program prints out a message that the game is over, and tells the user how many shots were required.

	 */
	

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		boolean play_again = true;
		while (play_again)
		{
			BattleshipGame game = new BattleshipGame();
			int[] shots = new int[10];
			
			while (!game.ocean.isGameOver()) 
			{	
				// display ocean
				game.ocean.print();
				
				// accept shots from user
				shots = game.getShotsFromUser();
				
				// apply shots
				boolean hit = false;
				boolean sank_ship = false;
				
				if (shots[0] == -1)
				{
					System.out.println("Invalid coordinates! ");
					continue;
				}
					for (int i = 0; i < 10; i += 2)
				{
					Ship x = game.ocean.getShipArray()[shots[i]][shots[i + 1]];
					if (game.ocean.shootAt(shots[i], shots[i + 1]))
					{
						hit = true;
						// since shoot at returns true only when the ship is not sunk
						if (x.isSunk())
							sank_ship = true;
					}
						
				}
				
				// show hit or miss
				if (sank_ship)
					System.out.println("You just sank a ship!");
				else if (hit)
					System.out.println("hit");
				else
					System.out.println("miss");
				
			}
			
			
			System.out.println(game.ocean.getShipsSunk());
			System.out.println(game.ocean.getHitCount());
			System.out.println(game.ocean.getShotsFired());
			
			// play again
			System.out.println("\nDo you want to play again? y/n");
			String again = scan.nextLine();
			
			if(!again.equals("y"))
				play_again = false;
		}
		
		scan.close();
	}

}
