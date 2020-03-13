package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * @author jsmit124, Aaron Merrell, Mlynn Jenkins
 *
 * @version 1.0
 */
public class Main extends Application {
	private static final String WINDOW_TITLE = "Hangman by M'lynn Jenkins, Aaron Merrell, and Justin Smith";
	private static final String GUI_FXML = "/view/Hangman.fxml";
	
	/**
	 * Main entry point for the program
	 * 
	 * @precondition none
     * @postcondition none
     * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(GUI_FXML));
			Scene scene = new Scene((Pane) loader.load());
			primaryStage.setScene(scene);
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.setAlwaysOnTop(true);
			primaryStage.show();
		} catch (IllegalStateException | IOException anException) {
			anException.printStackTrace();
		}
	}

}
