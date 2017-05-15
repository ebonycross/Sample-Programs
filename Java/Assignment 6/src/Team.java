
/**
 * The Team class holds information about a team, i.e., name, 
 * what sport it plays, and whether or not it is professional
 * @author R Alexander
 *
 */
public class Team {
	private String name;
	private String sport;
	private boolean professional;
	
	Team() {
		setName(null);
		setSport(null);
		setProfessional(true);
	}
	
	/**
	 * Team constructor that takes the team name and the array of integer votes as arguments
	 * @param name the String college team name
	 * @param sport of the team
	 */
	Team(String name, String sport) {
		setName(name);
		setSport(sport);
		setProfessional(true);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}
	public boolean getProfessional() {
		return professional;
	}

	public void setProfessional(boolean professional) {
		this.professional = professional;
	}
}
