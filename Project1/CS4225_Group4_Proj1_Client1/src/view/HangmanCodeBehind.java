package view;

import java.util.ArrayList;
import java.util.List;

import client.Client;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
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
    private ListView<String> chatView;

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

    
    protected ListProperty<String> listProperty = new SimpleListProperty<>();
    
    protected List<String> messages = new ArrayList<String>();
    
	@FXML
	private void initialize() {
		this.messages.add("code behind message");
		listProperty.set(FXCollections.observableArrayList(this.messages));
		chatView.itemsProperty().bind(listProperty);
		this.viewmodel = new HangmanViewModel(this.listProperty);
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
