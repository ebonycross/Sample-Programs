
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class DataManagerTest {
	private DataManagerInterface courses, courses2;
	private static File outputFile;
	
	@Before
	public void SetUp() throws Exception {
		courses = new Courses();
		courses2 = new Courses();
		populateCourses();
	}
	
	@AfterClass
	public static void oneTimeTearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		assertEquals(6,courses.size());
		courses.add("RN201","Chappel","Advanced Nursing",3,"MWF","10:00 - 10:50");
		assertEquals(7,courses.size());
		ArrayList<DataElementInterface> courseArray = courses.toArrayList();
		String [] lastRow = courseArray.get(6).toTableRow();
		assertEquals("RN201",lastRow[0]);
	}
	
	@Test
	public void testAddSTUDENT() {
		assertEquals(3,courses2.size());
		courses2.add("Bak101","Chap","Advanced Baking",3,"MWF","10:00 - 10:50");
		assertEquals(4,courses2.size());
		ArrayList<DataElementInterface> courseArray2 = courses2.toArrayList();
		String [] lastRow = courseArray2.get(1).toTableRow();
		assertEquals("Bak101",lastRow[0]);
		//fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		assertEquals(6,courses.size());
		courses.remove("FM101");
		assertEquals(5,courses.size());
		ArrayList<DataElementInterface> courseArray = courses.toArrayList();
		String [] lastRow = courseArray.get(4).toTableRow();
		assertEquals("HS101",lastRow[0]);
	}
	
	@Test
	public void testRemoveSTUDENT(){
		assertEquals(3,courses2.size());
		courses2.remove("DN140");
		assertEquals(2,courses2.size());
		ArrayList<DataElementInterface> courseArray2 = courses2.toArrayList();
		String [] lastRow = courseArray2.get(1).toTableRow();
		assertEquals("FM101",lastRow[0]);
		//fail("Not yet implemented");
	}

	@Test
	public void testSearch() {
		assertTrue(courses.search("FM101") != null);
		assertTrue(courses.search("SM202") == null);
	}
	
	@Test
	public void testSearchSTUDENT() {
		assertTrue(courses2.search("AM101") != null);
		assertTrue(courses2.search("ZOL302") == null);
		//fail ("Not yet implemented");
	}

	@Test
	public void testToArrayList() {
		ArrayList<DataElementInterface> courseArray = courses.toArrayList();
		String [] row = courseArray.get(3).toTableRow();
		assertEquals("EN190",row[0]);
	}

	@Test
	public void testToTable() {
		String [][] table = courses.toTable();
		assertEquals("AM101",table[0][0]);
		assertEquals("FM101",table[4][0]);
	}
	
	@Test
	public void testReadandWriteBinary()
	{
		
		writeBinaryFile();
		courses = new Courses();
		readBinaryFile();
		String [][] table = courses.toTable();
		assertEquals("AM101",table[0][0]);
		assertEquals("FM101",table[4][0]);
		ArrayList<DataElementInterface> courseArray = courses.toArrayList();
		String [] row = courseArray.get(3).toTableRow();
		assertEquals("EN190",row[0]);
		
	}
	

	private void populateCourses()
	{
		courses.add("EN190","Chung","English Composition",3,"MW","9:00 - 10:15");
		courses.add("CS226","McDowall","Intro to OOP",3,"TR","11:00 - 12:15");
		courses.add("HS101","Smith","Health Services",3,"MW","9:00 - 10:15");
		courses.add("FM101","Brown","Food Management",3,"MW","9:00 - 10:15");
		courses.add("AM101","Jones","American History",3,"MW","9:00 - 10:15");
		courses.add("DN140","Small","Tap Dance",3,"MWF","10:00 - 10:50");
		
		
		//student
		courses2.add("FM101","Brown","Food Management",3,"MW","9:00 - 10:15");
		courses2.add("AM101","Jones","American History",3,"MW","9:00 - 10:15");
		courses2.add("DN140","Small","Tap Dance",3,"MWF","10:00 - 10:50");
		
	}
	
	private void writeBinaryFile()

	{
		populateCourses();

		try{
			outputFile = new File("test.bin");
			
			ObjectOutputStream out = new ObjectOutputStream
					(new FileOutputStream(outputFile));
			out.writeObject(courses);
			out.close();
		}//end of try block
		catch(Exception e)
		{
			System.out.println("Error "+e.getMessage());
		}
	} // end of writeBinaryFile
	
	private void readBinaryFile()

	{
		try{
					ObjectInputStream in = new ObjectInputStream
							(new FileInputStream(outputFile));
					courses = (Courses) in.readObject();
					in.close();

		}// end of try block
		catch(Exception e)
		{
			System.out.println("Error");
		}

	}
}