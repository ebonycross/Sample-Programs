/**
 * Assignment 3, Spring 2015
 * The ServiceException class holds information about format violations in Driver
 * @author E Cross
 *
 */
public class ServiceException extends Exception {

	public ServiceException(){}
	
	/**
	 * constructor
	 * @param message string representation of error message
	 */
	public ServiceException(String message){
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