import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SortedLinkedListTest {
	SortedLinkedList<String> sortedLinkedString;
	SortedLinkedList<Double> sortedLinkedDouble;
	StringComparator comparator;
	DoubleComparator comparatorD;
	
	//STUDENT
	SortedLinkedList<String> sortedLinkedString2;
	SortedLinkedList<Double> sortedLinkedDouble2;
	StringComparator comparator2;
	DoubleComparator comparatorD2;

	@Before
	public void setUp() throws Exception {
		comparator = new StringComparator();
		sortedLinkedString = new SortedLinkedList<String>(comparator);
		comparatorD = new DoubleComparator();
		sortedLinkedDouble = new SortedLinkedList<Double>(comparatorD);
		
		//STUDENT
		comparator2 = new StringComparator();
		sortedLinkedString2 = new SortedLinkedList<String>(comparator);
		comparatorD2 = new DoubleComparator();
		sortedLinkedDouble2 = new SortedLinkedList<Double>(comparatorD);
		
	}

	@After
	public void tearDown() throws Exception {
		comparator = null;
		sortedLinkedString = null;
		
		//STUDENT
		comparator2 = null;
		sortedLinkedString2 = null;
	}

	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedString.addToEnd("Hello");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToEndSTUDENT() {
		try {
			sortedLinkedString2.addToEnd("Cross");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		
		try {
			sortedLinkedDouble2.addToEnd(1000.0);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}
	
	@Test
	public void testAddToFront() {
		try {
			sortedLinkedString.addToFront("Hello");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFrontSTUDENT() {
		try {
			sortedLinkedString2.addToFront("Ebony");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		
		try {
			sortedLinkedDouble2.addToFront(115.0);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}
	
	@Test
	public void testIterator() {
		sortedLinkedString.add("Hello");
		sortedLinkedString.add("World");
		sortedLinkedString.add("Begin");
		sortedLinkedString.add("Zebra");
		Iterator<String> iterator = sortedLinkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("Zebra", iterator.next());
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
		
		sortedLinkedDouble.add(2.0);
		sortedLinkedDouble.add(3.0);
		sortedLinkedDouble.add(1.0);
		sortedLinkedDouble.add(4.0);
		Iterator<Double> iteratorD = sortedLinkedDouble.iterator();
		assertEquals(true, iteratorD.hasNext());
		assertEquals(1.0, iteratorD.next(), .001);
		assertEquals(2.0, iteratorD.next(), .001);
		assertEquals(3.0, iteratorD.next(), .001);
		assertEquals(true, iteratorD.hasNext());
		assertEquals(4.0, iteratorD.next(), .001);
		try{
			//no more elements in list
			iteratorD.next();
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
			iteratorD.remove();
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
	public void testIteratorSTUDENT() {
		sortedLinkedString2.add("Harry");
		sortedLinkedString2.add("William");
		sortedLinkedString2.add("Barb");
		sortedLinkedString2.add("Zach");
		Iterator<String> iterator = sortedLinkedString2.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Barb", iterator.next());
		assertEquals("Harry", iterator.next());
		assertEquals("William", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("Zach", iterator.next());
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
		
		sortedLinkedDouble2.add(22.0);
		sortedLinkedDouble2.add(33.0);
		sortedLinkedDouble2.add(11.0);
		sortedLinkedDouble2.add(44.0);
		Iterator<Double> iteratorD2 = sortedLinkedDouble2.iterator();
		assertEquals(true, iteratorD2.hasNext());
		assertEquals(11.0, iteratorD2.next(), .001);
		assertEquals(22.0, iteratorD2.next(), .001);
		assertEquals(33.0, iteratorD2.next(), .001);
		assertEquals(true, iteratorD2.hasNext());
		assertEquals(44.0, iteratorD2.next(), .001);
		try{
			//no more elements in list
			iteratorD2.next();
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
			iteratorD2.remove();
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
	public void testAdd() {
		sortedLinkedString.add("Banana");
		sortedLinkedString.add("Elephant");
		sortedLinkedString.add("Apple");
		assertEquals("Apple", sortedLinkedString.getFirst());
		assertEquals("Elephant", sortedLinkedString.getLast());
		sortedLinkedString.add("Cat");
		sortedLinkedString.add("Dog");
		assertEquals("Apple", sortedLinkedString.getFirst());
		assertEquals("Elephant", sortedLinkedString.getLast());
		//deletes Elephant from linked list
		assertEquals("Elephant",sortedLinkedString.retrieveLastElement());
		assertEquals("Dog", sortedLinkedString.getLast());
		
		sortedLinkedDouble.add(2.0);
		sortedLinkedDouble.add(5.0);
		sortedLinkedDouble.add(1.0);
		assertEquals(1.0, sortedLinkedDouble.getFirst(), .001);
		assertEquals(5.0, sortedLinkedDouble.getLast(), .001);
		sortedLinkedDouble.add(3.0);
		sortedLinkedDouble.add(4.0);
		assertEquals(1.0, sortedLinkedDouble.getFirst(), .001);
		assertEquals(5.0, sortedLinkedDouble.getLast(), .001);
		//deletes Elephant from linked list
		assertEquals(5.0,sortedLinkedDouble.retrieveLastElement(), .001);
		assertEquals(4.0, sortedLinkedDouble.getLast(), .001);
	}
	
	@Test
	public void testAddSTUDENT() {
		sortedLinkedString2.add("Berry");
		sortedLinkedString2.add("Elf");
		sortedLinkedString2.add("Aaron");
		assertEquals("Aaron", sortedLinkedString2.getFirst());
		assertEquals("Elf", sortedLinkedString2.getLast());
		sortedLinkedString2.add("Coat");
		sortedLinkedString2.add("Dogma");
		assertEquals("Aaron", sortedLinkedString2.getFirst());
		assertEquals("Elf", sortedLinkedString2.getLast());
		//deletes Elephant from linked list
		assertEquals("Elf",sortedLinkedString2.retrieveLastElement());
		assertEquals("Dogma", sortedLinkedString2.getLast());
		
		sortedLinkedDouble2.add(22.0);
		sortedLinkedDouble2.add(55.0);
		sortedLinkedDouble2.add(11.0);
		assertEquals(11.0, sortedLinkedDouble2.getFirst(), .001);
		assertEquals(55.0, sortedLinkedDouble2.getLast(), .001);
		sortedLinkedDouble2.add(43.0);
		sortedLinkedDouble2.add(34.0);
		assertEquals(11.0, sortedLinkedDouble2.getFirst(), .001);
		assertEquals(55.0, sortedLinkedDouble2.getLast(), .001);
		//deletes Elephant from linked list
		assertEquals(55.0,sortedLinkedDouble2.retrieveLastElement(), .001);
		assertEquals(43.0, sortedLinkedDouble2.getLast(), .001);
	}


	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			return arg0.compareTo(arg1);
		}
		
	}
}
