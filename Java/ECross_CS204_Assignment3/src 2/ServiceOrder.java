/**
 * Assignment 3, Spring 2015:
 * The ServiceOrder class holds holds an order number, a reference to a Vehicle object,
 *  and keeps track of the services requested 
 * @author E Cross
 *
 */
public class ServiceOrder implements ServiceOrderInterface{
	private Vehicle car;
	private int order;
	private String name, make, model, oil, safety, emissions;


	/**no-arg constructor*/
	public ServiceOrder(){
		car = new Vehicle();
		order = 0;
		name = make = model = oil = safety = emissions = null;
	}
	
	/**
	 * constructor
	 * @param orderNum service order number 
	 * @param n name of owner
	 * @param mak make of car
	 * @param type model of car
	 * @param o oil check
	 * @param s safety check
	 * @param e emission test check
	 */
	public ServiceOrder(int orderNum, String n, String mak, String type, String o, String s, String e){
		name = n;
		make = mak;
		model = type;
		car = new Vehicle(n,mak,type);
		order = orderNum;
		oil = o;
		safety = s;
		emissions = e;
	}
	/**
	 * copy constructor
	 * @param otherOrder object of ServiceOrder
	 */
	public ServiceOrder(ServiceOrder otherOrder){
		name = otherOrder.name;
		make = otherOrder.make;
		model = otherOrder.model;
		car = otherOrder.car;
		order = otherOrder.order;
		oil = otherOrder.oil;
		safety = otherOrder.safety;
		emissions = otherOrder.emissions;
	}


	/**
	 * constructor
	 * @param orderNum number of service order
	 * @param o oil service
	 * @param s safety service
	 * @param d vehicle
	 * @param e emissions service
	 */
	public ServiceOrder(int orderNum, Vehicle d, String o, String s, String e){
		car = d;
		order = orderNum;
		oil = o;
		safety = s;
		emissions = e;

	}



	/**
	 * Returns reference to Dog object
	 * @return reference to Dog object of Service Order
	 */
	public Vehicle getVehicle() {
		return car;
	}


	/**
	 * Returns the order number of the Service Order
	 * @return the order number of the Service Order
	 */
	public int getOrderNum(){
		return order;
	}

	/**
	 * Returns if shampoo service is requested 
	 * @return yes or no if shampoo service is requested
	 */
	public String getOil(){
		if(oil.equalsIgnoreCase("yes")){
			return "yes";
		}
		else
			return "no";
	}

	/**
	 * Returns if cut service is requested
	 * @return yes or no if cut service is requested
	 */
	public String getSafety(){
		if(safety.equalsIgnoreCase("yes")){
			return "yes";
		}
		else
			return "no";
	}

	/**
	 * Returns if nail trim is requested
	 * @return yes or no if nail trim is requested
	 */
	public String getEmissions(){
		if(emissions.equalsIgnoreCase("yes")){
			return "yes";
		}
		else
			return "no";
	}
	
	/**
	 * String representation of vehicle
	 * @return string variable print
	 */
	public String toString(){
		String print = "";
		print += "\n" +getOrderNum() + " " + getVehicle() + " " + getOil() +
				" " + getSafety() + " " + getEmissions() + "\n";
		return print;
		
	}
}//end of program



