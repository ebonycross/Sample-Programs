

import java.io.File;
import java.util.ArrayList;

/**
 * Instances of the CollegeFootballPlayoffManager class are responsible for reading and displaying the votes of 
 * the new College Football Playoff committee for national ranking of NCAA I Football Teams.  It 
 * The  CollegeFootballPlayoffManager will manage an ArrayList of Team type of Team objects.  The manager is in charge 
 * of adding to the data structure, updating the data structure, retrieving information from the data structure and 
 * any other methods that involve the data structure as a whole.  This Data Manager will provide methods to read 
 * the input file (readVotes) and return an ArrayList of Team type of teams in order of their voted ranking (rankTeams).   
 * @author R Alexander
 *
 */
public interface CFPManagerInterface {

	/**
	 * The readVotes method opens a file and reads its content, creating instances of the Team class for each column
	 * of data.  The file contains the names of the teams and the votes of each committee member in ASCII format.  
	 * Each member votes on each team.
	 * @param inFile the file provided by the user.  The file's format is ASCII, with the first line being a comma-delimited
	 * string of 10 team names.  The subsequent 12 lines are comma-delimited strings of integers representing the ranking of 
	 * each of 12 committee members for the 10 teams, in the order listed in the first line.  Lower numbers represent a
	 * higher ranking
	 */
	public void readVotes(File inFile);

	/**
	 * The rankTeams method accesses existing votes for each team, sums the votes, and ranks the teams from lower to higher scores.
	 * The teams are placed in a new ArrayList of CollegeFootballTeam type by increasing total score.
	 * @return an ArrayList of CollegeFootballTeam type sorted with the lowest-voted team first, increasing to the highest-voted (last-ranked) team last.
	 */
	public ArrayList<CollegeFootballTeam> rankTeams();
	
	/**
	 * Get the ArrayList of Teams previously read in.
	 * @return the current ArrayList of CollegeFootballTeam type of teams read in, and possibly sorted by votes
	 */
	public ArrayList<CollegeFootballTeam> getTeamList();

}
