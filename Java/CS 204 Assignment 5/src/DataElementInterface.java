import java.io.Serializable;
import java.util.ArrayList;

/**
 * Data Element for the collection of Course objects.
 *
 * @author Professor Myers
 *
 */
public interface DataElementInterface extends Comparable<Keyable<DataElementInterface>>, Keyable<DataElementInterface>,Serializable
{
	/**
	 * Determines the key to use for comparing data element objects
	 * @return string
	 */
    public String getKey();
    
    /**
     * Determines if one DataElement is equal to another
     * @param otherElement DataElement to compare to
     * @return true if the two DataElements are equal, false if not
     */
    public boolean equals(Object otherElement);
    
    /**
     * Returns the data of the DataElement in an ArrayList of Strings
     * @return ArrayList of Strings in the following order:
     * 		[courseName, instructorName, description, numCredits, days, time]
     */
    public ArrayList<Comparable<String>> toArrayList();

    /**
     * Returns an array to populate one row of a table
     * @return Array of strings in the following order:
     * [0] - courseName
     * [1] - instructorName
     * [2] - description
     * [3] - numCredits
     * [4] - days
     * [5] - time
     */
    public String[] toTableRow();
    
    /**
     * String representation of the DataElement object
     * @return string
     */
    public String toString();
}
