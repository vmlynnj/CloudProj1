package application;
	
import java.awt.BorderLayout;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	private static final String WINDOW_TITLE = "Hangman";
	private static final String GUI_FXML = "./view/Login.fxml";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Login.fxml"));
			Scene scene = new Scene((Pane) loader.load());
			primaryStage.setScene(scene);
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.setAlwaysOnTop(true);
			primaryStage.show();
		} catch (IllegalStateException | IOException anException) {
			anException.printStackTrace();
		}
	}
	
	/**
	 * Loads the GUI from the given FXML file.
	 * 
	 * @return the pane to be loaded
	 * @throws IOException
	 */
	private Pane loadGui() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("./view/Login.fxml"));
		return (Pane) loader.load();
	}
	public static void main(String[] args) {
		launch(args);
	}
}