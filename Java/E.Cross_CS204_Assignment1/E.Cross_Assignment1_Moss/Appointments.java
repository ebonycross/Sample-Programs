/**Assignment 1, Spring 2015: Appointments (Data Manager)
 * Implements AppointmentsInterface interface and its methods.As the data manager, 
 * computations and methods for Driver class are created.
 * Contains an Array list of Appointment type.
 * 
 * @author Ebony Cross
 * 
 */
import java.util.GregorianCalendar;
import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class Appointments implements AppointmentsInterface{
	private ArrayList<Appointment> appt;// arraylist of appointment objects
	private DailyAppointment daily; //reference variable to daily 
	private WeeklyAppointment weekly; //reference variable to weekly
	private OneTimeAppointment once; //reference variable to once
	private int numOfAppt = 0; //number of appointments added 


	public Appointments(){
		appt = new ArrayList<Appointment>();
	}//end of constructor

	/**
	 * An Add Appointment method that adds an Appointment to the collection.  All parameters are
	 * validated before using this method.
	 * @param type Appointment Type, O for One Time, W for Weekly, D for Daily
	 * @param date A GregorianCalendar Date representing the time of the Appointment
	 * @param description The description of the Appointment.
	 */
	public void addAppt(char type, GregorianCalendar date, String description){
		if(type == 'O'){
			once = new OneTimeAppointment(type, date, description);
			appt.add(once);
			numOfAppt++;
		}

		else if(type == 'W'){
			weekly = new WeeklyAppointment(type, date, description);
			appt.add(weekly);
			numOfAppt++;
		}

		else if(type == 'D'){
			daily = new DailyAppointment(type, date, description);
			appt.add(daily);
			numOfAppt++;
		}

		else{
			System.out.println("invalid data entered");
		}
	
	}


	/**
	 * A Display Appointments method that returns a string representation of all the appointments that
	 * occur on the date the user enters within the collection (ArrayList).  All parameters are 
	 * validated before using this method.
	 * @param day Day of the month
	 * @param month Month of the year
	 * @param year the year
	 * @return String representation of all appointments that occur on the requested date, in the following
	 * format:
	 * Appointments on (month)/(day)/(year)
	 * (time) (description)
	 * (time) (description)
	 * 
	 * Example:
	 * 
	 * Appointments on 1/3/2011
	 * 1:00 PM  Bday
	 * 11:59 PM  Party
	 * 
	 * If there are no appointments in the collection on this date, display the following:
	 * There are no appointments on (month)/(day)/(year)
	 */
	public String displayAppt(int day, int month, int year){
		String show = "Appointments on " + (month +1) + "/" + (day) + "/" + year + "\n\n";
		String noAppt = "There are no appointments on " + (month + 1) + "/" + day + "/" + year;

		int counter = 0;
		for(int index = 0; index < appt.size(); index++){
			if((appt.get(index).Occurs_On(day, month, year)) == true){
				show += appt.get(index).TimetoString() + " " 
						+ appt.get(index).getDescription() + "\n";
				counter++;
			}
		}
		if(counter == 0){
			show = noAppt;
		}
		
		return show;
	}


	/**
	 * A method to return the number of appointments that are within the collection
	 * @return the number of appointments that are within the collection
	 */
	public int GetAppointmentCount(){
		return numOfAppt;
	}

	/**
	 * A String Representation of all appointments within the collection
	 * @return the String representation of all appointments within the collection in the following format:
	 * (date) (time) (description)
	 * 
	 * Example
	 * January 3, 2011 1:00 PM Bday
	 * January 5, 2011 8:00 PM Gym
	 * December 31, 2010 11:59 PM Party
	 */
	public String toString(){
		String print ="";

		for(int index = 0; index < appt.size(); index++){
			print += appt.get(index).toString();
		}
		
		return print;

	}

}