import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EmptySeaTest {
	Ship x;
	Ocean o;

	@Before
	public void setUp() throws Exception {
		x = new EmptySea();
		o = new Ocean();

	}

	@Test
	public void testGetShipType() {
		assertTrue(x.getShipType().equals("empty"));
	}

	@Test
	public void testOkToPlaceShipAt() {
		assertTrue(x.okToPlaceShipAt(7, 7, false, o));
		assertTrue(x.okToPlaceShipAt(0, 7, true, o));
		assertTrue(x.okToPlaceShipAt(19, 0, false, o));
		assertFalse(x.okToPlaceShipAt(20, 5, true, o));
		assertFalse(x.okToPlaceShipAt(21, 22, true, o));
	}

	@Test
	public void testPlaceShipAt() {
		x.placeShipAt(5, 5, true, o);
		assertTrue(o.getShipArray()[5][5].getShipType().equals("empty"));
	}

	@Test
	public void testShootAt() {
		assertFalse(x.shootAt(0, 0));
		assertFalse(x.shootAt(5, 5));
		assertFalse(x.shootAt(19, 19));
	}

	@Test
	public void testToString() {
		assertTrue(x.toString().equals("-"));
	}

}
