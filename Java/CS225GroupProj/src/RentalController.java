import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.FileChooser;

/**
 * Group Project: Scene II, RentalController Class (Controller)
 * Due Date: 04/28/16
 * Professor: Franceschi
 * Description: create a LoginController class that will action handle  
 * accessors, mutators, and other methods to change a user's  
 * word casing to be used in the MVC classes
 * 
 * @author Ebony Cross
 */
public class RentalController {
	//declare and initialize variables
	private Rental rent;
	@FXML private Button rentBtn;
	@FXML private Button exitBtn;
	@FXML private Label label;
	@FXML private Label info;
	@FXML private Label result;
	@FXML private Label text;
	@FXML private ImageView houseImage;
	@FXML private ComboBox<String> list;
	private SingleSelectionModel<String> houseSelectionModel;
	private House h;


	/**
	 * initialize controller
	 */
	public void initialize(){
		rent = new Rental();

		String s = label.getText() + " " + House.getUserNm();
		//System.out.println("username still is " + s);
		label.setText(s);

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		//fileChooser.showOpenDialog(stage);

		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			rent.readFile(file);
			//System.out.println("good job");
		}
		else{
			System.out.println("Error");
		}

		//System.out.println("Rent: " + rent.toString());

		//populate combo with data from the Model "UnixPermission"
		list.getItems().addAll(rent.houseList());

		//get a reference to the SingleSelectionModel
		houseSelectionModel = list.getSelectionModel();

		//initialize View "xml file" with initial data from Model
		houseSelectionModel.select(rent.getChoosenIndex());
		houseImage.setImage(rent.getSelectedImage());
	} //end of method 

	/**
	 * Action listener for selected combo boxes 
	 * @param e event listener for the 1 combo boxes and textfield
	 */
	@FXML protected void levelSelected(ActionEvent e){

		//retrieve index of permission selected
		int houseIndex = houseSelectionModel.getSelectedIndex();

		//update the Model
		rent.setChoosenIndex(houseIndex);

		result.setText(rent.printHouse(houseIndex));
		houseImage.setImage(rent.getSelectedImage());
	}


	/**
	 * Action listener for to check is house select is avaible 
	 * @param e event listener for the 1 rentBtn and textfield
	 */
	@FXML protected void reserve(ActionEvent e){
		if(e.getSource() == rentBtn){
			//System.out.println("index is: " + rent.getChoosenIndex());

			result.setText(rent.reserveHouse(rent.getChoosenIndex()));



		}
	}

	/**
	 * Action listener for exiting program
	 * @param e event listener for the 1 exitBtn 
	 */
	@FXML protected void exit(ActionEvent e){
		if(e.getSource() == exitBtn){
			rent.writeFile();
			System.exit(0); //terminate program

		}
	}

}//end of program


