package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

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
	private void initialize() {
		this.head.setVisible(false);
		this.body.setVisible(false);
		this.rightArm.setVisible(false);
		this.leftArm.setVisible(false);
		this.rigthLeg.setVisible(false);
		this.leftLeg.setVisible(false);
	}
	
    @FXML
    void btnQuit_Click(ActionEvent event) {

    }

    @FXML
    void btnSubmit_Click(ActionEvent event) {

    }

}
