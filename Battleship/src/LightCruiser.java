
public class LightCruiser extends Ship {
	
	
	LightCruiser ()
	{
		// set length
		this.setLength(5);
		
		// initialize hit array
		boolean[] hit = new boolean[8];
		for (int i = 0; i <hit.length; i++)
			hit[i] = false;
		this.setHit(hit);
	}	

	 @Override
	 String getShipType(){
		 return "light cruiser";
	 }

	@Override
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if (horizontal)
		{
			// check for fits row wise
			if (column > (ocean.getShipArray().length - this.getLength()))
				return false;
			
			// check for other ships in path
			for (int i = 0; i < this.getLength(); i++)
				if (!ocean.getShipArray()[row][column + i].getShipType().equals("empty"))
					return false;
			
			// check that the ships will not be adjacent to other ships vertically horizontally and diagonally
			// be wary of edge cases
			
			// vertical cases 
			int endl = column - 1;
			int endr = column + this.getLength();
			
			if ((endl >= 0 && endl <= 19))
				if (!ocean.getShipArray()[row][endl].getShipType().equals("empty"))
					return false;
			
			if ((endr >= 0 && endr <= 19))
				if (!ocean.getShipArray()[row][endr].getShipType().equals("empty"))
					return false;
			
			// horizontal cases
			int above = row - 1;
			int below = row + 1;
			if ((above >= 0 && above <= 19))
				for (int i = 0; i < this.getLength(); i++)
					if (!ocean.getShipArray()[above][column + i].getShipType().equals("empty"))
						return false;
			
			if ((below >= 0 && below <= 19))
				for (int i = 0; i < this.getLength(); i++)
					if (!ocean.getShipArray()[below][column + i].getShipType().equals("empty"))
						return false;
			
			// diagonal cases
			if ((above >= 0 && above <= 19))
			{
				if ((endl >= 0 && endl <= 19))
					if (!ocean.getShipArray()[above][endl].getShipType().equals("empty"))
						return false;
				if ((endr >= 0 && endr <= 19))
					if (!ocean.getShipArray()[above][endr].getShipType().equals("empty"))
						return false;
			}
			
			if ((below >= 0 && below <= 19))
			{
				if ((endl >= 0 && endl <= 19))
					if (!ocean.getShipArray()[below][endl].getShipType().equals("empty"))
						return false;
				if ((endr >= 0 && endr <= 19))
					if (!ocean.getShipArray()[below][endr].getShipType().equals("empty"))
						return false;
			}
		}
		else
		{
			// check for fits column wise
			if (row > (ocean.getShipArray().length - this.getLength()))
				return false;
			
			// check for othe ships in the path
			for (int i = 0; i < this.getLength(); i++)
				if (!ocean.getShipArray()[row + i][column].getShipType().equals("empty"))
					return false;
			
			// check that the ships will not be adjacent to other ships vertically horizontally and diagonally
			// be wary of edge cases
			///!!!
			// horizontal cases 
			int endt = row - 1;
			int endb = row + this.getLength();
			
			if ((endt >= 0 && endt <= 19))
				if (!ocean.getShipArray()[endt][column].getShipType().equals("empty"))
					return false;
			
			if ((endb >= 0 && endb <= 19))
				if (!ocean.getShipArray()[endb][column].getShipType().equals("empty"))
					return false;
			
			// vertical cases
			int left = column - 1;
			int right = column + 1;
			if ((left >= 0 && left <= 19))
				for (int i = 0; i < this.getLength(); i++)
					if (!ocean.getShipArray()[row + i][left].getShipType().equals("empty"))
						return false;
			
			if ((right >= 0 && right <= 19))
				for (int i = 0; i < this.getLength(); i++)
					if (!ocean.getShipArray()[row + i][right].getShipType().equals("empty"))
						return false;
			
			// diagonal cases
			
			if ((left >= 0 && left <= 19))
			{
				if ((endt >= 0 && endt <= 19))
					if (!ocean.getShipArray()[endt][left].getShipType().equals("empty"))
						return false;
				if ((endb >= 0 && endb <= 19))
					if (!ocean.getShipArray()[endb][left].getShipType().equals("empty"))
						return false;
			}
			
			if ((right >= 0 && right <= 19))
			{
				if ((endt >= 0 && endt <= 19))
					if (!ocean.getShipArray()[endt][right].getShipType().equals("empty"))
						return false;
				if ((endb >= 0 && endb <= 19))
					if (!ocean.getShipArray()[endb][right].getShipType().equals("empty"))
						return false;
			}
		}

		return true;
	}

	@Override
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if (this.okToPlaceShipAt(row, column, horizontal, ocean))
		{
			if (horizontal)
				for (int i = 0; i < this.getLength(); i++)
					ocean.getShipArray()[row][column + i] = this;
			else
				for (int i = 0; i < this.getLength(); i++)
					ocean.getShipArray()[row + i][column] = this;
			
			this.setBowRow(row);
			this.setBowColumn(column);
			this.setHorizontal(horizontal);
		}
	}

	@Override
	boolean shootAt(int row, int column) {
		if (!this.isSunk())
		{

			if (this.isHorizontal())
			{
				// modify hit
				this.getHit()[column - this.getBowColumn()] = true;
				return true;
			}
			else
			{
				this.getHit()[row - this.getBowRow()] = true;
				return true;
			}
			
		}
		return false;
	}

	@Override
	boolean isSunk() {
		boolean sunk = true;
		for (int i = 0; i < this.getLength(); i++)
			if (!this.getHit()[i])
				sunk = false;
		return sunk;
	}

}
