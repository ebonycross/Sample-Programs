import java.io.Serializable;
import java.util.ArrayList;


/**Binary Search Tree interface
 * Provides all the basic functions of a Binary Search Tree
 * as well as a method that returns an ArrayList of all the nodes in the Tree
 * in key order
 * @author Professor Myers
 *
 * You may need to implement the following methods as private methods in your class that implements this interface
 * to use ObjectInputStream and ObjectOutputStream classes, depending on your design
 * private void readObject(java.io.ObjectInputStream in)throws IOException, ClassNotFoundException();
 * private void writeObject(ObjectOutputStream s) throws IOException();
 */ 
public interface BSTInterface<K extends DataElementInterface> extends Serializable {
	
	/**
	 * Remove from the binary search tree
	 * @param entry The data to be removed
	 * @return true if the remove was successful, false if unsuccessful (entry not found)
	 */
	public boolean remove(K entry);
	
	/**
	 * Add to the binary search tree
	 * @param entry The data to be added
	 * @return true if the add was successful, false if unsuccessful (entry already exists)
	 */
	public boolean add(K entry);
	
	/**
	 * Search to see if in binary search tree
	 * @param entry The data searching for
	 * @return reference to the data if found, null if not found
	 */
	public K search(K entry);
	
	/**
	 * Returns the number of elements in the binary search tree
	 * @return the number of elements in the binary search tree
	 */
	public int size();
	
	/**
	 * Traverses the nodes of a binary search tree in key order.
	 * Places references to the nodes in an ArrayList
	 * @return ArrayList of references to the nodes in key order
	 */
	public ArrayList<K> inOrderTraversal();
	
	
}
