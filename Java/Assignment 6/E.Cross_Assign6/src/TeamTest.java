import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TeamTest {
	Team team1, team2;

	@Before
	/** This method is run before each individual test
	 *   Creates an object of Team 
	 */
	public void setUp() throws Exception {
		team1 = new Team("MSU", "football");
		team2 = new Team("TSU", "soccer");
	}

	@After
	/** This method is run after each individual test
	 *   It sets the team reference to null so the garbage
	 *   collector can reclaim the memory used for the
	 *   Team object
	 * @throws Exception
	 */
	public void tearDown() throws Exception {
		team1 = team2 = null;
	}
	
	

	@Test
	/**
	 * Test to check name of team
	 * 
	 */
	public void testGetName() {
		assertEquals("MSU",team1.getName());
		assertEquals("TSU",team2.getName());
		//fail("Not yet implemented");
	}

	@Test
	/**
	 * Test to
	 * 1. check name of team
	 * 2. check method for changing name of team
	 * 
	 */
	public void testSetName() {
		assertEquals("MSU",team1.getName());
		team1.setName("CW");
		assertEquals("CW",team1.getName());
		assertEquals("TSU",team2.getName());
		team2.setName("NYC");
		assertEquals("NYC",team2.getName());
		//fail("Not yet implemented");
	}

	@Test
	/**
	 * Test to check name of sport
	 * 
	 */
	public void testGetSport() {
		assertEquals("football",team1.getSport());
		assertEquals("soccer",team2.getSport());
		//fail("Not yet implemented");
	}

	@Test
	/**
	 * Test to
	 * 1. check name of sport
	 * 2. check method for changing name of sport
	 * 
	 */
	public void testSetSport() {
		assertEquals("football",team1.getSport());
		team1.setSport("track");
		assertEquals("track",team1.getSport());
		assertEquals("soccer",team2.getSport());
		team2.setSport("tennis");
		assertEquals("tennis",team2.getSport());
		//fail("Not yet implemented");
	}
		

	@Test
	/**
	 * Test to check to see if sport is professional
	 * 
	 */
	public void testGetProfessional() {
		assertTrue(team1.getProfessional()==true);
		assertTrue(team2.getProfessional()==true);
		assertFalse(team1.getProfessional()==false);
		assertFalse(team2.getProfessional()==false);
		//fail("Not yet implemented");
	}

	@Test
	/**
	 * Test to
	 * 1. check to see if sport is professional
	 * 2. check method for changing status of profession
	 * 
	 */
	public void testSetProfessional() {
		assertTrue(team1.getProfessional()==true);
		team1.setProfessional(false);
		assertTrue(team1.getProfessional()==false);
		assertTrue(team2.getProfessional()==true);
		team2.setProfessional(false);
		assertTrue(team2.getProfessional()==false);
		
		//fail("Not yet implemented");
	}

}
