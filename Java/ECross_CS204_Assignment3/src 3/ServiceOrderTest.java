import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ServiceOrderTest {
	ServiceOrder order1, order2;

	@Before
	/** This method is run before each individual test
	 *   Creates an object of serviceOrder 
	 */
	public void setUp() throws Exception {
		order1 = new ServiceOrder(8, "Patterson, Jimmy","Chevrolet","Malibu","no","yes","yes");
		order2 = new ServiceOrder(4, "Williams, June","Nissan","Sentra","yes","yes","no");
	}

	@After
	/** This method is run after each individual test
	 *   It sets the service order reference to null so the garbage
	 *   collector can reclaim the memory used for the
	 *   serviceOrder obj
	 * @throws Exception
	 */
	public void tearDown() throws Exception {
		order1 = order2 = null;
	}

	@Test
	/**
	 * Test to check is vehicle retrieved
	 * 
	 */
	public void testGetVehicle() {
		assertEquals("Patterson, Jimmy Chevrolet Malibu", order1.getVehicle().toString());
		assertEquals("Williams, June Nissan Sentra", order2.getVehicle().toString());
		//fail("Not yet implemented");
	}

	@Test
	/**
	 * Test to check order number 
	 * 
	 */
	public void testGetOrderNum() {
		assertEquals(8, order1.getOrderNum(), .001);
		assertEquals(4, order2.getOrderNum(), .001);
		//fail("Not yet implemented");
	}

	@Test
	/**
	 * Test to check oil 
	 * 
	 */
	public void testGetOil() {
		assertEquals("no", order1.getOil());
		assertEquals("yes", order2.getOil());
		//fail("Not yet implemented");
	}

	@Test
	/**
	 * Test to check safety
	 * 
	 */
	public void testGetSafety() {
		assertEquals("yes", order1.getSafety());
		assertEquals("yes", order2.getSafety());
		//fail("Not yet implemented");
	}

	@Test
	/**
	 * Test to check emissions test 
	 * 
	 */
	public void testGetEmissions() {
		assertEquals("yes", order1.getEmissions());
		assertEquals("no", order2.getEmissions());
		//fail("Not yet implemented");
	}

	@Test
	/**
	 * Test to string representation
	 * 
	 */
	public void testToString() {
		assertEquals("\n" + "8 Patterson, Jimmy Chevrolet Malibu no yes yes" +"\n"
				, order1.toString());
		assertEquals("\n" + "4 Williams, June Nissan Sentra yes yes no" +"\n"
				, order2.toString());
		//fail("Not yet implemented");
	}

}
