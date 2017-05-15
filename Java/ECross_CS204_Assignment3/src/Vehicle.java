/**
 * Assignment 3, Spring 2015
 * The vehicle class holds information about a type of vehicle,  
 * @author E Cross
 *
 */
public class Vehicle {
	String name, model, make; //variables to create vehicle
	
	/**no-arg constructor*/
	public Vehicle(){
		name =null;
		make = null;
		model = null;
	}
	
	 
	/**
	 * constructor that takes in owner name, make, and model of car 
	 * @param n name of owner
	 * @param mak maker of car model
	 * @param type model of car 
	 * */
	public Vehicle(String n, String mak, String type){
		name = n;
		make = mak;
		model = type;
	}
	
	/**
	 * copy constructor
	 * @param otherVehicle copy of vehicle object
	 */
	public Vehicle(Vehicle otherVehicle){
		name = otherVehicle.name;
		make = otherVehicle.make;
		model = otherVehicle.model;
	}

	/**
	 * retrieve name of owner
	 * @return owner name
	 */
	public String getName() {
		return name;
	}

	/**
	 * retrieve model name
	 * @return model name 
	 */
	public String getModel() {
		return model;
	}

	/**
	 * retrieve make of car model
	 * @return make of car
	 */
	public String getMake() {
		return make;
	}

	/**
	 * set owner name 
	 * @param name of owner
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * retrieve model name
	 * @param model name 
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * set make of car model
	 * @param make car maker 
	 */
	public void setMake(String make) {
		this.make = make;
	}
	
	/**
	 * retrieve make and model of car 
	 * @return print string representation of make and model of car 
	 */
	public String getMakeAndModel(){
		String print = "";
		print += getMake() + " " + getModel();
		return print;
	}
	
	/**
	 * set make and model of car
	 * @param make car maker
	 * @param model car model 
	 */
	public void setMakeAndModel(String make, String model){
		String makeModel = "";
		makeModel += make + " " + model;
	}
	
	/**
	 * String representation of vehicle
	 * @return string variable print
	 */
	public String toString(){
		String print = "";
		print += getName() + " " + getMake() + " " + getModel();
		return print;
	}
}//end of program
