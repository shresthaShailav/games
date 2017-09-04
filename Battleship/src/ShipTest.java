import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShipTest {
	Ship x, y;
	Ocean o;

	@Before
	public void setUp() throws Exception {
		x = new BattleCruiser();
		y = new Cruiser();
		o = new Ocean();
	}

	@Test
	public void testToString() {
		assertTrue(x.toString().equals("S"));
	}

}
