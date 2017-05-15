import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Vector;

/**Assignment 3, Spring 2015: ServiceOrdersManager (Data Manager)
 * Implements ServiceOrdersManagerInterface interface and its methods.As the data manager, 
 * computations and methods for Driver class are created.
 * Contains reference objects for all three ordered linked lists.
 * 
 * @author Ebony Cross
 * 
 */
public class ServiceOrdersManager implements ServiceOrdersManagerInterface {
	private OrderLinkedList orderNumList;
	private ownerLinkedList ownerList;
	private makeModelLinkedList makeModelList;
	private ServiceOrder orders;
	private ListIterator<ServiceOrder> iOrder; 
	//private ArrayList<ServiceOrder> alist = new ArrayList<ServiceOrder>();

	/**
	 * no-arg constructor
	 */
	public ServiceOrdersManager(){
		orderNumList = new OrderLinkedList();
		ownerList = new ownerLinkedList();
		makeModelList = new makeModelLinkedList();
	}

	/**
	 * Creates a Service Order object and adds it to the ordered linked lists
	 * @param orderNum Service Order Number
	 * @param owner name of owner of car to be serviced
	 * @param make make of car to be serviced
	 * @param model model of car to be serviced
	 * @param oilChange check oil
	 * @param safetyCheck safety check
	 * @param emissionsCheck emission test check
	 * @return true if the startService was successful
	 * @throws ServiceException if Service Order Number is already in use
	 */
	public boolean startService(int orderNum, String owner, String make, String model, String oilChange,String safetyCheck, String emissionsCheck) throws ServiceException 
	{
		boolean success = true;

		if(findOrderNum(orderNum) !=null){
			throw new ServiceException("Order Already in use: ServiceOrderInUseException thrown");
		}
		orders = new ServiceOrder(orderNum, owner, make, model,oilChange, safetyCheck, emissionsCheck);
		orderNumList.add(orders);
		ownerList.add(orders);
		makeModelList.add(orders);

		return success;
	}

	/**
	 * Adds a Service Order to the ordered linked lists.  This is for testing purposes.  
	 * Not intended to be used by the user
	 * @param order a Service Order to be added to the ordered linked lists.
	 * @return true if the startService was successful
	 * @throws ServiceException if Service Order Number is already in use
	 */
	public boolean startService(ServiceOrder order) throws ServiceException{
		boolean success = true;
		if(findOrderNum(order.getOrderNum()) !=null){
			throw new ServiceException("Order Already in use: ServiceOrderInUseException thrown");
		}
		orderNumList.add(order);
		ownerList.add(order);
		makeModelList.add(order);
		
		return success;
	}

	/**
	 * Removes the Service Order from the ordered linked lists
	 * @param orderNum Service Order Number
	 * @return true if the finishService is successful
	 * @throws ServiceException if the Service Order Number is not found
	 */
	public boolean finishService(int orderNum) throws ServiceException{
		boolean success = true;
		if(findOrderNum(orderNum) == null){
			success = false;
			throw new ServiceException("Order not Found: ServiceOrderNotFoundException thrown");
		}
		else{
			orderNumList.remove(orderNum);
			ownerList.remove(orderNum);
			makeModelList.remove(orderNum);
		}
		return success;

		}

		/**
		 * Returns a two dimensional array of Strings to populate a JTable
		 * @param key, how service orders are to be ordered 1 = service order number, 2 = owner name (last, first), 
		 * 3 = model
		 * @return a two dimensional array of Strings to populate a JTable
		 * order for key 1 = order num, owner, make, model, oilChange, safetyCheck, emissionsCheck
		 * order for key 2 = owner, order num, make, model, oilChange, safetyCheck, emissionsCheck
		 * order for key 3 = model, owner, make, order num, oilChange, safetyCheck, emissionsCheck
		 */
		public String[][] listByKeyTable(int key){
			String list[][]  = new String[20][7];
			if(key == 1){
				list = orderNumList.JTableData();
				
			}
			else if(key == 2){
				list = ownerList.JTableData();
			}
			else if(key == 3){
				list = makeModelList.JTableData();
			}
			return list;
		}

		/**
		 * Returns a Vector of Strings to populate a JList
		 * @param key how service orders are to be ordered 1 = service order number, 2 = owner name (last, first), 
		 * 3 = model
		 * @return a Vector of Strings to populate a JList
		 * order for key 1 = order num, owner, make, model, oilChange, safetyCheck, emissionsCheck
		 * order for key 2 = owner, order num, make, model, oilChange, safetyCheck, emissionsCheck
		 * order for key 3 = make, model, owner, order num, oilChange, safetyCheck, emissionsCheck
		 */
		public Vector<String> listByKeyVector(int key){
			return null;
		}

		/**
		 * Returns the reference to the order number if found or null if not found
		 * @param orderNum the order number to look for
		 * @return the reference to the service order object or null if not found
		 */
		public ServiceOrder findOrderNum(int orderNum){
			iOrder = orderNumList.listIterator();
			while(iOrder.hasNext()){
				orders = iOrder.next();
				if(orderNum == orders.getOrderNum()){
					return orders;
				}	
			}
			return null;
		}

		/**
		 * Returns a string with all the current Service Orders in the following format:
		 * 
		 *  Line	Information
		 *  1		Order #  (integer)
		 *  2		Owner (last name, first name) (String)
		 *  3		make (String)
		 *  4		model (String)
		 *  5		oilChange (String) either "yes" or "no"
		 *  6		safetyCheck (String) either "yes" or "no"
		 *  7		emissionsCheck (String) either "yes" or "no"
		 *  ** No blank lines between Service Orders
		 * @return a String that can be used with a PrintWriter object to write to a file.
		 */
		public String toString(){
			iOrder = orderNumList.listIterator();
			String print = "\n";
			while(iOrder.hasNext()){
				orders=iOrder.next();
				
				print += orders.getOrderNum() +"\n" + orders.getVehicle().getName() + "\n" + 
						orders.getVehicle().getMake() + "\n" + orders.getVehicle().getModel() + "\n"
						+ orders.getOil() + " "+ orders.getSafety() + " " + orders.getEmissions() + "\n";
			}	
			return print;
		}
	}//end of program
