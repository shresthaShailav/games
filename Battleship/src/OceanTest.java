import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OceanTest {
	Ship x, y;
	Ocean o;

	@Before
	public void setUp() throws Exception {
		x = new BattleCruiser();
		y = new LightCruiser();
		o = new Ocean();
	}

	@Test
	public void testGetShotsFired() {
		assertEquals(o.getShotsFired(),0);
		o.shootAt(10, 10);
		assertEquals(o.getShotsFired(),1);
	}

}
