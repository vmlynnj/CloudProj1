package view;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import viewmodel.HangmanViewModel;

public class HangmanCodeBehind {

    @FXML
    private Circle head;

    @FXML
    private Line body;

    @FXML
    private Line leftArm;

    @FXML
    private Line rightArm;

    @FXML
    private Line leftLeg;

    @FXML
    private Line rigthLeg;

    @FXML
    private ListView<?> chatView;

    @FXML
    private Button btnQuit;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField txtBoxChatBox;

    @FXML
    private ComboBox<?> guessComboBox;

    @FXML
    private Button submitGuessButton;
    
    private HangmanViewModel viewmodel;

    
	@FXML
	private void initialize() {
		this.viewmodel = new HangmanViewModel();
	}
	
    @FXML
    void btnQuit_Click(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void btnSubmit_Click(ActionEvent event) {
    	this.viewmodel.sendChat(txtBoxChatBox.getText());
    }
    
    @FXML
    void btnGuess_Click(ActionEvent event) {
    	this.viewmodel.sendGuess(guessComboBox.getSelectionModel());
    }

}
