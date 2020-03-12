/**
 * 
 */
package viewmodel;

import java.io.IOException;
import java.util.List;

import client.Client;
import client.WriteThread;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.SingleSelectionModel;
import view.HangmanCodeBehind;

/**
 * @author jsmit124
 *
 * @version 1.0
 */
public class HangmanViewModel {

	private static ListProperty<String> messages;
	private static HangmanCodeBehind controller;
	
	public static String USERNAME;
	
	public HangmanViewModel(ListProperty<String> listProperty, HangmanCodeBehind codeBehind) {
		Client.startConnection();
		HangmanViewModel.messages = listProperty;
		HangmanViewModel.controller = codeBehind;
	}
	
	
	public static void addMessage(String message) {
		HangmanViewModel.messages.add(message);
	}


	/**
	 * @param text
	 */
	public void sendMessage(String text) {
		System.out.println("Submit chat button clicked");
		Client.sendChat(text);
	}
	
	public static void startGame() {
		HangmanViewModel.controller.startGame();
	}
	
	public static void userNameError() {
		HangmanViewModel.controller.userNameError();
	}

}
