import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Assignment 2, Spring 2015
 *SortedLinkedList class (generic T) Implements a generic sorted list using a provided
 *Comparator. It extends BasicLinkedList class.
 * @author E Cross
 */
public class SortedLinkedList<T> extends BasicLinkedList<T>{
	Comparator<T> cmp; //comparator ref variable

	/**no-arg constructor*/
	public SortedLinkedList(){
		super();
		size = 0;
	}

	/**
	 * creates an empty list associated with specified comparator
	 * @param comp Comparator type to allow comparison methods in class methods
	 */
	public SortedLinkedList(java.util.Comparator<T> comp) {
		cmp = comp;
	}

	/**
	 * prints of data of current node
	 */
	public void showAll() {
		Node<T> nextNode = head;
		while(nextNode !=null){
			System.out.print(nextNode.element +"  ");
			nextNode=nextNode.next;
		}
		System.out.println();
	}

	/**
	 * This operation is invalid for a sorted list.
	 * An UnsupportedOperationException will be generated 
	 * using the message "Invalid operation for sorted list."
	 */
	public void addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	/**This operation is invalid for a sorted list.
	 *An UnsupportedOperationException will be generated 
	 *using the message "Invalid operation for sorted list."
	 */
	public void addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	/**
	 * inserts the specified element at the correct position in the sorted list.
	 *@param newdata generic element 
	 */
	public void add(T newdata) {
		Node<T> previousNode = head;
		Node<T> nextNode = head;
		if(head == null){
			super.addToFront(newdata);
		}
		else  
			if(cmp.compare(newdata, head.element) < 0){
				super.addToFront(newdata);		
			}

		else{
			while(nextNode.next !=null){
				previousNode = nextNode;
				nextNode = nextNode.next;  

				if(cmp.compare(newdata, nextNode.element) < 0 ){
					previousNode.next = new Node<T>(newdata);
					previousNode.next.next = nextNode;
					return;
				}
			}
				//tail = new Node<T>(newdata);
				//nextNode.next = tail;	
				super.addToEnd(newdata);
			}
		//System.out.println(newdata);
	}

	/**Implements the iterator by calling the super class iterator method.
	 * @return an iterator positioned at the head of the list by creating instance
	 * of LinkedListIterator
	 */
	public java.util.Iterator<T> iterator(){
		super.iterator();
		return new LinkedListIterator();
	}
}//end of program
