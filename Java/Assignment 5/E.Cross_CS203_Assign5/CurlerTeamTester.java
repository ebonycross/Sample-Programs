/**Assignment 5: JUnit Test
 * implement and test methods and variables used in Assignment 5
 */

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a JUnit test program to test the Data Manager CurlerTeam.
 * The following classes must be defined and implemented:
 * CurlerPosition - enumeration
 * CurlerPlayer - data element
 * CurlerTeam - data manager
 * @author Professor Myers
 *
 */
public class CurlerTeamTester {

	/**  A CurlerTeam object reference used for testing */
	CurlerTeam team, team1;
	
	@Before
	/** This method is run before each individual test
	 *   Creates an object of CurlerTeam and adds three
	 *   CurlerPlayers to the CurlerTeam
	 */
	public void setUp() throws Exception {
		team = new CurlerTeam("Sweden");
		team.addPlayer("John", "Smith","thrower");
		team.addPlayer("Bob", "Brown", "sweeper");
		team.addPlayer("Harold", "Jones", "skip");
		
		//STUDENT use this team to do the STUDENT tests.  
		//Add some players to the team in setUp
		team1 = new CurlerTeam("Germany");
		team1.addPlayer("Johnny", "Smith","skip");
		team1.addPlayer("Bobby", "Brown", "sweeper");
		team1.addPlayer("Harry", "Jones", "thrower");
	}

	@After
	/** This method is run after each individual test
	 *   It sets the team reference to null so the garbage
	 *   collector can reclaim the memory used for the
	 *   CurlerTeam object
	 * @throws Exception
	 */
	public void tearDown() throws Exception {
		team = team1 = null;
	}

	@Test
	/**
	 * Test to 
	 * 1.  check if the number of teams is originally 1
	 * 2.  Add another team, and check if number of teams is 2
	 * 
	 */
	public void testGetNumPlayers() {
		assertEquals(2,CurlerTeam.getNumTeams());
		CurlerTeam newTeam = new CurlerTeam("France");
		assertEquals(3, CurlerTeam.getNumTeams());
		CurlerTeam myTeam = new CurlerTeam("Nigeria");
		assertEquals(4, CurlerTeam.getNumTeams());
	}

	@Test
	/**
	 * Test to
	 * 1.  add a new player as a skip
	 *     check if recognizes there is already a skip on the team
	 * 2.  add a new player as a thrower
	 *     check if recognizes there is already a skip on the team
	 * 3.  add new player as a sweeper - player is added
	 */
	public void testAddPlayer() {
		String result;
		result = team.addPlayer("Benny", "Jets", "skip");
		assertEquals("There is already a skip on this team\nPlayer not added", result);
		result = team.addPlayer("Benny","Jets", "thrower");
		assertEquals("There is already a thrower on this team\nPlayer not added", result);
		result = team.addPlayer("Benny","Jets", "sweeper");
		assertEquals(null, result);
		assertEquals(4,team.getNumPlayers());
	}

	@Test
	/**
	 * Use team1
	 * Test to
	 * 1.  add a new player as a skip
	 * 2.  add a new player as a thrower
	 * 3.  add new player as a sweeper
	 * Depending on what players you added in the setUp,
	 * some of these test will return error messages (See
	 * test above for an example)
	 */
	public void testAddPlayerSTUDENT() {
		String result;
		result = team1.addPlayer("Chris", "Pine", "skip");
		assertEquals("There is already a skip on this team\nPlayer not added", result);
		result = team1.addPlayer("Chris", "Pine", "thrower");
		assertEquals("There is already a thrower on this team\nPlayer not added", result);
		result = team1.addPlayer("Chris", "Pine", "sweeper");
		assertEquals(null, result);
		assertEquals(4,team1.getNumPlayers());
		//fail("Test has not been implemented");
	}
	
	@Test
	/**
	 * Test to:
	 * 1.  Check if the country name is on the first line
	 * 2.  Check if Harold is on the forth line
	 */
	public void testPrintTeam() {
		String result = team.printTeam();
		Scanner team = new Scanner(result);
		assertEquals("Sweden",team.nextLine()); //Sweden
		//extract three team players
		team.nextLine();  //John Smith     Position guard
		team.nextLine();  //Bob Brown     Position forward
		assertEquals("Harold",team.next()); //Harold
	}

	@Test
	/**
	 * Use team1
	 * Test to:
	 * 1.  Check if the country name is on the first line
	 * 2.  Check for the players names based on the
	 * players you added in the setUp (See test above
	 * for an example)
	 */
	public void testPrintTeamSTUDENT() {
		String result = team1.printTeam();
		Scanner team1 = new Scanner(result);
		assertEquals("Germany", team1.nextLine()); //Germany
		//obtain the 3 team players
		team1.nextLine(); //Johnny Smith   Position skip
		team1.nextLine(); //Bobby Brown   Position sweeper
		assertEquals("Harry", team1.next()); //Harry
		//fail("Test has not been implemented");
	}
}
