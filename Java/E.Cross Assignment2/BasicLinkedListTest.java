
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BasicLinkedListTest {
	BasicLinkedList<String> linkedString;
	BasicLinkedList<Double> linkedDouble;
	StringComparator comparator;
	DoubleComparator comparatorD;
	
	//student version
	BasicLinkedList<String> linkedString2;
	BasicLinkedList<Double> linkedDouble2;
	StringComparator comparator2;
	DoubleComparator comparatorD2;


	@Before
	public void setUp() throws Exception {
		linkedString = new BasicLinkedList<String>();
		linkedString.addToEnd("Hello");
		linkedString.addToEnd("World");
		comparator = new StringComparator();
		
		linkedDouble = new BasicLinkedList<Double>();
		linkedDouble.addToEnd(15.0);
		linkedDouble.addToEnd(100.0);
		comparatorD = new DoubleComparator();
		
		//STUDENT
		linkedString2 = new BasicLinkedList<String>();
		linkedString2.addToEnd("Ebony");
		linkedString2.addToEnd("Cross");
		comparator2 = new StringComparator();
		
		linkedDouble2 = new BasicLinkedList<Double>();
		linkedDouble2.addToEnd(115.0);
		linkedDouble2.addToEnd(1000.0);
		comparatorD2 = new DoubleComparator();
		
	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
		comparator = null;
		
		//STUDENT
		linkedString2 = null;
		comparator2 = null;

	}

	@Test
	public void testGetSize() {
		assertEquals(2,linkedString.getSize());
		assertEquals(2,linkedDouble.getSize());
	}
	
	@Test
	public void testAddToEnd() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("End");
		assertEquals("End", linkedString.getLast());
	
		assertEquals(100.0, linkedDouble.getLast(), .001);
		linkedDouble.addToEnd(50.0);
		assertEquals(50.0, linkedDouble.getLast(), .001);
	}

	@Test
	public void testAddToFront() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("Begin");
		assertEquals("Begin", linkedString.getFirst());
	}

	@Test
	public void testAddToFrontSTUDENT(){
		assertEquals("Ebony", linkedString2.getFirst());
		linkedString2.addToFront("Mrs");
		assertEquals("Mrs", linkedString2.getFirst());
		//fail("Not yet implemented");
		
		assertEquals(115.0, linkedDouble2.getFirst(), .001);
		linkedDouble2.addToFront(1.0);
		assertEquals(1.0, linkedDouble2.getFirst(), .001);
	}
	
	@Test
	public void testGetFirst() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());
		
		assertEquals(15.0, linkedDouble.getFirst(), .001);
		linkedDouble.addToFront(50.0);
		assertEquals(50.0, linkedDouble.getFirst(), .001);
	}

	@Test
	public void testGetLast() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("New");
		assertEquals("New", linkedString.getLast());
	}

	@Test
	public void testGetLastSTUDENT(){
		assertEquals("Cross", linkedString2.getLast());
		linkedString2.addToEnd("Jr");
		assertEquals("Jr", linkedString2.getLast());
		
		assertEquals(1000.0, linkedDouble2.getLast(), .001);
		linkedDouble2.addToEnd(11.0);
		assertEquals(11.0, linkedDouble2.getLast(), .001);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testToArrayList()
	{
		ArrayList<String> list;
		linkedString.addToFront("Begin");
		linkedString.addToEnd("End");
		list = linkedString.toArrayList();
		assertEquals("Begin", list.get(0));
		assertEquals("Hello", list.get(1));
		assertEquals("World", list.get(2));
		assertEquals("End", list.get(3));
		
		ArrayList<Double> listD;
		linkedDouble.addToFront(1.0);
		linkedDouble.addToEnd(1000.0);
		listD = linkedDouble.toArrayList();
		assertEquals(1.0, listD.get(0), .001);
		assertEquals(15.0, listD.get(1), .001);
		assertEquals(100.0, listD.get(2), .001);
		assertEquals(1000.0, listD.get(3), .001);
	}
	
	@Test
	public void testIterator() {
		linkedString.addToFront("Begin");
		linkedString.addToEnd("End");
		Iterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("End", iterator.next());
		try{
			//no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
		try{
			//remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		
	}

	@Test
	public void testIteratorSTUDENT(){
		linkedDouble2.addToFront(114.0);
		linkedDouble2.addToEnd(1001.0);
		Iterator<Double> iterator = linkedDouble2.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(114.0, iterator.next(), .001);
		assertEquals(115.0, iterator.next(), .001);
		assertEquals(1000.0, iterator.next(), .001);
		assertEquals(true, iterator.hasNext());
		assertEquals(1001.0, iterator.next(), .001);
		try{
			//no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
		try{
			//remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		//test the iterator for the linkedDouble
		//be thorough, use the preceding test method as an example
		//fail("Not yet implemented");
	}	

	@Test
	public void testRetrieveFirstElement() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());
		assertEquals("New", linkedString.retrieveFirstElement());
		assertEquals("Hello",linkedString.getFirst());
		assertEquals("Hello", linkedString.retrieveFirstElement());
		assertEquals("World",linkedString.getFirst());
		
		assertEquals(15.0, linkedDouble.getFirst(), .001);
		linkedDouble.addToFront(1.0);
		assertEquals(1.0, linkedDouble.getFirst(), .001);
		assertEquals(1.0, linkedDouble.retrieveFirstElement(), .001);
		assertEquals(15.0,linkedDouble.getFirst(), .001);
		assertEquals(15.0, linkedDouble.retrieveFirstElement(), .001);
		assertEquals(100.0,linkedDouble.getFirst(), .001);
		
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("New");
		assertEquals("New", linkedString.getLast());
		assertEquals("New", linkedString.retrieveLastElement());
		assertEquals("World",linkedString.getLast());
		
		linkedDouble.addToEnd(1000.0);
		assertEquals(1000.0, linkedDouble.getLast(), .001);
		assertEquals(1000.0, linkedDouble.retrieveLastElement(), .001);
		assertEquals(100.0, linkedDouble.getLast(), .001);
		assertEquals(100.0, linkedDouble.retrieveLastElement(), .001);
		assertEquals(15.0, linkedDouble.getLast(), .001);
	}
	
	@Test
	public void testRetrieveLastElementSTUDENT(){
		assertEquals("Cross", linkedString2.getLast());
		linkedString2.addToEnd("student");
		assertEquals("student", linkedString2.getLast());
		assertEquals("student", linkedString2.retrieveLastElement());
		assertEquals("Cross",linkedString2.getLast());
		
		assertEquals(1000.0, linkedDouble2.getLast(), .001);
		linkedDouble2.addToEnd(55.0);
		assertEquals(55.0, linkedDouble2.getLast(), .001);
		assertEquals(55.0, linkedDouble2.retrieveLastElement(), .001);
		assertEquals(1000.0, linkedDouble2.getLast(), .001);
		assertEquals(1000.0, linkedDouble2.retrieveLastElement(), .001);
		assertEquals(115.0, linkedDouble2.getLast(), .001);
		//test retrieveLastElement for linkedDouble
		//fail("Not yet implemented");
	}

	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
}