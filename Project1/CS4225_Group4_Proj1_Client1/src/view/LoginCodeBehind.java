package view;

import java.io.IOException;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginCodeBehind {

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField txtBoxUsername;

	
	@FXML
	private void initialize() {
		
	}
    @FXML
    void btnSubmit_Click(ActionEvent event) {
    	Client client = new Client();
    	//client.startConnection();
    	Stage stage = (Stage) this.btnSubmit.getScene().getWindow();
        try {
			Parent root = FXMLLoader.load(getClass().getResource("Hangman.fxml"));
			Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}