import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;

/**
 * Group Project: Rental (Model)
 * Due Date: 04/28/16
 * Professor: Franceschi
 * Description:  create a Rental class that will be a 
 * create a combo box, accessors, mutators, and other methods to check a user's  
 * RentalController Class to be used in the MVC classes
 * 
 * @author Ebony Cross
 */
public class Rental {
	private ArrayList<House> housing; //create arraylist of House objects
	private static int numRented;
	private String[] line;
	private Image [] houseImg = new Image[3];
	private Image img1,img2,img3;
	private File f;
	private String parentPath;
	private String custNm;
	private House h;
	private int choosenIndex = 0;

	/**
	 * default constructor 
	 */
	public Rental(){
		housing = new ArrayList<House>();
		//System.out.println("hello");
		//img1 = new Image(getClass().getResource("PyeongchangSmall.jpg"));
		//img1 = new Image(getClass().getResource("house0.jpg")) ;
		houseImg[0] = new Image("house0.jpg",300, 200, false, false);
		houseImg[1]  = new Image("house1.jpg", 300, 200, false, false);
		houseImg[2]  = new Image("house2.jpg", 300, 200, false, false);
		//System.out.println("images loaded");
	}

	/**
	 * get array of image of chooseIndex
	 * @return get array
	 */
	public Image getSelectedImage(){
		return houseImg[choosenIndex];
	}

	/**
	 * get number of houses rented 
	 * @return number of houses
	 */
	public static int getNumRented(){
		return numRented;
	}

	/**
	 * add house object to arraylist 
	 * @param hName house name 
	 * @param des description of house 
	 * @param stat status of reservation
	 * @param cName customer name 
	 */
	public void addHouse(String hName, String des, boolean stat, String cName ){
		House home = new House(hName,des,stat,cName);
		housing.add(home);
		numRented++;
	}

	/**
	 * get array of Images
	 * @return array
	 */
	public Image [] imageList(){
		return houseImg;
	}

	//list of house names for combobox
	/**
	 * get array of housing names
	 * @return list of house obj names 
	 */
	public String [] houseList(){
		String [] list = new String[housing.size()];

		for(int i = 0; i < housing.size(); i++){
			list[i] = housing.get(i).getHouseName();
			//System.out.println("item " + (i+1) + ": " +list[i]);
		}
		return list;
	}

	/**
	 * check is house is already reserved 
	 * @param index index of arraylist obj
	 * @return output of status 
	 */
	public String checkBooking(int index){
		if(housing.get(index).isRented()){
			return "reserved";
		}
		else if(housing.get(index).isRented() == false){
			return "not reserved";
		}
		return "invalid";
	}

	/**
	 * reserve selected house
	 * @param index index of arraylist obj
	 * @return string rep
	 */
	public String reserveHouse(int index){
		if(housing.get(index).isRented()){
			return "Sorry...House is already reserved";
		}
		else if(housing.get(index).isRented() == false){
			custNm = House.getUserNm();
			housing.get(index).setCustomerNm(custNm);
			housing.get(index).setRented(true);
			return "Reservation has been successful";
		}
		return "invalid";
	}

	/**
	 * string rep of all listings 
	 * @return string rep
	 */
	public String toString(){
		String show ="";

		for(int i = 0; i < housing.size(); i++){
			show += housing.get(i).toString() + "\n";
		}
		System.out.println(show);
		return show;
	} //end of toString method 

	/**
	 * string rep of array at index
	 * @param index index of arraylist
	 * @return string output
	 */
	public String printHouse(int index){
		String show ="";
		show += "Description: " + housing.get(index).getDescription() +"\n";
		show += "Customer Name: " + housing.get(index).getCustomerNm() + "\n";
		show += "Status: " + checkBooking(index) + "\n";

		//System.out.println(show);
		return show;
	} //end of toString method 



	@SuppressWarnings("resource")
	/**
	 * read objects in from file 
	 * @param file file object
	 */
	public void readFile(File file){

		//read in file
		try{

			Scanner input = new Scanner (file);
			String divide;
			/*
				String h = "null";
				String d = "null";

				String nm = "null";
			 */
			boolean state = false;

			while(input.hasNext()){
				divide = input.nextLine();


				line = divide.split(","); //parse textfile by the commas ","

				if(line[2].equals("true")){
					state = true;
				}else{
					state = false;
				}
				/*
				System.out.println(line.length);
				System.out.println(line[0]);
				System.out.println(line[1]);
				System.out.println(line[2]);
				System.out.println(line[3]);
				 */
				//add house object to ArrayList of datatype "House"
				//parmeter(house Name, house description, boolean rental status, username)
				addHouse(line[0], line[1], state, line[3]);
				//System.out.println("adding text");

			}

		}

		catch (FileNotFoundException e1){
			System.out.println("error");
			e1.printStackTrace();
		}	
		parentPath = file.getAbsolutePath();
		//System.out.println("file path: " + parentPath);
	}

	/**
	 * wrinte objects to file 
	 */
	public void writeFile(){	
		try {
			File newFile = new File(parentPath);

			PrintWriter outputFile = new PrintWriter(newFile);
			//System.out.println("array size: " + housing.size());
			for(int i = 0; i < housing.size(); i++){
				outputFile.write(housing.get(i).toString());
				//System.out.println("Housing:" + housing.get(i).toString());
			}

			outputFile.close();
		}

		catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * retrieve selected index
	 * @return choosen index
	 */
	public int getChoosenIndex(){
		return choosenIndex;
	}

	/**
	 * set selected index
	 * @param index new index for array of Strings
	 */
	public void setChoosenIndex(int index){
		choosenIndex = index;
	}

}//end of program
