


import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CollegeFootballPlayoffManagerTest {
	private File newFile;
	private PrintWriter output;
	private CFPManagerInterface mgr;
	private CFPManagerInterface student;


	@Before
	public void setUp() throws Exception {
		newFile = new File("CFPTest");
		try {
			output = new PrintWriter(newFile);
			
			output.println("MissState,FloridaSt,Auburn,OleMiss,Oregon,Alabama,TCU,MichSt,KState,NotreDm");
			output.println("SEC,ACC,SEC,SEC,Pac-12,SEC,Big12,BigTen,Big12,Indep");
			output.println("4,1,9,10,7,12,13,11,5,6");
			output.println("1,2,10,4,5,14,7,8,16,6");
			output.println("1,2,6,8,5,14,7,4,10,3");
			output.println("1,4,2,3,5,8,7,6,10,16");
			output.println("2,4,9,3,5,8,12,14,10,16");
			output.println("1,4,2,6,5,8,12,9,16,10");
			output.println("1,9,3,2,5,14,12,4,7,10");
			output.println("1,2,3,8,4,5,12,14,7,6");
			output.println("4,1,3,8,2,5,12,14,7,13");
			output.println("1,2,8,4,6,5,7,14,13,9");
			output.println("1,2,6,4,5,14,7,12,13,8");
			output.println("1,2,10,4,5,14,7,12,13,8");

			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		student = new CollegeFootballPlayoffManager();
		mgr = new CollegeFootballPlayoffManager();
	}

	@After
	public void tearDown() throws Exception {
		mgr = null;
	}

	@Test
	public void testReadVotes() {
		File inFile = new File("CFPTest");
		mgr.readVotes(inFile);
		ArrayList<CollegeFootballTeam> list = mgr.getTeamList();
		assertEquals(list.get(1).getName(),"FloridaSt");
		assertEquals(list.get(4).getName(),"Oregon");
		assertTrue(list.get(2).getSumOfVotes()==71);
		assertTrue(list.get(5).getSumOfVotes()==121);
		if(list.get(1).getProfessional()==true) {
			fail(list.get(1).getName()+" should not be a professional team");
		}
	}

	@Test
	public void testRankTeams() {
		File inFile = new File("CFPTest");
		mgr.readVotes(inFile);
		ArrayList<CollegeFootballTeam> rankings = mgr.rankTeams();
		assertEquals(rankings.get(5).getName(),"NotreDm");
		assertEquals(rankings.get(8).getName(),"MichSt");
		assertEquals(rankings.get(2).getSumOfVotes(),59,.001);
		assertEquals(rankings.get(4).getSumOfVotes(),71,.001);
	}
	@Test
	public void testRankTeamsSTUDENT() {
		File inFile = new File("CFPTest");
		student.readVotes(inFile);
		ArrayList<CollegeFootballTeam> rankings = student.rankTeams();
		assertEquals(rankings.get(0).getName(),"MissState");
		assertEquals(rankings.get(2).getName(),"Oregon");
		assertEquals(rankings.get(8).getSumOfVotes(),122,.001);
		assertEquals(rankings.get(9).getSumOfVotes(),127,.001);
		
	}
	
	@Test
	public void testGetTeamList() {
		File inFile = new File("CFPTest");
		mgr.readVotes(inFile);
		ArrayList<CollegeFootballTeam> tmList = mgr.getTeamList();
		assertEquals(tmList.get(5).getName(),"Alabama");
		assertEquals(tmList.get(5).getConference(),"SEC");
		assertEquals(tmList.get(2).getSumOfVotes(),71,.001);
	}

}
