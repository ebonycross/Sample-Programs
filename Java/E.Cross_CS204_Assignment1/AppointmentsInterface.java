import java.util.GregorianCalendar;

/**
	 * An interface class to standardize the Appointments class.
	 * The Appointments class holds a collection (ArrayList) of Appointment objects as well as the ability
	 * to add to the collection and display all appointments within the collection that occur on a specific date.
	 * @author Professor Myers
	 */
public interface AppointmentsInterface {
		
		/**
		 * An Add Appointment method that adds an Appointment to the collection.  All parameters are
		 * validated before using this method.
		 * @param type Appointment Type, O for One Time, W for Weekly, D for Daily
		 * @param date A GregorianCalendar Date representing the time of the Appointment
		 * @param description The description of the Appointment.
		 */
		public void addAppt(char type, GregorianCalendar date, String description);
		
		
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
		public String displayAppt(int day, int month, int year);
						
		
		/**
		 * A method to return the number of appointments that are within the collection
		 * @return the number of appointments that are within the collection
		 */
		public int GetAppointmentCount();
			
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
		public String toString();

}
