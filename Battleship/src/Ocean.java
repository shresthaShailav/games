import java.util.Random;
public class Ocean {
	// used to quickly determine which ship is in any given location.
	private Ship[][] ships;
	
	// The total number of shots fired by the user.
	private int shotsFired;
	
	// The number of times a shot hit a ship. If the user shoots the same part of a ship
	// more than once, every hit is counted, even though the additional "hits" don't do the 
	// user any good.
	private int hitCount;
	
	// The number of ships sunk
	// Remember that you have a total of 13 ships
	private int shipsSunk;
	
	
	Ocean()
	{
		// Creates an "empty" ocean (fills the ships array with a bunch of EmpytSea instances)
		// Also initializes the game variables , such as how many shots have been fired
		
		ships = new Ship[20][20];
		// initialize an array of ships with EmptySea
		for (int i = 0; i < ships.length; i++)
			for (int j = 0; j < ships.length; j++)
			{
				EmptySea sea = new EmptySea();
				sea.placeShipAt(i, j, true, this);
			}
		
		
		// place other ships randomly into the ocean
		placeAllShipsRandomly();
		
		
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
	}
	
	Ocean(int x)
	{
		
		ships = new Ship[20][20];
		// initialize an array of ships with EmptySea
		for (int i = 0; i < ships.length; i++)
			for (int j = 0; j < ships.length; j++)
			{
				EmptySea sea = new EmptySea();
				sea.placeShipAt(i, j, true, this);
			}
		
	
		
		
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
	}
	
	
	public void placeAllShipsRandomly()
	{
		// place all randomly on the (initially empty) ocean.
		// Place larger ships before smaller ones, or you may end up with no legal place to put a large ship
		
		Random rand = new Random();
		
		// one battleship
		Ship battleship = new BattleShip();
		while(true)
		{
			// generate random values of row, column and horizontal
			int row = rand.nextInt(ships.length);
			int column = rand.nextInt(ships.length);
			boolean horizontal = rand.nextBoolean();
			
			// place ship if the values are valid
			if (battleship.okToPlaceShipAt(row, column, horizontal, this))
			{
				battleship.placeShipAt(row, column, horizontal, this);
				//System.out.println("battleship placed at " + row + " row " + column + " column");
				break;
			}
		}
		
		// one battlecruiser
		Ship battlecuriser = new BattleCruiser();
		while(true)
		{
			// generate random values of row, column and horizontal
			int row = rand.nextInt(ships.length);
			int column = rand.nextInt(ships.length);
			boolean horizontal = rand.nextBoolean();
			
			// place ship if the values are valid
			if (battlecuriser.okToPlaceShipAt(row, column, horizontal, this))
			{
				battlecuriser.placeShipAt(row, column, horizontal, this);
				//System.out.println("battlecruiser placed at " + row + " row " + column + " column");
				break;
			}
		}
		
		// 2 Cruisers
		for (int i = 0; i < 2; i++)
		{
			Ship cruiser = new Cruiser();
			while (true)
			{
				// generate random values of row, column and horizontal
				int row = rand.nextInt(ships.length);
				int column = rand.nextInt(ships.length);
				boolean horizontal = rand.nextBoolean();
				
				// place ship if the values are valid
				if (cruiser.okToPlaceShipAt(row, column, horizontal, this))
				{
					cruiser.placeShipAt(row, column, horizontal, this);
					//System.out.println("cruiser placed at " + row + " row " + column + " column");
					break;
				}
			}
		}
		
		// 2 light cruisers
		for (int i = 0; i < 2; i++)
		{
			Ship lcruiser = new LightCruiser();
			while (true)
			{
				// generate random values of row, column and horizontal
				int row = rand.nextInt(ships.length);
				int column = rand.nextInt(ships.length);
				boolean horizontal = rand.nextBoolean();
				
				// place ship if the values are valid
				if (lcruiser.okToPlaceShipAt(row, column, horizontal, this))
				{
					lcruiser.placeShipAt(row, column, horizontal, this);
					//System.out.println("light cruiser placed at " + row + " row " + column + " column");
					break;
				}
			}
		}
		
		// 3 destroyers
		for (int i = 0; i < 3; i++)
		{
			Ship destroyer = new Destroyer();
			while (true)
			{
				// generate random values of row, column and horizontal
				int row = rand.nextInt(ships.length);
				int column = rand.nextInt(ships.length);
				boolean horizontal = rand.nextBoolean();
				
				// place ship if the values are valid
				if (destroyer.okToPlaceShipAt(row, column, horizontal, this))
				{
					destroyer.placeShipAt(row, column, horizontal, this);
					//System.out.println("destroyer placed at " + row + " row " + column + " column");
					break;
				}
			}
		}
		
		// 4 submarines
		for (int i = 0; i < 4; i++)
		{
			Ship submarine = new Submarine();
			while (true)
			{
				// generate random values of row, column and horizontal
				int row = rand.nextInt(ships.length);
				int column = rand.nextInt(ships.length);
				boolean horizontal = rand.nextBoolean();
				
				// place ship if the values are valid
				if (submarine.okToPlaceShipAt(row, column, horizontal, this))
				{
					submarine.placeShipAt(row, column, horizontal, this);
					//System.out.println("submarine placed at " + row + " row " + column + " column");
					break;
				}
			}
		}
	
	}
	
