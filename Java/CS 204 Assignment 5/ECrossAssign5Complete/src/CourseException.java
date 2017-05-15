/**
	 * Assignment 5, Spring 2015
	 * The CoruseException class holds information about format violations in Driver
	 * @author E Cross
	 *
	 */
public class CourseException extends Exception {

	
	private static final long serialVersionUID = 1L;

		public CourseException(){}
		
		/**
		 * constructor
		 * @param message string representation of error message
		 */
		public CourseException(String message){
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

