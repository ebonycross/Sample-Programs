/**Assignment 5, Fall 2014: CurlerPlayer Class (Worker Class)
 * Implement and compute the curler player's first name, last name, and position 
 * Computations executed using getter and setter methods as well
 * as data from the enumerated class called Curler Position. 
 * 
 * @author Ebony Cross
 * 
 */
public class CurlerPlayer {
	//declare variables for first name, last name and team position
	String firstName, lastName;
	private CurlerPosition position;
	
	/**default constructor*/
	public CurlerPlayer(){
		firstName = "default";
		lastName = "default";
		position = position.SKIP;
	}
	
	/**constructor initializes firstName, lastName and position
	 * 
	 * @param fNam first name
	 * @param lName last name
	 * @param pos position on the current team
	 */
	public CurlerPlayer(String fNam, String lName, CurlerPosition pos){
		firstName = fNam;
		lastName = lName;
		position = pos;
	}

	/**retrieve the first name of the team member
	 * 
	 * @return first name of current player 
	 */
	public String getFirstName() {
		return firstName;
	}

	/**retrieve the last name of the team member
	 * 
	 * @return last name of current player 
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**retrieve the position value of the team member
	 * 
	 * @return  position value current player 
	 */
	public CurlerPosition getPosition() {
		return position;
	}

	/**set the first name of the team member
	 * 
	 * @param firstName sends in a string to represent first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**set the last name of the team member
	 * @param lastName sends in a string to set a representative for last name
	 * 
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**set the position value of the team member
	 * @param position sends in value of position to set value for current player
	 */
	public void setPosition(CurlerPosition position) {
		this.position = position;
	}
	
	/**create a string representation of the player's first name, last
	 * name and position on the current team
	 * 
	 * @return string that represents the curler player's first name,
	 * last name and positon 
	 */
	public String ptoString(){
		String curlerMem = "";
		String pos = "" + getPosition();
		
		curlerMem += getFirstName() + " " + getLastName() + "   " +
		"Position: " + pos.toLowerCase();
		return curlerMem;
	}

	
	}

	



