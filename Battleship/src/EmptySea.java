
public class EmptySea extends Ship{
	// Describes a part of the ocean that does not have a ship in it
	// While it might seem silly to have a lack of ship be 
	// a type of ship, this trick does simplyfy a number of things
	
	/*
	 *  You may wonder why "EmptySea" is a type of Ship
	 *  The answer is that the Ocean contains a Ship array, every location of which is,
	 *  or can be, a reference to some Ship. 
	 *  If a particular location is empty, the obvious thing to do is to put a null in that location.
	 *  But this obvious approach has the problem that, every time we look at some location in the array,
	 *  we have to check if it is null. 
	 *  By putting a non-null value in empty locations, denoting the absence of a ship,
	 *  we can save all that null checking
	 */
	
	
	EmptySea() {		
		// set length
		this.setLength(1);
		
		// initialize hit array
		boolean[] hit = new boolean[8];
		for (int i = 0; i <hit.length; i++)
			hit[i] = false;
		this.setHit(hit);
	}

	@Override
	String getShipType() {
		return "empty";
	}
	
	
	
	@Override
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		
		// if the sea does not contain any other ships
		// should return false
		
		// however our program doesn't require it to be placed so return true (for all valid values) anyway for now.. modify later
		
		if ((row >= 0 && row <= 19) && (column >= 0 && column <= 19))
			return true;
		return false;
	}

	@Override
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {

		if (okToPlaceShipAt(row, column, horizontal, ocean))
		{
			ocean.getShipArray()[row][column] = this;
			this.setBowRow(row);
			this.setBowColumn(column);
			this.setHorizontal(true);
		}
	}

	/*
	 * This method overrides shootAt (int row, int column) that is inherited from Ship,
	 * and always returns false indicating that nothing was hit 
	 */
	@Override
	boolean shootAt(int row, int column) {
		this.getHit()[0] = true;
		return false;
	}

	/*
	 * This method overrides isSunk() that is inherited from Ship, and always returns false to 
	 * indicate that you didn't sink anything.
	 */
	@Override
	boolean isSunk() {
		return false;
	}

	/*
	 * Returns a singlt character String to use in the OCeans's print method.
	 * Since this is the empytsea, you could choose to have an unoccupid sea in many ways
	 */
	@Override
	public String toString() {
		if (this.getHit()[0])
			return "-";
		return "."; 
	}
	
	

}
