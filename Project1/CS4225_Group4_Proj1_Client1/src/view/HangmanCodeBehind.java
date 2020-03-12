package view;

import java.util.ArrayList;
import java.util.List;
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
import util.ClientActions;
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
    private Button btnGuess;

    @FXML
    private TextField txtBoxChatBox;

    @FXML
    private ComboBox<String> guessComboBox;


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
		
		this.btnGuess.setDisable(true);
		this.btnQuit.setDisable(true);
		
		this.guessComboBox.itemsProperty().set(FXCollections.observableArrayList(new ArrayList<String>()));		
   		this.guessComboBox.getItems().addAll("A", "B", "C",
				"D", "E", "F", "G", "H", "I", "J", "K", "L",
				"M", "N", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z");
   		
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
		
		this.btnGuess.setDisable(false);
		this.btnQuit.setDisable(false);
		this.guessComboBox.setDisable(false);
		
	}
	
    @FXML
    void btnQuit_Click(ActionEvent event) {
    	this.viewmodel.sendMessage(ClientActions.QUIT, "");
    	System.exit(0);
    }

    @FXML
    void btnGuess_Click(ActionEvent event) {
    	this.viewmodel.sendMessage(ClientActions.GUESS,txtBoxChatBox.getText());
    }
    
    @FXML
    void btnUsername_Click(ActionEvent event) {
    	HangmanViewModel.USERNAME = this.userName;
    	this.userName = txtBoxUserName.getText();
    	this.viewmodel.sendMessage(ClientActions.LOGIN,this.userName);
    }
    
    public void removeLetter(String letter) {
    	this.guessComboBox.getItems().remove(letter);
    }

}
