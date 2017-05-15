import java.io.Serializable;
import java.util.*;

import com.sun.corba.se.impl.orbutil.graph.Node;

/**
 * Assignment 5, Spring 2015:
 * The BST implements BSTInterface with buckets. A treemap will be used to create method to add to the hashtable 
 * and other methods necessary
 * @author E Cross
 *
 */
public class BST<K>implements BSTInterface {
	private static final long serialVersionUID = 1L;
	TreeMap<Object, Object> tree = new TreeMap<>();
	ArrayList<K> a;
	Course c;
	ArrayList<DataElementInterface> r2;

	/**
	 * no-arg constructor
	 */
	public BST()
	{
		
	}

	/**
	 * constructor
	 * @param comp comparator of generic type
	 */
	public BST(Comparator<K> comp)
	{

	}


	/**
	 * Remove from the binary search tree
	 * @param entry The data to be removed
	 * @return true if the remove was successful, false if unsuccessful (entry not found)
	 */
	public boolean remove(K entry){
		boolean success; 
		if(tree.size() != 0){
			tree.remove(entry);
			success = true;
			return success;
		}
		else{
			success = false;
			return success;
		}
	}

	/**
	 * Add to the binary search tree
	 * @param entry The data to be added
	 * @return true if the add was successful, false if unsuccessful (entry already exists)
	 */
	public boolean add(K entry){
		boolean success;
		int count = 0;
		tree.put(entry, entry);
		count++;
		
		if(count > 0){
			success = true;
			return success;
		}
		else{
			success = false;
			return success;
		}
	}

	/**
	 * Search to see if in binary search tree
	 * @param entry The data searching for
	 * @return reference to the data if found, null if not found
	 */
	public K search(K entry){
		boolean found;
		if(tree.containsKey(entry)){
			found = true;
			return (K) tree.get(entry); 
		}
		else{
			found = false;
			return null;
		}	
	}

	/**
	 * Returns the number of elements in the binary search tree
	 * @return the number of elements in the binary search tree
	 */
	public int size(){
		return tree.size();
	}

	/**
	 * Traverses the nodes of a binary search tree in key order.
	 * Places references to the nodes in an ArrayList
	 * @return ArrayList of references to the nodes in key order
	 */
	public ArrayList<K> inOrderTraversal(){
		a = new ArrayList<K>();;
		Set set = tree.entrySet();
		
		Iterator<K> iter = set.iterator();//just added k
		
		while(iter.hasNext()){
			Map.Entry<Object, Object> map = (Map.Entry<Object, Object>) iter.next();
			a.add((K) map.getKey());	
		}
		
		copyArray(a);
		return a;
		
	}
	
	/**
	 * Puts the entries in the treeMap in a ArrayList
	 * @param w ArrayList
	 * @return ArrayList of entries of DataElementInterface type 
	 */
	public ArrayList<DataElementInterface> copyArray(ArrayList<K> w){
		r2 = new ArrayList<DataElementInterface>();
		//System.out.println("a inside copy array "+a);
	
		for(int i = 0; i < w.size(); i++){
			r2.add((DataElementInterface) w.get(i));
			//System.out.println("r2 at index " + i + " " + r2.get(i));	
		}
		//System.out.println("r" + r2);
		return  r2;
	}
	
	

	/**
	 * Remove from the binary search tree
	 * @param entry The data to be removed
	 * @return true if the remove was successful, false if unsuccessful (entry not found)
	 */
	public boolean remove(DataElementInterface entry) {
		boolean success; 
		if(tree.size() != 0){
			tree.remove(entry);
			success = true;
			return success;
		}
		else{
			success = false;
			return success;
		}
			
	}

	/**
	 * Add to the binary search tree
	 * @param entry The data to be added
	 * @return true if the add was successful, false if unsuccessful (entry already exists)
	 */
	public boolean add(DataElementInterface entry) {
		boolean success;
		int count = 0;
		tree.put(entry,entry);
		count++;
		
		if(count > 0){
			success = true;
			return success;
		}
		else{
			success = false;
			return success;
		}
	}

	/**
	 * Search to see if in binary search tree
	 * @param entry The data searching for
	 * @return reference to the data if found, null if not found
	 */
	public DataElementInterface search(DataElementInterface entry) {
		boolean found;
		if(tree.containsKey(entry)){
			found = true;
			return (DataElementInterface) tree.get(entry); 
		}
		else{
			found = false;
			return null;
		}
	}
}