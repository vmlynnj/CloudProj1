/**
 * 
 */
package viewmodel;

import client.Client;
import javafx.beans.property.ListProperty;
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
		Client.sendMessage(action, text);
	}
	
	public static void startGame() {
		HangmanViewModel.controller.startGame();
	}
	
	public static void userNameError() {
		HangmanViewModel.controller.userNameError();
	}
	
	public static void enableTurn() {
		HangmanViewModel.controller.enableTurn();
	}
	public static void updateWord(String word) {
		HangmanViewModel.controller.updateWord(word);
	}


	/**
	 * @param string
	 */
	public static void removeLetterOption(String string) {
		HangmanViewModel.controller.removeLetter(string);
	}
	
	public static void gameLost() {
		HangmanViewModel.controller.gameLost();
	}

}
