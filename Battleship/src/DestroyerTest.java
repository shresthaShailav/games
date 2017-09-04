import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DestroyerTest {
	Ship x;
	Ship y;
	Ocean o;

	@Before
	public void setUp() throws Exception {
		x = new Destroyer();
		y = new Destroyer();
		o = new Ocean(0);
	}

	@Test
	public void testGetShipType() {
		assertTrue(x.getShipType().equals("destroyer"));
	}

	@Test
	public void testOkToPlaceShipAt() {
		assertTrue(x.okToPlaceShipAt(5, 5, true, o));
		assertTrue(x.okToPlaceShipAt(15, 15, true, o)); 
		assertFalse(y.okToPlaceShipAt(17, 3, false, o));
		assertFalse(y.okToPlaceShipAt(5, 19, true, o));
		assertTrue(y.okToPlaceShipAt(16, 16, true, o));
		assertTrue(y.okToPlaceShipAt(5, 19, false, o));
		assertFalse(x.okToPlaceShipAt(21, 19, true, o));
	}

	@Test
	public void testPlaceShipAt() {
		x.placeShipAt(9, 7, true, o);
		y.placeShipAt(12, 11, false, o);
		assertFalse(x.okToPlaceShipAt(9,  10, false, o));
		assertFalse(x.okToPlaceShipAt(9, 11, false, o));
		assertFalse(x.okToPlaceShipAt(9, 11, true, o));
		assertFalse(x.okToPlaceShipAt(8, 8, true, o));
		assertTrue(x.okToPlaceShipAt(13, 3, true, o));
	}

	@Test
	public void testShootAt() {
		x.placeShipAt(13, 16, true, o);
		for (int i = 16; i < (16 + 4); i++)
		{
			assertFalse(x.isSunk());
			x.shootAt(13, i);
		}
		assertTrue(x.isSunk());
		
		y.placeShipAt(5, 8, false, o);
		for (int i = 5; i < (5 + 4); i++)
		{
			assertFalse(y.isSunk());
			y.shootAt(i, 8);
		}
		assertTrue(y.isSunk());
	}

	@Test
	public void testIsSunk() {
		x.placeShipAt(13, 16, true, o);
		for (int i = 16; i < (16 + 4); i++)
		{
			assertFalse(x.isSunk());
			x.shootAt(13, i);
		}
		assertTrue(x.isSunk());
		
		y.placeShipAt(5, 8, false, o);
		for (int i = 5; i < (5 + 4); i++)
		{
			assertFalse(y.isSunk());
			y.shootAt(i, 8);
		}
		assertTrue(y.isSunk());
	}

}
