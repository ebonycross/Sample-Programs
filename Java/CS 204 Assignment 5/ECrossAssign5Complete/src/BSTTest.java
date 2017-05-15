import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BSTTest {
	private BSTInterface<Course> bst, bst2;
	Course c1, c2, c3, c4, c5, c6;

	@Before
	public void setUp() throws Exception {
		bst = new BST<Course>();
		
		c1 = new Course("EN190","Chung","English Composition",3,"MW","9:00 - 10:15");
		c2 = new Course("CS226","McDowall","Intro to OOP",3,"TR","11:00 - 12:15");
		c3 = new Course("HS101","Smith","Health Services",3,"MW","9:00 - 10:15");
		c4 = new Course("FM101","Brown","Food Management",3,"MW","9:00 - 10:15");
		c5 = new Course("AM101","Jones","American History",3,"MW","9:00 - 10:15");
		c6 = new Course("DN140","Small","Tap Dance",3,"MWF","10:00 - 10:50");
		bst.add(c1);
		bst.add(c2);
		bst.add(c3);
		bst.add(c4);
		bst.add(c5);
		bst.add(c6);
		
		//student 
		bst2 = new BST<Course>();
		bst2.add(c1);
		bst2.add(c4);
		bst2.add(c5);
		bst2.add(c6);
	}

	@After
	public void tearDown() throws Exception {
		bst =bst2= null;
	}

	@Test
	public void testRemove() {
		assertEquals(6,bst.size());
		assertTrue(bst.inOrderTraversal().get(4).equals(c4));
		bst.remove(c4);
		assertTrue(bst.inOrderTraversal().get(4).equals(c3));
		assertEquals(5,bst.size());	
	}
	

	@Test
	public void testRemoveSTUDENT() {
		assertEquals(4,bst2.size());
		assertTrue(bst2.inOrderTraversal().get(0).equals(c5));
		bst2.remove(c5);
		assertTrue(bst2.inOrderTraversal().get(0).equals(c6));
		assertEquals(3,bst2.size());
		//fail("Not yet implemented");
	}
	
	@Test
	public void testAdd() {
		assertEquals(6,bst.size());
		assertTrue(bst.inOrderTraversal().get(5).equals(c3));
		bst.add(new Course("GL101", "Missy", "Glamour", 3, "TR", "1:00 - 1:50"));
		assertTrue(bst.inOrderTraversal().get(6).equals(c3));
		assertEquals(7,bst.size());
	}
	
	@Test
	public void testAddSTUDENT() {
		assertEquals(4,bst2.size());
		assertTrue(bst2.inOrderTraversal().get(2).equals(c4));
		bst2.add(new Course("Baking", "Glen", "Glamour", 3, "TR", "1:00 - 1:50"));
		assertTrue(bst2.inOrderTraversal().get(2).equals(c6));
		assertEquals(5,bst2.size());
		//fail("Not yet implemented");
	}

	@Test
	public void testSearch() {
		assertTrue(bst.search(c4)!=null);
		Course c7 = new Course("GL101", "Missy", "Glamour", 3, "TR", "1:00 - 1:50");
		assertFalse(bst.search(c7) != null);
	}
	
	@Test
	public void testSearchSTUDENT() {
		assertTrue(bst.search(c1)!=null);
		Course c8 = new Course("Baking", "Jobs", "Glamour", 3, "TR", "1:00 - 1:50");
		assertFalse(bst.search(c8) != null);
		//fail("Not yet implemented");
	}

	@Test
	public void testInOrderTraversal() {
		assertTrue(bst.inOrderTraversal().get(4).equals(c4));
		bst.remove(c4);
		assertTrue(bst.inOrderTraversal().get(4).equals(c3));
		bst.add(new Course("GL101", "Missy", "Glamour", 3, "TR", "1:00 - 1:50"));
		assertTrue(bst.inOrderTraversal().get(5).equals(c3));
	}

}