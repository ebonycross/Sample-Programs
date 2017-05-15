import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.util.Date;

/**
 * Assignment 1, Spring 2015: 
 * The WeeklyAppointment class holds information about a 
 * type of date,GregorianCalendar object and description of appointment 
 *  and computes whether given date is a weekly occurrence for that specific date
 * @author E Cross
 *
 */

public class WeeklyAppointment extends Appointment{
	Date now; //time variable
	DateFormat defaultDate; //format time variable 
	char type; //appt type
	GregorianCalendar date; //date object reference variable
	String description; //description of amount 
	int day, month, year; //date of the month, month and year of appt


	/**no-arg construct*/
	public WeeklyAppointment(){
		super();
		type = '0';
		description = "null";
	}

	/**constructor
	 * 
	 * @param group date of occurrence for appointment
	 * @param d date of appointment
	 * @param describe explanation of appointment 
	 */
	public WeeklyAppointment(char group, GregorianCalendar d, String describe){
		super(group, d, describe);
		type = group;
		date = d;
		description = describe; 
	}

	/**
	 * compute the whether give appt date is a weekly date
	 * @param day day of the month of appt
	 * @param month month of appt 
	 * @param year year of appt
	 * @return success returns true if appt is weekly occurrence
	 */
	public boolean Occurs_On(int day, int month, int year){
		boolean success = false;

		//user's date 
		GregorianCalendar newAppointment = new GregorianCalendar(year,month, day);

		//date of appt
		GregorianCalendar today = new GregorianCalendar(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE));

		if(newAppointment.after(today)&& ((today.get(Calendar.DAY_OF_WEEK) == (newAppointment.get(Calendar.DAY_OF_WEEK))))){
			success = true;
		}

		else if((today.before(newAppointment)) && 
				(today.get(Calendar.DAY_OF_WEEK) == (newAppointment.get(Calendar.DAY_OF_WEEK)))){
			success = true;
		}

		else if ((newAppointment.get(Calendar.YEAR)) == today.get(Calendar.YEAR) && 
				(newAppointment.get(Calendar.MONTH)) == today.get(Calendar.MONTH) && 
				(newAppointment.get(Calendar.DATE)) == today.get(Calendar.DATE)){
			success = true;
		}

		else{
			//System.out.println("This does not occur weekly on this date.");
			success = false;
		}

		return success;
	}


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
	 * retrieves day of month
	 * @return day 
	 */
	public int getDay() {
		return day;
	}

	/**
	 * retrieves month
	 * @return month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * retrieves year of appt 
	 * @return year 
	 */
	public int getYear() {
		return year;
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
	 * set day of month of appt
	 * @param day
	 */
	public void setDay(int day) {
		this.day = day;
	}
	
	/**
	 * set month of appt 
	 * @param month
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * set year of appt
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * String representation of the date, time and appt description
	 * @return string variable printAppt
	 */
	public String toString(){
		GregorianCalendar newDate = getDate();
		now = newDate.getTime();
		defaultDate = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);
		
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
		GregorianCalendar newDate = getDate();
		now = newDate.getTime();
		defaultDate = DateFormat.getTimeInstance(DateFormat.SHORT);
		String dateTime = defaultDate.format(now);
		String printAppt = "";

		printAppt += dateTime;

		return printAppt;
	}
}//end of class


