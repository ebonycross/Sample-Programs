import static org.junit.Assert.*;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class AppointmentsTest {

	Appointments apt1, apt2;

	@Before
	public void setUp() throws Exception {

		apt1 = new Appointments();
		apt1.addAppt('O', new GregorianCalendar(2015, 0, 05,10, 0), "Dr. Appt");
		apt1.addAppt('W', new GregorianCalendar(2015, 0, 04, 9, 30), "Zumba Class");
		apt1.addAppt('D', new GregorianCalendar(2015, 0, 01, 8, 0), "Walk the Dog");
		apt1.addAppt('O', new GregorianCalendar(2015, 0, 21, 20, 30), "Eric's Bday Party");
		apt1.addAppt('D', new GregorianCalendar(2015, 0, 15, 7, 30), "Treadmill");
		apt1.addAppt('W', new GregorianCalendar(2015, 1, 1, 18, 30), "Call Mom");
		apt1.addAppt('W', new GregorianCalendar(2015, 1, 2, 16, 0), "Band Practice");
		apt1.addAppt('W', new GregorianCalendar(2015, 0, 27, 15, 30), "Piano Lessons");
		apt1.addAppt('O', new GregorianCalendar(2015, 1, 14, 19, 0), "Valentine Dinner");

		apt2 = new Appointments();
		apt2.addAppt('D', new GregorianCalendar(2015, 0, 25,11, 0), "Yoga");
		apt2.addAppt('D', new GregorianCalendar(2015, 7, 04, 9, 30), "Dentist");
		apt2.addAppt('W', new GregorianCalendar(2015, 2, 01, 16, 0), "Happy Hour");
		apt2.addAppt('O', new GregorianCalendar(2015, 1, 21, 20, 30), "Dinner");
	}

	@After
	public void tearDown() throws Exception {

		apt1 = null;
	}

	@Test
	public void testAddAppt() {
		assertEquals(9,apt1.GetAppointmentCount());

		String expected = "January 5, 2015 10:00 AM Dr. Appt\n"+
				"January 4, 2015 9:30 " +"AM Zumba Class\n"+
				"January 1, 2015 8:00 AM Walk the Dog\n"+
				"January 21, 2015 8:30 PM Eric's Bday Party\n"+
				"January 15, 2015 7:30 AM Treadmill\n"+
				"February 1, 2015 6:30 PM Call Mom\n"+
				"February 2, 2015 4:00 PM Band Practice\n"+
				"January 27, 2015 3:30 PM Piano Lessons\n"+
				"February 14, 2015 7:00 PM Valentine Dinner\n";
		assertEquals(expected, apt1.toString());
		apt1.addAppt('O', new GregorianCalendar(2015, 0, 21, 14, 0), "Presentation");
		String expected2 = "Appointments on 1/21/2015\n\n"+
				"8:00 AM Walk the Dog\n"+
				"8:30 PM Eric's Bday Party\n"+
				"7:30 AM Treadmill\n"+
				"2:00 PM Presentation\n";
		assertEquals(expected2, apt1.displayAppt(21, 0, 2015));
		//assertFalse("Student method not implemented yet",true);
	}


	/**
	 * Student created test for add appointment
	 */
	@Test
	public void testAddApptSTUDENT() {

		assertEquals(4,apt2.GetAppointmentCount());

		String expected = "January 25, 2015 11:00 AM Yoga\n"+
				"August 4, 2015 9:30 " +"AM Dentist\n"+
				"March 1, 2015 4:00 PM Happy Hour\n"+
				"February 21, 2015 8:30 PM Dinner\n";

		assertEquals(expected, apt2.toString());
		apt2.addAppt('O', new GregorianCalendar(2015, 11, 21, 14, 0), "Dance Lesson");
		String expected2 = "Appointments on 12/21/2015\n\n"+
				"11:00 AM Yoga\n"+
				"9:30 AM Dentist\n"+
				"2:00 PM Dance Lesson\n";
		assertEquals(expected2, apt2.displayAppt(21, 11, 2015));
	}



	@Test
	public void testDisplayAppt() {

		String expected1 = "Appointments on 2/14/2015\n\n"+
				"8:00 AM Walk the Dog\n"+
				"7:30 AM Treadmill\n"+
				"7:00 PM Valentine Dinner\n";

		String expected2 = "Appointments on 1/21/2015\n\n"+
				"8:00 AM Walk the Dog\n"+
				"8:30 PM Eric's Bday Party\n"+
				"7:30 AM Treadmill\n";

		String expected3 = "Appointments on 2/2/2015\n\n"+
				"8:00 AM Walk the Dog\n"+
				"7:30 AM Treadmill\n" +
				"4:00 PM Band Practice\n";

		String expected4 = "There are no appointments on 12/30/2012";

		String expected5 = "Appointments on 1/27/2015\n\n"+
				"8:00 AM Walk the Dog\n"+
				"7:30 AM Treadmill\n" +
				"3:30 PM Piano Lessons\n";

		assertEquals(expected1, apt1.displayAppt(14, 1, 2015));
		assertEquals(expected2, apt1.displayAppt(21, 0, 2015));
		assertEquals(expected3, apt1.displayAppt(2, 1, 2015));
		assertEquals(expected4, apt1.displayAppt(30, 11, 2012));
		assertEquals(expected5, apt1.displayAppt(27, 0, 2015));
	}
	
	
	
	/**
	 * Student created test for display appointments
	 */
	@Test
	public void testDisplayApptSTUDENT() {
		String expected1 = "Appointments on 2/5/2015\n\n"+
				"11:00 AM Yoga\n";

		String expected2 = "Appointments on 2/21/2015\n\n"+
				"11:00 AM Yoga\n" +
				"8:30 PM Dinner\n";;

		String expected3 = "Appointments on 12/2/2015\n\n"+
				"11:00 AM Yoga\n"+
				"9:30 AM Dentist\n";
				

		String expected4 = "There are no appointments on 7/5/2000";

		

		assertEquals(expected1, apt2.displayAppt(05, 1, 2015));
		assertEquals(expected2, apt2.displayAppt(21, 1, 2015));
		assertEquals(expected3, apt2.displayAppt(02, 11, 2015));
		assertEquals(expected4, apt2.displayAppt(05, 06, 2000));
		
		//assertFalse("Student method not implemented yet",true);
	}

	@Test
	public void testGetAppointmentCount() {

		assertEquals(9, apt1.GetAppointmentCount());
	}

	@Test
	public void testToString() {

		assertEquals(9,apt1.GetAppointmentCount());

		String expected = "January 5, 2015 10:00 AM Dr. Appt\n"+
				"January 4, 2015 9:30 " +"AM Zumba Class\n"+
				"January 1, 2015 8:00 AM Walk the Dog\n"+
				"January 21, 2015 8:30 PM Eric's Bday Party\n"+
				"January 15, 2015 7:30 AM Treadmill\n"+
				"February 1, 2015 6:30 PM Call Mom\n"+
				"February 2, 2015 4:00 PM Band Practice\n"+
				"January 27, 2015 3:30 PM Piano Lessons\n"+
				"February 14, 2015 7:00 PM Valentine Dinner\n";

	}

}