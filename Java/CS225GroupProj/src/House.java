/**
 * Group Project: House (Model)
 * Due Date: 04/28/16
 * Professor: Franceschi
 * Description:  create a House class that will be a 
 * create a combo box, accessors, mutators, and other methods to check a user's  
 * Rental Class to be used in the MVC classes
 * 
 * @author Ebony Cross
 */
public class House {
	private String houseName = "null";
	private String description = "null";
	private boolean rented = false;
	private static String userNm = "null";
	private String customerNm = "null";

	/**
	 * default constructor 
	 */
	public House(){
		houseName = "null";
		description = "null";
		rented = false;
		customerNm = "null";
	}

	/**
	 * constructor 
	 * @param houseNm house name 
	 * @param desc description of house 
	 * @param rent check is house is reserved 
	 * @param custNm customer name that reservation is under 
	 */
	public House(String houseNm, String desc, boolean rent, String custNm){
		houseName = houseNm;
		description = desc;
		rented = rent;
		customerNm = custNm;

	}

	/**
	 * obtain house name 
	 * @return house name 
	 */
	public String getHouseName() {
		return houseName;
	}

	/**
	 * set house name 
	 * @param houseName house name 
	 */
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	/**
	 * retrieve description of house 
	 * @return string rep of descripton
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * set description of house 
	 * @param description description of house 
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * verify is house is rented (already reserved) by someone else 
	 * @return true is already reserve (meaning not available for rent)
	 */
	public boolean isRented() {
		return rented;
	}

	/**
	 * set verification of house reservation
	 * @param rented status of reservation
	 */
	public void setRented(boolean rented) {
		this.rented = rented;
	}

	/**
	 * obtain customer name 
	 * @return customer name 
	 */
	public String getCustomerNm() {
		return customerNm;
	}


	/**
	 * retrieve user name 
	 * @return username 
	 */
	public static String getUserNm() {
		return userNm;
	}

	/**
	 * set user name 
	 * @param userNm username 
	 */
	public static void setUserNm(String userNm) {
		House.userNm = userNm;
	}

	/**
	 * set customer name 
	 * @param c customer name 
	 */
	public void setCustomerNm(String c){
		if(isCustomerName(c)){
			customerNm = c;
		}
		else{
			customerNm = "null";
		}
	}

	/**
	 * check valid of string 
	 * @param name user/customer name 
	 * @return true is valid string 
	 */
	public boolean isCustomerName(String name){
		if(name.length() <= 0){
			return false;
		}
		else{
			return true;
		}
	}

	/**
	 * string rep of a house 
	 * @return string rep
	 */
	public String toString(){
		String print = "";
		print += getHouseName() + "," + getDescription() + "," + isRented() 
		+ "," + getCustomerNm() +"\n";

		return print;
	}


}//end of program 
