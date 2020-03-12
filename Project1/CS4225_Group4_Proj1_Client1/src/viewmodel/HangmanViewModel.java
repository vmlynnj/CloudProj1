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
import util.ClientActions;
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
	public void sendMessage(ClientActions action, String text) {
		Client.sendChat(action.toString()+"="+text);
	}
	
	public static void startGame() {
		HangmanViewModel.controller.startGame();
	}
	
	public static void userNameError() {
		HangmanViewModel.controller.userNameError();
	}

}
