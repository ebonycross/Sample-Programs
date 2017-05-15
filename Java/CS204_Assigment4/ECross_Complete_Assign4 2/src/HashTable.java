import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.List;


/**
 * Assignment 4, Spring 2015:
 * The HashTable implemnets HashableTableInterface with buckets. An array of linked
 * lists (of HashableWord) will be used to create method to add to the hashtable 
 * and other methods necessary
 * @author E Cross
 *
 */
public class HashTable implements HashTableInterface {
	private LinkedList<HashableWordInterface>[] bucket;
	private static final int BUCKET_SIZE = 1300;
	private int tableSize = BUCKET_SIZE / 3;
	private int numOfWords;
	private int ip;
	private ArrayList<String> list;



	/**constructor
	 * initiates ArrayList of buckets
	 */
	HashTable(){
		numOfWords = 0;
		list = new ArrayList<String>();
		bucket = new LinkedList[tableSize];
		for(int index = 0; index < bucket.length; index++){
			bucket[index] = new LinkedList<HashableWordInterface>();
		}
	}

	/**
	 * adds an element to this set. Adds to the appropriate LinkedList by taking the wordHashCode()
	 * and mod(%) by the table size
	 * @param s A hashableWord to add to the HashTable
	 * @return the number of words currently in HashTable
	 */
	public int add(HashableWordInterface s){
		ip = s.hashCode() % tableSize;
		ListIterator<HashableWordInterface>i = bucket[ip].listIterator();

		if(bucket[ip].isEmpty()){
			//System.out.println("string is empty");
			i.add(s);
			//System.out.println("STRING IS ADDED");
			//System.out.println(s.getString() + " ");
			numOfWords++;
		}
		else{
			//System.out.println("string is NOT empty");
			i.add(s);

			//			ListIterator<HashableWordInterface>i2 = bucket[ip].listIterator();
			//			System.out.println("LINKED LIST IN BUCKET AT INDEX[IP]:");
			//			while(i2.hasNext()){
			//				s = i2.next();
			//
			//				System.out.print(s.getString() + " ");
			//
			//
			//			}
			//System.out.println();
			numOfWords++;
		}


		return numOfWords;	
	}

	/**
	 * Tests to see if the HashableWord is in the HashTable.
	 * @param s a HashableWord
	 * @return true if word contains in the dictionary
	 */
	public boolean contains(HashableWordInterface s){
		boolean included = false;
		String s2 = s.getString();
		int w = s.hashCode();
		int ip = w % tableSize;
		int counter = 0;

		if(bucket[ip].isEmpty()){
			included = false;
			return included;
		}

		for(int i = 0; i < bucket[ip].size(); i++){

			System.out.print(bucket[ip].get(i).getString() + " "); 
			String m = bucket[ip].get(i).getString();
			if(s2.equals(m)){
				counter++;

				included = true;
				return included;
			}
		}

		return included;

	}

	/**
	 * Puts the words in the hashtable in a sorted ArrayList
	 * @return ArrayList of words in hashtable in sorted order
	 */
	public ArrayList<String> sort(){
		copy(list);

		Collections.sort(list);
		return list;
	}

	/**
	 * Puts the words in the hashtable in a ArrayList
	 * @param s ArrayList
	 * @return ArrayList of words in hashtable 
	 */
	public ArrayList<String>copy(ArrayList<String> s){
		for(int i = 0; i < bucket.length; i++){
			for(int m = 0; m < bucket[i].size(); m++){
				s.add(bucket[i].get(m).getString());
				//list.addAll(bucket[i].get(m));
			}
		}
		return s;
	}


	/**
	 * String representation of ArrayList
	 * @return string of HashableWords from HashTable
	 */	
	public String toString(){
		String print = "";

		sort();

		for(int i = 0; i < list.size(); i++){
			print += list.get(i) + "\n";
		}
	
		return print;
	}
}//end of program






