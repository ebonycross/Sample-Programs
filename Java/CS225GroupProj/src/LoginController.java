import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import java.net.URL;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.fxml.*;

/**
 * Group Project: Scene I, LoginController Class (Controller)
 * Due Date: 04/28/16
 * Professor: Franceschi
 * Description: create a LoginController class that will action handle  
 * accessors, mutators, and other methods to change a user's  
 * word casing to be used in the MVC classes
 * 
 * @author Ebony Cross
 */
public class LoginController{  
	@FXML private TextField user;
	@FXML private Button loginBtn; //button for user to login after verification
	@FXML private Button exitBtn;
	private House h = new House();
	private String username;

	/**
	 * initialize controller
	 */
	public void initialize(){
		user.setText("");
	}

	/**
	 * onKeyPressed listener for to check login
	 * @param e event listener for textfield
	 */
	@FXML protected void loginTxt( KeyEvent e )
	{ 
		h = new House();
		if(e.getCode() == KeyCode.ENTER){


			if((h.isCustomerName(user.getText()))){
				loginBtn.setDisable(false);
				House.setUserNm(user.getText());
				username = House.getUserNm();
				//System.out.println("Username:" + username);
			}
			else{
				loginBtn.setDisable(true);
			}
		}
	}//end of method

	/**
	 * Action listener for to check login
	 * @param e event listener for textfield
	 */
	@FXML protected void login( ActionEvent e )
	{

		if(e.getSource() == loginBtn){
			h = new House();

			//System.out.println("Inside button listener, Username is:" + username);

			if(username != null){
				try
				{
					URL url = 
							getClass().getClassLoader().getResource("fxml_scene2.xml");
					//System.out.println(url);
					FXMLLoader f = new FXMLLoader(url);

					BorderPane root = f.load();
					//System.out.println("loaded");
					Scene scene = new Scene( root, 700, 600 );
					scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
					// assign the scene to the stage object
					Stage stage = new Stage( );
					stage.setScene( scene );
					// set title of stage (optional)      
					stage.setTitle( "Rental Service" );

					// make the stage visible
					stage.show( ); 

				}
				catch( Exception e1 )
				{
					e1.printStackTrace();
				}
			}
			else{
				user.setText("invalid. Re-Enter username"); 
			}
		}

	}//end of method


	/**
	 * Action listener for to exit
	 * @param e event listener for exitBtn and textfield
	 */
	@FXML protected void exit(ActionEvent e){
		if(e.getSource() == exitBtn){

			System.exit(0); //terminate program

		}
	}


}//end of program

