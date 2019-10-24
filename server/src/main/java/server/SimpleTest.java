package server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SimpleTest {

	@Test
	void testTeile() {
		Simple s = new Simple();
		double e = s.teile(50, 2);
		assertEquals(25, e, 0.00000000000000000000000000000000001);
		//fail("Not yet implemented");
	}
	
	@Test
	void testTeile2() {
		Simple s = new Simple();
		double e = s.teile(50, 0);
		assertEquals(0, e, 0.00000000000000000000000000000000001);
		//fail("Not yet implemented");
	}
}
