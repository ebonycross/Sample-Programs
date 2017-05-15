import java.util.ArrayList;
import java.util.Collections;

/**
 * Assignment 5, Spring 2015
 * The Course class holds information about that implements DataElementInterface,  
 * 
 * @author E Cross
 */
public class Course implements DataElementInterface {
	
	private static final long serialVersionUID = 1L;
	String courseName, profName, description, day, time = "";
	int credits;
	ArrayList<Comparable<String>> cArray;
	String [] cNameArray;
	
	/**no-arg constructor*/
	public Course(){
		courseName = profName = description = day = time = null;
		credits = 0;
		cArray = new ArrayList<Comparable<String>>();
	}
	
	/**
	 * 
	 * @param course course name
	 * @param prof instructor's name
	 * @param desc description of course
	 * @param c number of credit
	 * @param d day of week the course occurs
	 * @param t course scheduled time
	 */
	public Course(String course, String prof,String desc,int c, String d,String t){
		courseName = course;
		profName = prof;
		description = desc;
		day = d;
		time = t;
		credits = c;
	}
	
	/**
	 * copy constructor
	 * @param otherCourse object of Course
	 */
	public Course(Course otherCourse){
		courseName = otherCourse.courseName;
		profName = otherCourse.profName;
		description = otherCourse.description;
		day = otherCourse.day;
		time = otherCourse.time;
		credits = otherCourse.credits;
	}

	
	/**
	 * Determines the key to use for comparing data element objects
	 * @return course name
	 */
    public String getKey(){
		return courseName;
    	
    }
    /**
     * 
     * @param courseName name of course
     */
	public void setKey(String courseName) {
		this.courseName = courseName;
	}
    
    /**
     * Determines if one DataElement is equal to another
     * @param otherElement DataElement to compare to
     * @return true if the two DataElements are equal, false if not
     */
    public boolean equals(Object otherElement){
    	if(this == otherElement){
    		return true;
    	}
    	if(otherElement == null){
    		return false;
    	}
    	if(getClass() != otherElement.getClass()){
    		return false;
    	}
    	
    	Course obj = (Course) otherElement;
    	
    	if (this.getKey() == null){
    		if(obj.getKey() != null){
    			return false;
    		}
    		else if(!this.getKey().equals(obj.getKey())){
    			return false;
    		}
    	}
    	
    	
		return true;
		}
    
    /**
     * Returns the data of the DataElement in an ArrayList of Strings
     * @return ArrayList of Strings in the following order:
     * 		[courseName, instructorName, description, numCredits, days, time]
     */
    public ArrayList<Comparable<String>> toArrayList(){
    	cArray.add(toString());
    	
		return cArray;
		
    }

    /**
     * Returns an array to populate one row of a table
     * @return Array of strings in the following order:
     * [0] - courseName
     * [1] - instructorName
     * [2] - description
     * [3] - numCredits
     * [4] - days
     * [5] - time
     */
    public String[] toTableRow(){
    	cNameArray = new String[6];
    	
    	cNameArray[0] = getCourseName();
    	cNameArray[1] = getProfName();
    	cNameArray[2] = getDescription();
    	cNameArray[3] = getCredits() +"";
    	cNameArray[4] = getDay();
    	cNameArray[5] = getTime();
    	
		return cNameArray;
    	}
    
    /**
     * Determines the key to use for comparing data element objects
     * @return course
     */
    public String getCourseName() {
		return courseName;
	}
    
    /**
     * retrieves instructor name of course
     * @return instructor name
     */
	public String getProfName() {
		return profName;
	}

	/**
	 * retrieves description of course
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * retrieves day of course
	 * @return day schedule
	 */
	public String getDay() {
		return day;
	}

	/**
	 * retrieves time of course
	 * @return time schedule
	 */
	public String getTime() {
		return time;
	}

	/**
	 * retrieves number of credits of course
	 * @return the amount of credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * sets professor's name
	 * @param profName instructor name
	 */
	public void setProfName(String profName) {
		this.profName = profName;
	}

	/**
	 * set course description
	 * @param description description of course 
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * set day of course
	 * @param day scheduled day
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * set time of course 
	 * @param time scheduled times
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * set credits of set
	 * @param credits credits of class
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}

	/**
     * String representation of the DataElement object
     * @return string 
     */
    public String toString(){
    	String print = "";
    	
    	print += getCourseName() + " " + getProfName() + " " + getDescription() + " " +getCredits() + " "
    			+ getDay() + " " + getTime() + " " + "\n";
		return print;
		}

	/**
	 * Compares this instance with a specified String object and indicates whether this instance precedes, follows,
	 *  or appears in the same position in the sort order as the specified string.
	 */
	public int compareTo(Keyable<DataElementInterface> o) {
		int result;
		
		result = this.getKey().compareTo(o.getKey());
		return result;
	}
}//end of program



