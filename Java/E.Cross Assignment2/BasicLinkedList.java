import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator; 
import java.util.NoSuchElementException;

/**
 * Assignment 2, Spring 2015
 * The BasicLinkedList (generic T) class contains a generic singly-linked list that 
 * relies on a head(reference to first element) and tail(reference to the last element).
 * Both are set to null when the list is empty. Both point to the same element 
 * when there is only one element in the list. A node structure has only two 
 * fields: data and next reference. The class must only define the following
 * entities: a protected class Node, head and tail references and an integer 
 * representing the list size. All the entities are defined as protected so
 * they can be accessed by the subclass.
 * @author E Cross
 */
public class BasicLinkedList<T> implements Iterable<T> {
	/**
	 * inner nested node class for generic type T
	 */
	public class Node<T> {//inner node class
		T element; 
		Node<T> next;

		/**
		 * Node constructor
		 * @param data sends in data of specified node
		 */
		public Node (T data){
			element = data;
			next = null;
		}
		/**
		 * node constructor
		 * @param data sends in data of specified node
		 * @param nodeRef sends in a node
		 */
		public Node (T data, Node<T> nodeRef){
			element = data;
			next = nodeRef;
		}
		
		/**
		 * retrieves data element from specified node
		 * @return specified data element
		 */
		public T getElement(){ // return data of node referenced
			return element;
		}
		
		/**
		 * retrieves the next node in list
		 * @return next node in the list
		 */
		public Node<T> getNext(){
			return next;
		}
		
		/**
		 * sets the node next from the list to new node n
		 * @param n node 
		 */
		public void setNext(Node<T> n){
			next = n;
		}
	}

	/**
	 *inner class called iterator that implements all necessary methods
	 */
	protected class LinkedListIterator implements Iterator<T>{
		Node<T> ip;
		
		/** linkedlistIterator constructor*/
		LinkedListIterator(){
			ip = head;
		}

		@Override
		/**
		 * checks to if statement of next node present is true
		 * @return true is node is present
		 */
		public boolean hasNext() {
			if(ip == null){
				return false;
			}
			return true;
		}
		@Override
		/**
		 * moves iterator forward
		 * @return element equal to the head element (if exception not thrown)
		 */
		public T next() throws NoSuchElementException {
			if(ip == null)
				throw new NoSuchElementException("iteration has no more elements at the end of the linked list");
			T temp = ip.element;
			ip = ip.next;
			return temp;
		}

		@Override
		/**
		 * throws UnsupportedOperationException
		 */
		public void remove() throws UnsupportedOperationException{
			throw new UnsupportedOperationException("Did not throw a UnsupportedOperationException");	
		}

	}
	protected Node<T> head;
	protected Node<T> tail;
	int size;
	protected ArrayList<T> aList;

	/**no-arg constructor*/
	public BasicLinkedList(){
		size = 0;
		head = null;
		tail = null;
	}
	
	/**
	 * Adds element to the end of the list
	 * @param data generic element
	 */
	public void addToEnd(T data) {
		Node<T> newNode = new Node<>(data, null);

		if(isEmpty()){
			head = newNode;
		}
		else{
			tail.setNext(newNode);
		}
		tail = newNode;
		size = size +1;		
	}

	/**
	 * checks size of list
	 * @return true if list is empty
	 */
	public boolean isEmpty() {
		if(size == 0){
			return true;
		}
		else
		return false;
	}

	/**
	 *  Adds element to the front of the list
	 *@param data generic element
	 */
	public void addToFront(T data){
		Node<T> tmp = new Node<T>(data);
		if(head == null){
			tmp.next = null;
			head = tmp;
			tail = tmp;
		}
		else {
			tmp.next = head;
			head = tmp;
		}
		size++;
	}

	/**
	 * retrieves the current size of the list 
	 * @return size current size of list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Returns but does not remove the last element from the list
	 * @return returns last element. If there are no elements the method returns null
	 */
	public T getLast() {
		if(isEmpty()){
			return null;
		}
		return tail.getElement() ;
	}

	/**
	 * Returns but does not remove the first element from the list
	 * @return returns first element. If there are no elements the method returns null
	 */
	public T getFirst() {
		if(isEmpty()){
			return null;
		}
		return head.getElement();
	}

	/**
	 * Returns an Arraylist of the items in the linked list
	 * @return an Arraylist of the items in the linked list
	 */
	public java.util.ArrayList<T> toArrayList() {
		aList = new ArrayList<T>();
		
		Node<T> h = head;
		while(h != tail){
			aList.add(h.element);
			h = h.next;				
		}
		aList.add(tail.getElement());
		return aList;
	}
	
	
	/**
	 * This method must be implemented using an inner class that implements Iterator
	 *  and defines the methods of the iterator (hasNext, next and remove)
	 *  @return create an instance of LinkedListIterator inner class
	 */
	public Iterator<T> iterator() throws java.lang.UnsupportedOperationException{
		return new LinkedListIterator();
	}

	/**
	 * Removes and returns the first element from the list
	 * @return removes and returns first element.If there are no elements the method returns null
	 */
	public T retrieveFirstElement() {
		T answer = null;
		if(head == null){
			answer = head.getElement();
		}
		else if(head == tail){
			head = null;
			tail = null;
			answer = null;
		}
		else{
			answer = head.getElement();
			head = head.next;
		}
		return answer;
	}

	/**
	 * Removes and returns the last element from the list
	 * @return removes and returns last element.If there are no elements the method returns null
	 */
	public T retrieveLastElement() {
		T answer = null;
		Node<T> previousNode;
		if(tail == head ){
			if(tail != null && head !=null){
				head = tail = null;
				answer = null;
			}
		}
		else if(tail == null){
			answer = null;
		}
		else{
			previousNode = head;
			while(!(previousNode.next == tail)){
				previousNode = previousNode.next;
			}
			tail = previousNode.next;
			answer = tail.getElement();
			tail = previousNode;

		}
		return answer;
	}
}//end of program
