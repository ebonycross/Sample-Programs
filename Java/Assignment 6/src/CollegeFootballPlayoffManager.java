/**Assignment 6, Fall 2014: CollegeFootballPlayoffManager (Data Manager)
 * Implements CFPManagerInterface interface and its methods.As the data manager, 
 * computations and methods for Driver class are created.
 * Contains an Array list of CollegeFootballTeam type.
 * 
 * @author Ebony Cross
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;


public class CollegeFootballPlayoffManager extends java.lang.Object implements CFPManagerInterface {
	private ArrayList<CollegeFootballTeam> teams;
	private String[][] teamNames = new String[2][10]; //string holder for team names and conferences
	private int [][] teamVotes = new int[12][10]; //int array holder for team votes
	private String[] names; //string holder for team names
	private String[] v; //string holder for string rep of votes
	private int[] voteOfThisTeam=new int[12]; //int array holder of votes for this team
	private String c, n, line; //string variables for conference, team name and scanner

	
	/**no-arg constructor*/
	CollegeFootballPlayoffManager(){
		teams = new ArrayList<CollegeFootballTeam>();
	}

	/**opens a file and reads its contents, creating instances of the team class for each column
	 * of data. The file contains the names of the teams and the votes of each committee
	 * member.
	 * @param inFile file provided by user. Contains first line of team names, second name of 
	 * conference names and remaining lines of votes from each committee member
	 * 
	 */
	public void readVotes(File inFile){
		try{
			//read in file
			Scanner input = new Scanner (inFile);
			
			//retrieve team names and conferences
			for(int row = 0; row < 2; row++){
				line= input.nextLine();
				names = line.split(",");
				for(int col = 0; col < 10; col++){
					teamNames[row][col]=names[col];
				//	System.out.println(teamNames[row][col] + " ");
				}
			}
			
			//retrieve votes each each team from every committee member
			for(int row = 2 ;row < 14; row++){
				line= input.nextLine();
				v = line.split(",");
				for(int col = 0; col < 10; col++){
					teamVotes[row-2][col]= Integer.parseInt(v[col]) ;
					//System.out.print(teamVotes[row-2][col] + "");
				}	
				//System.out.println();
				
			}
			
			//retrieve votes for college football team
			for(int col = 0; col < 10; col++){
				
				voteOfThisTeam = new int[12];
				for(int row = 0; row < 12; row++){
					voteOfThisTeam[row] = teamVotes[row][col];
				}
				
				n = teamNames[0][col]; //string array holder for line of team names
				c = teamNames[1][col]; //string array holder for line of conference names 
				
				//create instance variable
				CollegeFootballTeam CFBTeam = new CollegeFootballTeam(n, c, voteOfThisTeam);
				CFBTeam.setProfessional(false);
				teams.add(CFBTeam);//add instance variable to ArrayList
			}
		//catch for except thrown
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
	}


	/**sorts the list of teams into ascending order of cumulative votes, lower scores 
	 * means higher ranking. Compute by utilizing selectionSort method
	 * 
	 * @param list ArrayList of CollegeFootballTeam type
	 * @return sorted ArrayList of CollegeFootballTeam type
	 */
	public ArrayList<CollegeFootballTeam> selectionSort(ArrayList<CollegeFootballTeam> list){
		int startScan, index, minIndex, minValue;
		CollegeFootballTeam tempList;
		for(startScan = 0; startScan < list.size() - 1; startScan++){
			
			//find the smallest remaining integer
			minIndex = startScan;
			minValue = list.get(startScan).getSumOfVotes();
			
			for(index = startScan + 1; index < list.size(); index++){
				if(list.get(index).getSumOfVotes() < minValue){
					minValue = list.get(index).getSumOfVotes();
					minIndex = index;
				}
			}
			//swap smallest into correct position
			tempList = list.get(startScan);
			list.set(startScan, list.get(minIndex));
			list.set(minIndex, tempList);
			
		}
		return list;
		
	}



	/**
	 * The rankTeams method accesses existing votes for each team, sums the votes, and ranks the teams from lower to higher scores.
	 * The teams are placed in a new ArrayList of CollegeFootballTeam type by increasing total score.
	 * 
	 * @return an ArrayList of CollegeFootballTeam type sorted with the lowest-voted team first, increasing to the highest-voted (last-ranked) team last.
	 */
	public ArrayList<CollegeFootballTeam> rankTeams(){
		return selectionSort(teams);
	}
	
	/**
	 * Get the ArrayList of Teams previously read in.
	 * @return the current ArrayList of CollegeFootballTeam type of teams read in, and possibly sorted by votes
	 */
	public ArrayList<CollegeFootballTeam> getTeamList(){
		return teams;
		}
	
	/**retrieves each Team from the ArrayList and puts its team name in an array of Strings
	 * 
	 * @return an array of String with each team name, size of array is 10
	 */
	public String[] getTeamNames(){
		String[] name = new String[10];
		for(int i = 0; i < 10; i++){
			name[i] = teams.get(i).getName();
		}
		return name;
	}
	
	/**creates a string with the name of the team name followed by the sum of votes
	 * for this team.
	 * @return the String representation of the team name and corresponding sum of votes
	 */
	public String toString(){
		String t = "";
		for(int i = 0; i < teams.size(); i++){
			t += teams.get(i).ptoString() + "\n";
		}
		return t;
	}//end of toString method
	

}



