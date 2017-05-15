import java.io.Serializable;
import java.util.ArrayList;

/**
 * Data Manager for the collection of Course objects.
 * Stored in a binary search tree.
 * @author Professor Myers
 *
 */
public interface DataManagerInterface extends Serializable, Tableizeable<DataManagerInterface>{
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	/**
	 * Adds a course into the binary search tree
	 * @param course - course to be added
	 */
	public void add(DataElementInterface course);
	
	/**
	 * Creates a course from the following parameters and then adds the 
	 * course to the binary search tree
	 * @param cn course number
	 * @param in instructor
	 * @param desc course description
	 * @param cred number of credits
	 * @param day days of class
	 * @param hour time of class
	 */
	public void add(String cn, String in, String desc, int cred, String day, String hour);
	
	/**
	 * Removes a course from the binary search tree
	 * @param key course number of course to remove
	 * @return true if remove is successful, false if key not found
	 */
	public boolean remove(String key);
	
	/**
	 * 
	 * @param key course number of course to search for
	 * @return reference to CourseElement if found, null if not found
	 */
	public DataElementInterface search(String key);
	
	/**
	 * Returns the number of elements in the Data Collection
	 * @return number of elements in the Data Collection
	 */
	public int size();
	
	/**
	 * The courses in the binary search tree are put in an
	 * ArrayList based on in order traversal 
	 * @return ArrayList of CourseElements in order
	 */
	public ArrayList<DataElementInterface> toArrayList();
	
	/**
	 * The courses in the binary search tree are put in a
	 * two-dimensional array of Strings to populate the table
	 * [][0] - course number
	 * [][1] - instructor
	 * [][2] - description
	 * [][3] - credits
	 * [][4] - days
	 * [][5] - hours/time
	 * @return two dimensional array of Strings to populate the table
	 */
	public String[][] toTable();
	
}
