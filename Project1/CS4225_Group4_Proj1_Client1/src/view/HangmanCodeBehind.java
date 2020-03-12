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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import util.EnglishAlphabet;
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
    private ComboBox<EnglishAlphabet> guessComboBox;

    @FXML
    private Button submitGuessButton;

    @FXML
    private Label lblWord;
    
    @FXML
    private Label lblusernameError;
    
    @FXML
    private TextField txtBoxUserName;
    
    @FXML
    private Button btnUsername;
    
    private String userName;
    
    private HangmanViewModel viewmodel;

    
    protected ListProperty<String> listProperty = new SimpleListProperty<>();
    
    protected List<String> messages = new ArrayList<String>();
    
	@FXML
	private void initialize() {
		listProperty.set(FXCollections.observableArrayList(this.messages));
		chatView.itemsProperty().bind(listProperty);
		this.viewmodel = new HangmanViewModel(this.listProperty, this);
		
		this.btnSubmit.setDisable(true);
		this.btnQuit.setDisable(true);
		

		this.guessComboBox.setDisable(true);
		
		
	}
	
	public void userNameError() {
		this.txtBoxUserName.clear();
		this.lblusernameError.setVisible(true);
	}
	
	public void startGame() {
		this.txtBoxUserName.setDisable(true);
		this.btnUsername.setDisable(true);
		this.lblusernameError.setVisible(false);
		
		this.btnSubmit.setDisable(false);
		this.btnQuit.setDisable(false);
		this.guessComboBox.setDisable(false);
		
		this.guessComboBox = new ComboBox<EnglishAlphabet>();
		this.guessComboBox.setItems( FXCollections.observableArrayList( EnglishAlphabet.values()));
	}
	
	
    @FXML
    void btnQuit_Click(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void btnSubmit_Click(ActionEvent event) {
    	this.viewmodel.sendMessage(txtBoxChatBox.getText());
    	this.txtBoxChatBox.setText("");
    }
    
    @FXML
    void btnGuess_Click(ActionEvent event) {
    	this.viewmodel.sendMessage(guessComboBox.getSelectionModel().toString());
    }
    

    @FXML
    void btnUsername_Click(ActionEvent event) {
    	HangmanViewModel.USERNAME = this.userName;
    	this.userName = txtBoxUserName.getText();
    	this.viewmodel.sendMessage(this.userName);
    }

}
