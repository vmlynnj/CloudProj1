package view;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    	client.startConnection();
    }

}
