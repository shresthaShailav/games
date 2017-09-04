import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CruiserTest {

	Ship x;
	Ship y;
	Ocean o;
	
	@Before
	public void setUp() throws Exception {
		
		x = new Cruiser();
		y = new Cruiser();
		o = new Ocean(0);
		
	}

	@Test
	public void testGetShipType() {
		assertTrue(x.getShipType().equals("cruiser"));
	}

	@Test
	public void testOkToPlaceShipAt() {
		assertTrue(x.okToPlaceShipAt(5, 5, true, o));
		assertFalse(x.okToPlaceShipAt(15, 15, true, o));
		assertFalse(y.okToPlaceShipAt(17, 3, false, o));
		assertFalse(y.okToPlaceShipAt(5, 19, true, o));
	}

	@Test
	public void testPlaceShipAt() {
		x.placeShipAt(7, 7, true, o);
		y.placeShipAt(8, 7, false, o);
		assertFalse(x.okToPlaceShipAt(5,  9, false, o));
		assertFalse(x.okToPlaceShipAt(8, 8, true, o));
	}

	@Test
	public void testShootAt() {
		x.placeShipAt(9, 9, true, o);
		for (int i = 9; i < (9 + 6); i++)
		{
			assertFalse(x.isSunk());
			x.shootAt(9, i);
		}
		assertTrue(x.isSunk());
		
		y.placeShipAt(3, 3, false, o);
		for (int i = 3; i < (3 + 6); i++)
		{
			assertFalse(y.isSunk());
			y.shootAt(i, 3);
		}
		assertTrue(y.isSunk());
	}

	@Test
	public void testIsSunk() {
		x.placeShipAt(9, 9, true, o);
		for (int i = 9; i < (9 + 6); i++)
		{
			assertFalse(x.isSunk());
			x.shootAt(9, i);
		}
		assertTrue(x.isSunk());
		
		y.placeShipAt(3, 3, false, o);
		for (int i = 3; i < (3 + 6); i++)
		{
			assertFalse(y.isSunk());
			y.shootAt(i, 3);
		}
		assertTrue(y.isSunk());
	}

}
