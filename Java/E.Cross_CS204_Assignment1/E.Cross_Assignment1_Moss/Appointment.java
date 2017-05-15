/**
 * Assignment 1, Spring 2015
 * The appointment class holds information about a type of date, 
 * GregorianCalendar object and description of appointment 
 * @author E Cross
 *
 */

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.util.Date;


public abstract class Appointment{
	Date now; //time variable
	DateFormat defaultDate; //format time variable 
	char type; //appt type
	GregorianCalendar date; //date object reference variable
	String description; //description of amount 
	
	/**no-arg construct*/
	public Appointment(){
		type = '0';
		description = "null";
		//date = new GregorianCalendar();
	}
	
	/**constructor
	 * 
	 * @param group date of occurrence for appointment
	 * @param d date of appointment
	 * @param describe explanation of appointment 
	 */
	public Appointment(char group, GregorianCalendar d, String describe){
		//date = new GregorianCalendar(year, month, day);
		type = group;
		date = d;
		description = describe;  
	}
	
	/**abstract method*/
	public abstract boolean Occurs_On(int year, int month, int day);

	/**
	 * get appt type
	 * @return type
	 */
	public char getType() {
		return type;
	}

	/**
	 * get date 
	 * @return Gregorian date 
	 */
	public GregorianCalendar getDate() {
		return date;
	}
	
	/**
	 * get appt description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * set type date 
	 * @param type
	 */
	public void setType(char type) {
		this.type = type;
	}

	/**
	 * set Gregorian Calendar date
	 * @param date
	 */
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	/**
	 * set description 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * String representation of the date, time and appt description
	 * @return string variable printAppt
	 */
	public String toString(){
		now = date.getTime();
		defaultDate = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);
		
		String dateTime = defaultDate.format(now);
		String printAppt = "";

		printAppt += dateTime + " " + getDescription() + "\n";

		return printAppt;
	}

	/**
	 * String representation of time in formatted short
	 * @return string variable printAppt
	 */
	public String TimetoString(){
		now = date.getTime();

		defaultDate = DateFormat.getTimeInstance(DateFormat.SHORT);
		String dateTime = defaultDate.format(now);
		String printAppt = "";

		printAppt += dateTime;

		return printAppt;
	}
}