	public boolean isOccupied(int row, int column)
	{
		// Returns true if the given location contains a ship, false if it does not
		return (!this.getShipArray()[row][column].getShipType().equals("empty"));
	}
	
	/*
	 * Returns true if the given location contains a "real" ship, still afloat, (not an EmptySea), false if it does not
	 * updates the number of shots that have been fired, and the number of hits.
	 * Note: If a location contains a "real" ship, shootAt should return true every time the user shoots at the same location
	 * Once a ship has been "sunk", additional shots at its location should return false.
	 */
	public boolean shootAt(int row, int column)
	{
		shotsFired++;
		Ship x = this.getShipArray()[row][column];
		if(x.shootAt(row, column))
		{
			this.hitCount++;
			
			if (x.isSunk())
				this.shipsSunk++;
			return true;
		}
		return false;
	}
	
	public int getShotsFired()
	{
		// Returns the number of shots fired (in this game)
		return shotsFired;
	}
	
	public int getHitCount()
	{
		// Returns the number of hits recorded (in this game).
		// All hits are counted, not just the first time a given square is hit.
		return hitCount;
	}
	
	public int getShipsSunk()
	{
		// Returns the number of ships sunk (in this game)
		return shipsSunk;
	}
	
	public boolean isGameOver()
	{
		// Returns true if all ships have been sunk, otherwise false
		if (this.getShipsSunk() < 13)
			return false;
		return true;
	}
	
	public Ship[][] getShipArray()
	{
		// Returns the 20X20 array of the ships. The methods in the Ship class that take an Ocean parameter really need to be able to look at the contents of this array;
		// the placeShipAt method even needsto modify it.
		// While it is undesirable to allow methods in one class to directly access instance variables in another class, sometimes there is just no good alternative.
		return ships;
		
	}
	
	public void print()
	{
		
		// Print column number
		for (int i = 0; i < ships.length; i++)
		{
			if (i == 0)
				System.out.printf("   ");
			System.out.printf("%3d", i);
		}
		System.out.println();
		
		// print the rest of the ocean
		for (int i = 0; i < ships.length; i++)
		{
			// print the row number in each line
			System.out.printf("%3d", i);
			
			for (int j = 0; j < ships.length; j++)
			{
				//int index;
				
				
				// distance from the bow
				int index; 	
				if (this.getShipArray()[i][j].isHorizontal())
					index = j - this.getShipArray()[i][j].getBowColumn();
				else
					index = i - this.getShipArray()[i][j].getBowRow();
				index = Math.abs(index);
				
				// if any part of the ship was hit, display the status of that ship else, display "."
				if (this.getShipArray()[i][j].getHit()[index])
					System.out.printf("%s", this.ships[i][j].toString());
				else 
					System.out.printf("*");
			}
			System.out.println();
		}

	}
	
	
	
	
}
