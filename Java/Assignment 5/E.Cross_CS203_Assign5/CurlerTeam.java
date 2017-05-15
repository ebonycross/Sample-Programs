/**Assignment 5, Fall 2014: CurlerTeam Class (Worker Class)
 * Implement CurlerPlayer and its methods to design CurlerTeam methods and 
 * objects. As data manager, computations and methods for Driver class are created.
 * Contains an Array list of CurlerPlayer type, keeps track of number of teams and players
 *  and error messages as indicated. 
 * 
 * @author Ebony Cross
 * 
 */
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class CurlerTeam {
	private ArrayList<CurlerPlayer> players; //create array list reference variable
	private int numPlayers; //current number of players
	private int numSkip; //current number of skip players
	private int numSweepers; //number of sweep players currently on the team
	private int numThrowers;//current number of throwers on the team
	private static int numTeams; //number of teams created
	private String teamName; //team country

	/**
	 * no arg constructor
	 */
	public CurlerTeam(){
		numPlayers = numSkip = numSweepers =numThrowers = 0;
		teamName = "default";
		players = new ArrayList<CurlerPlayer>();
	}//end of constructor 

	/**constructor intializes numPlayers, numSkip, numSweepers,
	 * numThrowers, teamName, and players array. numTeams is incremented.
	 * 
	 * @param cn the team name ie)country is received 
	 */
	public CurlerTeam(String cn){
		numPlayers = numSkip = numSweepers =numThrowers = 0;
		teamName = cn;
		players = new ArrayList<CurlerPlayer>(5);
		numTeams++;
	}//end of constructor

	
	/**retrieves the number of teams currently present
	 * 
	 * @return the total number of teams currently
	 */
	public static int getNumTeams(){
		return numTeams;//increment in gui class everytime object is created 
	}//end of method 

	
	/**retrieves the number of players currently on the team
	 * 
	 * @return number of players current on this team
	 */
	public int getNumPlayers(){
		return numPlayers;
	}//end of method 

	/**adds a player to the team by adding to the Array List
	 * 
	 * @param fName first name of the player
	 * @param lName last name of the player
	 * @param pos the team position of the player 
	 * 
	 * @return null if sccessfully added. Otherwise a String is returned that
	 * describes the error and reason for not adding the player to the team, thus
	 * not adding to the Array List called players
	 */
	public String addPlayer(String fName, String lName, String pos){
		if(numPlayers < 4)
		{
			if(pos.equals("thrower")){
				CurlerPosition position = CurlerPosition.THROWER;
				if(numThrowers <1)
				{
					CurlerPlayer member = new CurlerPlayer(fName, lName, position);
					players.add(member);
					numPlayers++;
					numThrowers++;
					return null;
				}
				else
				{
					return "There is already a thrower on this team\nPlayer not added"; 
				}
			}
			else if(pos.equals("skip")){
				CurlerPosition position = CurlerPosition.SKIP;
				if(numSkip < 1){
					CurlerPlayer member = new CurlerPlayer(fName, lName, position);
					players.add(member);
					numPlayers++;
					numSkip++; 
					return null;
				}
				else
				{
					return "There is already a skip on this team\nPlayer not added";
				}
			}
			else if(pos.equals("sweeper")){
				CurlerPosition position = CurlerPosition.SWEEPER;
				if(numSweepers < 2){
					CurlerPlayer member = new CurlerPlayer(fName, lName, position);
					players.add(member);
					numPlayers++;
					numSweepers++; 
					return null;
				}
				else
				{
					return "There is already 2 sweepers on this team\nPlayer not added";
				}
			}
			return "Invalid data enter";
		}
		return "The are already 4 members on this team\nPlayer not added";
	}//end of addPlayer method 

	
	/**creates a string with the name of the team followed by the first and 
	 * last name and position of each of the players on the team. The first and 
	 * last name are separated by one space and then there are 3 spaces between 
	 * the last name and Position.
	 * 
	 * @return the String name of the country followed by the team members 
	 */
	public String printTeam(){
		String country = teamName;
		for(int i = 0; i < players.size(); i++){
			country += "\n" + players.get(i).ptoString() ;
		}
		return country;
	}//end of printTeam method
	
	/****creates a string with the name of the team followed by the first and 
	 * last name and position of each of the players on the team. The first and 
	 * last name are separated by one space and then there are 3 spaces between 
	 * the last name and Position. Method follows same format as printTeam method.
	 *
	 * 
	 * @return the String name of the country followed by the team members 
	 */
	public String toString(){
		String country = teamName;
		for(int i = 0; i < players.size(); i++){
			country += "\n" + players.get(i).ptoString() + "\n";
		}
		return country;
	}//end of toString method
	
	
} //end program


