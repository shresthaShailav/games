import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LightCruiserTest {
	Ship x;
	Ship y;
	Ocean o;

	@Before
	public void setUp() throws Exception {
		x = new LightCruiser();
		y = new LightCruiser();
		o = new Ocean(0);
	}

	@Test
	public void testGetShipType() {
		assertTrue(x.getShipType().equals("light cruiser"));
	}

	@Test
	public void testOkToPlaceShipAt() {
		assertTrue(x.okToPlaceShipAt(5, 5, true, o));
		assertTrue(x.okToPlaceShipAt(15, 15, true, o));
		assertFalse(x.okToPlaceShipAt(16, 16, true, o));
		assertFalse(y.okToPlaceShipAt(17, 3, false, o));
		assertFalse(y.okToPlaceShipAt(5, 19, true, o));
		assertTrue(y.okToPlaceShipAt(12, 12, false, o));
		assertFalse(x.okToPlaceShipAt(16, 9, false, o));
		assertFalse(x.okToPlaceShipAt(21, 19, true, o));
		assertTrue(y.okToPlaceShipAt(19, 4, true, o));
	}

	@Test
	public void testPlaceShipAt() {
		x.placeShipAt(12, 12, true, o);
		y.placeShipAt(12, 11, false, o);
		assertFalse(x.okToPlaceShipAt(9,  16, false, o));
		assertFalse(x.okToPlaceShipAt(9, 16, true, o));
		assertTrue(x.okToPlaceShipAt(8, 8, true, o));
		assertFalse(y.okToPlaceShipAt(12,  16, false, o));
		assertFalse(x.okToPlaceShipAt(12, 17, false, o));
	}

	@Test
	public void testShootAt() {
		x.placeShipAt(9, 9, true, o);
		for (int i = 9; i < (9 + 5); i++)
		{
			assertFalse(x.isSunk());
			x.shootAt(9, i);
		}
		assertTrue(x.isSunk());
		
		y.placeShipAt(3, 3, false, o);
		for (int i = 3; i < (3 + 5); i++)
		{
			assertFalse(y.isSunk());
			y.shootAt(i, 3);
		}
		assertTrue(y.isSunk());
	}

	@Test
	public void testIsSunk() {
		x.placeShipAt(9, 9, true, o);
		for (int i = 9; i < (9 + 5); i++)
		{
			assertFalse(x.isSunk());
			x.shootAt(9, i);
		}
		assertTrue(x.isSunk());
		
		y.placeShipAt(3, 3, false, o);
		for (int i = 3; i < (3 + 5); i++)
		{
			assertFalse(y.isSunk());
			y.shootAt(i, 3);
		}
		assertTrue(y.isSunk());
	}

}
