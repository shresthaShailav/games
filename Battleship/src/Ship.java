
public abstract class Ship {
	// the row(0 - 19) which contains the bow(front) of the ship
	private int bowRow;
	
	// the column(0 - 19) which contains the bow(front) of the ship
	private int bowColumn;
	
	// the number of squares occupied by the ship (an Empty sea has length 1)
	private int length;
	
	// true if the ship occupies a single row, false otherwise (Ships will be placed either horizontally or vertically in the ocean)
	private boolean horizontal;
	
	// a boolean array of size 8 that records hits (only battleships use all the locations ,the others will use fewer)
	private boolean[] hit;

	public int getBowRow() {
		return bowRow;
	}

	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}

	public int getBowColumn() {
		return bowColumn;
	}

	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	public boolean[] getHit() {
		return hit;
	}

	public void setHit(boolean[] hit) {
		this.hit = hit;
	}
	
	// abstract methods
	
	abstract String getShipType();
	
	/*
	 * 	returns true if it is okay to put a ship of this length with it's bow in this location, with the given orientation and returs false otherwise.
	 *  the ship must not overlap or touch other ship (vertically, horizontally and diagonally)
	 *  it must not stick out beyond the array.
	 *  Does not actually change the ship or the ocean.
	 *  Just says whether it is okay to do so.
	 */
	abstract boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean);
	
	/*
	 * "Puts" the ship in the ocean
	 * 	involves giving values to the bowRow, bowColumn, and horizontal instance variables in the ship
	 * 	also involves putting reference to the ship in each of 1 or more locations (up to 8) in the ship array in the Ocean object
	 *  (Note : This will be as many as eight identical references; you can't refer to a "part" of a ship, only the whole ship.)
	 */
	abstract void placeShipAt(int row, int column, boolean horizontal, Ocean ocean);
	
	/*
	 * If a part of the ship occupies the given row and column, and the ship hasn't been sunk, 
	 * 		mark that part of the ship as "hit" (in the hit array, 0 indicates the bow)
	 * 		and return true, otherwise false. 
	 */
	abstract boolean shootAt(int row, int column);
	
	/*
	 * Returns true if every part of the ship has been hit, false otherwise
	 */
	abstract boolean isSunk();

	/*
	 * Returns a single character Stirng to use in the Ocean's print method
	 * This method should return "x" if the ship has been sunk, "S" if it has not been sunk
	 * This method can be used to print out locations in the ocean that have been shot at;
	 * it should not be used to print locations that have not been shot at
	 * 
	 * Since toString behaves exactly the same for all the ship types, it can be moved to the Ship Classs
	 * 
	 * Note that the toString method for the EmptySea class has to override the Ship class's implementation,
	 * In order to figure out what needs to be done, please see the description of the print method in the Ocean class
	 */	
	@Override
	public String toString() {
		if (this.isSunk())
			return "x";
		else 
			return "S" ;
	}
	
}

