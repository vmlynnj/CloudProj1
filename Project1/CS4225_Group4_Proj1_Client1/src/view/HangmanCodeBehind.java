package view;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
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
import javafx.scene.text.Text;
import util.ClientActions;
import viewmodel.HangmanViewModel;

/**
 * View for hangman
 * @author Victoria Jenkisn, Justin Smith, Aaron Merrell
 *
 */
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
    private Line rightLeg;

    @FXML
    private Text deadLeftEye;

    @FXML
    private Text deadRightEye;

    @FXML
    private Line deadMouth;
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
    private Label lblLost;

    @FXML
    private Label lblWin;
    @FXML
    private Label lblTurn;

    @FXML
    private Label lblFullRoom;
    @FXML
    private Label lblGameStarted;
    
    @FXML
    private Label lblWord;
    
    @FXML
    private Label lblusernameError;
    
    @FXML
    private TextField txtBoxUserName;
    
    @FXML
    private Button btnUsername;
    
    @FXML
    private Button btnRetry;
    
    @FXML
    private Label lblWarning;

    @FXML
    private Label lblRemvoed;
    
    private String userName;
    
    private HangmanViewModel viewmodel;
    
    private ListProperty<String> listProperty = new SimpleListProperty<>();
    
    private List<String> messages = new ArrayList<String>();
    
	@FXML
	private void initialize() {
		this.listProperty.set(FXCollections.observableArrayList(this.messages));
		this.chatView.itemsProperty().bind(this.listProperty);
		this.viewmodel = new HangmanViewModel(this.listProperty, this);
		
		this.btnGuess.setDisable(true);
		
		this.guessComboBox.itemsProperty().set(FXCollections.observableArrayList(new ArrayList<String>()));		
   		this.guessComboBox.getItems().addAll("A", "B", "C",
				"D", "E", "F", "G", "H", "I", "J", "K", "L",
				"M", "N", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z");
   		
   		this.guessComboBox.setDisable(true);
   		this.lblWord.setText("Welcome to Hangman by M'lynn, Aaron, and Justin. Please wait for your game to begin.");
   		this.btnRetry.setDisable(true);
	}
	
	/**
	 * Handles name error.
	 */
	public void userNameError() {
		this.txtBoxUserName.clear();
		this.lblusernameError.setVisible(true);
	}
	
	/**
	 * Handles start game.
	 */
	public void startGame() {

		this.btnGuess.setDisable(false);
		this.guessComboBox.setDisable(false);
		this.disableUntilTurn();
		
	}
	
	/**
	 * Handles login.
	 */
	public void login() {
		this.txtBoxUserName.setDisable(true);
		this.btnUsername.setDisable(true);
		this.lblusernameError.setVisible(false);
		
		this.disableUntilTurn();
	}
	
	/**
	 * Handles full room.
	 */
    public void fullRoom() {
    	this.lblFullRoom.setVisible(true);
    	this.disableUntilTurn();
    	this.login();
    }
    
    /**
     * Handles game started.
     */
    public void gameStarted() {
    	this.lblGameStarted.setVisible(true);
    	this.disableUntilTurn();
    }
	
    @FXML
    void btnQuitClick(ActionEvent event) {
    	this.viewmodel.sendMessage(ClientActions.QUIT, " quit");
    	System.exit(0);
    }

    @FXML
    void btnGuessClick(ActionEvent event) {
    	HangmanViewModel.setTakenTurn(true);
    	System.out.println(ClientActions.GUESS + "=" + this.guessComboBox.getValue());
    	this.viewmodel.sendGuess(this.guessComboBox.getValue());
    	this.disableUntilTurn();
    }
    
    @FXML
    void btnUsernameClick(ActionEvent event) {
    	HangmanViewModel.setUsername(this.userName);
    	this.userName = this.txtBoxUserName.getText();
    	this.viewmodel.sendMessage(ClientActions.LOGIN, this.userName);
    }
    
    @FXML
    void btnRetryClick(ActionEvent event) {
    	HangmanViewModel.retry();
		this.txtBoxUserName.setDisable(false);
		this.btnUsername.setDisable(false);
		this.lblusernameError.setVisible(false);
		this.clearLastGame();
		this.disableUntilTurn();
		this.btnRetry.setDisable(true);
    }
    
    /**
     * Removes a letter from the combo box.
     * 
     * @param letter The leter to remove.
     */
    public void removeLetter(String letter) {
    	this.guessComboBox.getItems().remove(letter);
    	this.guessComboBox.valueProperty().set(null);
    }
    
    /**
     * Disable UI until the players turn.
     */
    public void disableUntilTurn() {
    	this.lblTurn.setVisible(false);
    	this.btnGuess.setDisable(true);
		this.guessComboBox.setDisable(true);
		HangmanViewModel.setTakenTurn(false);
    }
    
    /**
     * Enables UI for the player turn.
     */
    public void enableTurn() {
    	this.lblTurn.setVisible(true);
		this.btnGuess.setDisable(false);
		this.guessComboBox.setDisable(false);
    }
    
    /**
     * Adds a message to the UI.
     * 
     * @param message The message to add.
     */
    public void addMessage(String message) {
    	this.listProperty.add(message);
    }
    
    /**
     * Handles game lost for the UI.
     */
    public void gameLost() {
    	this.showHead();
    	this.showBody();
    	this.showLeftLeg();
    	this.showRightLeg();
    	this.showLeftArm();
    	this.showRightArm();
    	
    	this.lblLost.setVisible(true);
    	this.disableUntilTurn();
    	this.deadLeftEye.setVisible(true);
    	this.deadRightEye.setVisible(true);
    	this.deadMouth.setVisible(true);
    	
    	this.btnRetry.setDisable(false);
    }

    /**
     * Handles game won for the UI.
     */
    public void gameWon() {
    	this.lblWin.setVisible(true);
    	this.disableUntilTurn();
    	this.btnRetry.setDisable(false);
    }
    
    /**
     * Updates the word to guess.
     * 
     * @param word The word to update.
     */
    public void updateWord(String word) {
		Runnable task = () -> {
			Platform.runLater(() ->
			  this.lblWord.setText(word));
			System.out.println("LABEL WORD IS: " + this.lblWord.getText());
		};
		
		ExecutorService service = Executors.newSingleThreadExecutor();
		service.execute(task);
		service.shutdown();
    }
    
    /**
     * Shows the hangman's head.
     */
    public void showHead() {
		this.head.setVisible(true);
	}

    /**
     * Shows the hangman's body.
     */
	public void showBody() {
		this.body.setVisible(true);
	}

	/**
     * Shows the hangman's left leg.
     */
	public void showLeftLeg() {
		this.leftLeg.setVisible(true);
	}

	/**
     * Shows the hangman's right leg.
     */
	public void showRightLeg() {
		this.rightLeg.setVisible(true);
	}

	/**
     * Shows the hangman's left arm.
     */
	public void showLeftArm() {
		this.leftArm.setVisible(true);
	}

	/**
     * Shows the hangman's right arm.
     */
	public void showRightArm() {
		this.rightArm.setVisible(true);
	}
	
	private void clearLastGame() {
		this.head.setVisible(false);
		this.body.setVisible(false);
		this.leftLeg.setVisible(false);
		this.rightLeg.setVisible(false);
		this.leftArm.setVisible(false);
		this.rightArm.setVisible(false);
		this.deadLeftEye.setVisible(false);
		this.deadRightEye.setVisible(false);
		this.deadMouth.setVisible(false);
		this.listProperty.clear();
		this.lblLost.setVisible(false);
		this.lblWin.setVisible(false);
		this.lblWord.setVisible(false);
	}
	
	/**
	 * Shows the warning label.
	 */
	public void showWarning() {
		this.lblWarning.setVisible(true);
	}
	
	/**
	 * Show remove label.
	 */
	public void showRemove() {
		this.lblRemvoed.setVisible(true);
		this.disableUntilTurn();
	}

}
