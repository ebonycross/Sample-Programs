/**Assignment 6, Fall 2014: CollegeFootballTeam
 * Implement Team and its methods to design CollegeFootballTeam methods and 
 * objects. As data element, methods are inherited from Team class to accommodate
 * any team, regardless of profession while implementing instance variables of 
 * CollegeFootballTeam
 * 
 * 
 * @author Ebony Cross
 * 
 */
public class CollegeFootballTeam extends Team{
	private String conference;
	private int votes[] = new int[12];
	private int sumOfVotes;
	private static String football ="football";
	
	/**no-arg contructor*/
	public CollegeFootballTeam(){
		super();
		conference = null;
		sumOfVotes = 0;
	}//end of constructor
	
	/**constructor intializes naame, conference, and an array of points
	 * 
	 * @param nam name of team
	 * @param conf name of conference
	 * @param pts is array of votes for team
	 */
	public CollegeFootballTeam(String nam, String conf, int[] pts){
		super(nam, football);
		votes = pts;
		conference = conf;
		sumOfVotes =0;
	}//end of constructor
	
	/**retrieves conference name
	 * @return conference name for this team
	 */
	public String getConference() {
		return conference;
	}//end of method
	
	/**retrieves name of the college football team
	 * @return return name of college football team
	 */
	public String getName(){
		return super.getName();
	}//end of method
	
	/**calculates summation of all the votes accumulated from votes array
	 * @return sum of votes for team
	 */
	public int getSumOfVotes() {
		sumOfVotes = 0;
		 for(int row = 0; row < votes.length; row++){
				  sumOfVotes+= votes[row];	  
		 }
		return sumOfVotes;
	}//end of method
	
	/**returns the votes for each committee member
	 * @return votes for each committee member
	 */
	public int[] getVotes() {
		return votes;
	}//end of method
	
	/**set the name of the college Football Team*
	 * 
	 * @param name name of team
	 */
	public void setName(String name){
		super.setName(name);
	}//end of method

	/**set the conference for this team
	 * 
	 * @param conference name of conference
	 */
	public void setConference(String conference) {
		this.conference = conference;
	}//end of method
	
	/**allows the user to set the number of votes*/
	public void setSumOfVotes(){
		getSumOfVotes();
	}//end of method
	
	/**calculates the summation of votes and stores it inside of instance variable
	 * called sum of votes.
	 * 
	 * @param sumOfVotes summation of votes for college football team
	 */
	public void setSumOfVotes(int sumOfVotes) {
		this.sumOfVotes = sumOfVotes;
	}//end of method
	
	/**receives an array of votes from the committee
	 *  members and sets the votes for this team
	 * @param votes an array of integers to represent votes
	 */
	public void setVotes(int[] votes) {
		this.votes = votes;
	}//end of method
	
	/**creates a string with the name of the team name followed by the sum of votes
	 * for this team.
	 * @return the String representation of the team name and corresponding sum of votes
	 */
	public String ptoString(){
		String printTeam = "";
		
		printTeam += getName() + " " + getSumOfVotes();
		return printTeam;
	}//end of toString method
	

	
	
	

}
