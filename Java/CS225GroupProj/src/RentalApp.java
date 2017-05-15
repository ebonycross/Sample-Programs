import javafx.application.*; // Application library imports;

import java.io.File;
import java.net.URL;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.fxml.*;
/**
 * Group Project: RentApp (Application) Class
 * Due Date: 04/28/16
 * Professor: Franceschi
 * Description: use comboBox to show House Rental selected 
 * 
 * @author Ebony Cross
 */
public class RentalApp extends Application
{
	private Rental rentalMgr;

	public void start( Stage stage )
	{
		try
		{
			//initial model object instance
			//rentalMgr = new Rental();

			URL url = getClass().getClassLoader().getResource("fxml_scene1.xml");
			//System.out.println(url);
			VBox root = FXMLLoader.load( url );
			Scene scene = new Scene( root, 500, 600 );
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			// assign the scene to the stage object
			stage.setScene( scene );
			// set title of stage (optional)      
			stage.setTitle( "Rental Service Login" );

			// make the stage visible
			stage.show( );



		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}


	public static void main( String [] args )
	{
		launch( args );
	}

}//end of program

