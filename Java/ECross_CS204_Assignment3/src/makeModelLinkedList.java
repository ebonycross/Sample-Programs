import java.util.LinkedList;
import java.util.ListIterator;


public class makeModelLinkedList extends LinkedList<ServiceOrder> {
	ServiceOrder orderTmp; 


	/**
	 * no-arg constructor
	 */
	public makeModelLinkedList(){
		super();
		orderTmp = new ServiceOrder();
	}

	/**
	 * add service order to list in numerical order 
	 * @param order object of ServiceOrder type
	 * @return true is successful added numerically and false if not 
	 */
	public boolean add(ServiceOrder order){
		ListIterator<ServiceOrder>i = this.listIterator();
		boolean flag = false;
		if(this.isEmpty()){
			i.add(order);
			flag = true;
			return flag;
		}

		while(i.hasNext()){
			orderTmp = i.next();
			if(orderTmp.getVehicle().getMakeAndModel().compareTo(order.getVehicle().getMakeAndModel()) > 0){
				i.previous();
				i.add(order);
				flag  = true;
				return flag;
			}
		}
		i.add(order);
		return true;

	}

	/**
	 * remove existing service order depending on the order number
	 * @param orderNum order number for the service order 
	 * @return ServiceOrder object
	 */
	public ServiceOrder remove(int orderNum){
		ListIterator<ServiceOrder>i =this.listIterator();
		boolean flag = false;

		if(this.isEmpty()){
			flag = true;
		}

		while(i.hasNext()){		
			orderTmp = i.next();
			if(orderTmp.getOrderNum() == orderNum){
				i.previous();
				i.remove();
				flag  = true;
			}
		}

		return orderTmp;
	}


	/**
	 * create 2d string array for display existing service order sort by order number 
	 * @return 2d string array
	 */
	public String[][]  JTableData(){
		String[][] s = new String[this.size()][7];

		ServiceOrder order;
		Vehicle v;
		int index = 0;
		ListIterator<ServiceOrder>i = this.listIterator();
		while(i.hasNext()){

			order = i.next();	
			v = order.getVehicle();
			//i.next();
			s[index][0] = order.getVehicle().getMake(); 
			s[index][1] = order.getVehicle().getModel();
			s[index][2] = order.getVehicle().getName();
			s[index][3] = order.getOrderNum() + "";
			s[index][4] = order.getOil();
			s[index][5] = order.getSafety();
			s[index][6] = order.getEmissions();
			index++;
		}

		return s;
	}




}//end of program








