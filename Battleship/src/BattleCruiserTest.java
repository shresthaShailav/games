import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BattleCruiserTest {
	Ship x;
	Ship y;
	Ocean o;
	
	@Before
	public void setUp() throws Exception {
		x = new BattleCruiser();
		y = new BattleCruiser();
		o = new Ocean(0);
		
	}

	@Test
	public void testGetShipType() {
		assertTrue(x.getShipType().equals("battlecruiser"));
	}

	@Test
	public void testOkToPlaceShipAt() {
		assertFalse(x.okToPlaceShipAt(5, 18, true, o));
		assertTrue(x.okToPlaceShipAt(5, 5, true, o));
		assertTrue(x.okToPlaceShipAt(7, 7, false, o));
		assertFalse(x.okToPlaceShipAt(16, 9, false, o));
		assertFalse(x.okToPlaceShipAt(21, 19, true, o));
	}

	@Test
	public void testPlaceShipAt() {
		x.placeShipAt(10, 4, true, o);
		assertFalse(x.okToPlaceShipAt(10, 1, true, o));
		assertTrue(x.okToPlaceShipAt(5, 1, false, o));
		assertFalse(x.okToPlaceShipAt(6,  4, false,  o));
		y.placeShipAt(5,  2,  false, o);
		assertFalse(y.okToPlaceShipAt(5, 3, true,  o));
		assertFalse(y.okToPlaceShipAt(7,  1, true, o));
	}

	@Test
	public void testShootAt() {
		x.placeShipAt(5, 5, true, o);
		o.print();
		for (int i = 5; i < (5 + 7); i++)
		{
			assertFalse(x.isSunk());
			x.shootAt(5, i);
		}
		o.print();
		assertTrue(x.isSunk());
		
		
		y.placeShipAt(10, 3,  false, o);
		for (int i = 10; i < (10 + 7); i++)
		{
			assertFalse(y.isSunk());
			y.shootAt(i, 3);
		}
		assertTrue(y.isSunk());
	}

	@Test
	public void testIsSunk() {
		x.placeShipAt(5, 5, true, o);
		x.shootAt(5, 5);
		x.shootAt(5, 6);
		x.shootAt(5, 7);
		assertFalse(x.isSunk());
		x.shootAt(5, 8);
		x.shootAt(5, 9);
		x.shootAt(5, 10);
		x.shootAt(5, 11);
		assertTrue(x.isSunk());
	}

}
