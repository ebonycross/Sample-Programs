import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author Justh
 *
 */
public class LeagueTest
{
	League leagueTest1, leagueTest2, studentLeagueTest;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		int [][] points1 = {{1, 1, 3, 3, 0, 0, 1, 1, 0, 3},
							{1, 1, 1, 0, 0, 3, 3, 3, 3, 0},
							{1, 0, 0, 0, 1, 1, 3, 0, 0, 3},
							{1, 3, 1, 3, 3, 3, 0, 0, 1, 1},
							{3, 0, 1, 3, 1, 1, 1, 1, 1, 1},
							{0, 3, 1, 0, 3, 0, 0, 3, 3, 0}};
		leagueTest1 = new League();
		leagueTest2 = new League(points1);
		
		// STUDENT - use the studentLeagueTest for the STUDENT test
		int [][] studentPoints = {	{3, 1, 3, 3, 0, 0, 1, 1, 1, 3},
									{0, 0, 1, 0, 3, 3, 0, 0, 3, 0},
									{3, 1, 0, 0, 1, 1, 3, 3, 0, 3},
									{1, 3, 1, 3, 0, 1, 0, 0, 0, 1},
									{1, 0, 3, 3, 1, 3, 3, 1, 1, 1},
									{0, 3, 0, 0, 3, 0, 1, 3, 3, 0}};
		studentLeagueTest = new League(studentPoints);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception 
	{
		leagueTest1 = null;
		leagueTest2 = null;
		studentLeagueTest = null;
	}

	@Test
	public void testChampionshipWinner()
	{
		//assertEquals("Arabian GobiOutback Patagonian Sahara Sonoran ", leagueTest1.championshipWinner());
		assertEquals("Patagonian ", leagueTest2.championshipWinner());
	}
	
	@Test
	public void testChampionshipWinnerSTUDENT()
	{
		//Determine the ChampionshipWinner for the studentLeagueTest
		assertEquals("Sahara ", studentLeagueTest.championshipWinner());
		//fail("Not yet implemented");
	}
	
	@Test
	public void testEarthCupWinner()
	{
		//assertEquals("Arabian Gobi Outback Patagonian Sahara Sonoran ", leagueTest1.earthCupWinner());
		assertEquals("Gobi Patagonian Sonoran ", leagueTest2.earthCupWinner());
	}
	
	@Test
	
	public void testEarthCupWinnerSTUDENT()
	{
		//Determine the EarthCupWinner for the studentLeagueTest
		assertEquals("Arabian Outback Sahara Sonoran ", studentLeagueTest.earthCupWinner());
		//fail("Not yet implemented");
	}
	
	@Test
	/** Test the total number of points for a team.*/
	public void testGetTotal()
	{
		assertEquals(13, leagueTest2.getTotal(0));
		assertEquals(15, leagueTest2.getTotal(1));
		assertEquals(9, leagueTest2.getTotal(2));
		assertEquals(16, leagueTest2.getTotal(3));
		assertEquals(13, leagueTest2.getTotal(4));
		assertEquals(13, leagueTest2.getTotal(5));
	}
	
	@Test
	/** Test the total number of points for a team.*/
	public void testGetTotalSTUDENT()
	{
		//Determine the total points for the studentLeagueTest 
		assertEquals(16, studentLeagueTest.getTotal(0));
		assertEquals(10, studentLeagueTest.getTotal(1));
		assertEquals(15, studentLeagueTest.getTotal(2));
		assertEquals(10, studentLeagueTest.getTotal(3));
		assertEquals(17, studentLeagueTest.getTotal(4));
		assertEquals(13, studentLeagueTest.getTotal(5));
		//fail("Not yet implemented");
	}
	
	@Test
	/** Test the total number of wins for a team. */
	public void testGetWin()
	{
		assertEquals(3, leagueTest2.getWin(0));
		assertEquals(4, leagueTest2.getWin(1));
		assertEquals(2, leagueTest2.getWin(2));
		assertEquals(4, leagueTest2.getWin(3));
		assertEquals(2, leagueTest2.getWin(4));
		assertEquals(4, leagueTest2.getWin(5));
	}
	@Test
	/** Test the total number of ties for a team. */
	public void testGetTie()
	{
		
		assertEquals(4, leagueTest2.getTie(0));
		assertEquals(3, leagueTest2.getTie(1));
		assertEquals(3, leagueTest2.getTie(2));
		assertEquals(4, leagueTest2.getTie(3));
		assertEquals(7, leagueTest2.getTie(4));
		assertEquals(1, leagueTest2.getTie(5));
	}
	
	@Test
	/** Test the total number of losses for a team. */
	public void testGetLoss()
	{
		assertEquals(3, leagueTest2.getLoss(0));
		assertEquals(3, leagueTest2.getLoss(1));
		assertEquals(5, leagueTest2.getLoss(2));
		assertEquals(2, leagueTest2.getLoss(3));
		assertEquals(1, leagueTest2.getLoss(4));
		assertEquals(5, leagueTest2.getLoss(5));
	}
	
	@Test
	/** Test the record of wins-losses-ties with a "-" between each integer with no blanks*/
	public void testGetRecord()
	{
		assertEquals("3-3-4", leagueTest2.getRecord(0));
		assertEquals("4-3-3", leagueTest2.getRecord(1));
		assertEquals("2-5-3", leagueTest2.getRecord(2));
		assertEquals("4-2-4", leagueTest2.getRecord(3));
		assertEquals("2-1-7", leagueTest2.getRecord(4));
		assertEquals("4-5-1", leagueTest2.getRecord(5));
	}
	
	@Test
	
	/** Test the record of wins-losses-ties with a "-" between each integer with no blanks*/
	public void testGetRecordSTUDENT()
	{
		//Determine the wins-losses-times for the studentLeagueTest 
		assertEquals("4-2-4", studentLeagueTest.getRecord(0));
		assertEquals("3-6-1", studentLeagueTest.getRecord(1));
		assertEquals("4-3-3", studentLeagueTest.getRecord(2));
		assertEquals("2-4-4", studentLeagueTest.getRecord(3));
		assertEquals("4-1-5", studentLeagueTest.getRecord(4));
		assertEquals("4-5-1", studentLeagueTest.getRecord(5));
		//fail("Not yet implemented");
	}
	
	@Test
	/** Test the name of a team by indicating its position on the team list. */
	public void testGetNames()
	{
		assertEquals("Arabian", leagueTest2.getNames(0));
		assertEquals("Arabian", studentLeagueTest.getNames(0));

	}
}