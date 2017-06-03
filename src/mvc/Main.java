package mvc;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


/**
* The Calculator program implements an application that does basic
* calculations.
*
* @author  Ricardo Matos
* @version 1.0
* @since   03-06-2017
*/


public class Main extends Application {
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("UI.fxml"));				//Must be same package
			loader.setControllerFactory(t -> new Controller(new Model()));						//Binds fxml>controller>model
			
			primaryStage.setScene(new Scene(loader.load()));
			primaryStage.getScene().getStylesheets().add("/mvc/application.css");
			primaryStage.setResizable(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
