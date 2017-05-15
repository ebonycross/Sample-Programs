import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CollegeFootballTeamTest {
	CollegeFootballTeam team1, team2;
	int[] pts1 = {1, 1, 3, 3, 0, 0, 1, 1, 0, 3};
	int[] pts2 = {1, 1, 3, 3, 0, 0, 1, 1, 0, 0};

	@Before
	public void setUp() throws Exception {
		team1 = new CollegeFootballTeam("MSU", "ACC", pts1);
		team2 = new CollegeFootballTeam("TSU", "SEC", pts2);
	}

	@After
	public void tearDown() throws Exception {
		team1 = team2 = null;
	}

	@Test
	public void testGetName() {
	assertEquals("MSU",team1.getName());
	assertEquals("TSU",team2.getName());
		//fail("Not yet implemented");
	}

	@Test
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
	public void testGetConference() {
		assertEquals("ACC",team1.getConference());
		assertEquals("SEC",team2.getConference());
		//fail("Not yet implemented");
	}

	@Test
	public void testGetSumOfVotes() {
		assertEquals(13,team1.getSumOfVotes());
		assertEquals(10,team2.getSumOfVotes());
		//fail("Not yet implemented");
	}

	@Test
	public void testGetVotes() {
		assertEquals(pts1,team1.getVotes());
		assertEquals(pts2,team2.getVotes());
	}

	@Test
	public void testSetConference() {
		assertEquals("ACC",team1.getConference());
		team1.setConference("Big12");
		assertEquals("Big12",team1.getConference());
		team2.setConference("Indep");
		assertEquals("Indep",team2.getConference());
		//fail("Not yet implemented");
	}

	@Test
	public void testSetSumOfVotes() {
		team1.setSumOfVotes();
		assertEquals(13,team1.getSumOfVotes());
		team2.setSumOfVotes();
		assertEquals(10,team2.getSumOfVotes());
		//fail("Not yet implemented");
	}

	@Test
	public void testSetSumOfVotesInt() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetVotes() {
		//fail("Not yet implemented");
	}

	@Test
	public void testPtoString() {
		assertEquals("MSU 13",team1.ptoString());
		assertEquals("TSU 10",team2.ptoString());
		//fail("Not yet implemented");
	}

}
