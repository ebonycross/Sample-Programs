/**
 * Assignment 1, Spring 2015
 * The IllegalNumFormatException class holds information about format violations in Driver
 * @author E Cross
 *
 */
public class IllegalNumFormatException extends Exception{
	
	/**
	 * no-argument constructor
	 */
	public IllegalNumFormatException(){}
	
	/**
	 * constructor
	 * @param message string representation of error message
	 */
	public IllegalNumFormatException(String message){
		super(message);
	}
	
	/**
	 * get string message 
	 * @return string message from super 
	 */
	public String getMessage(){
		return super.getMessage();
	}

}
