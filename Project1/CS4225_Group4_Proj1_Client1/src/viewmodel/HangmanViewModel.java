/**
 * 
 */
package viewmodel;

import client.Client;
import javafx.beans.property.ListProperty;
import util.ClientActions;
import view.HangmanCodeBehind;

/**
 * Stores information for the HangmanViewModel class
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

	public void sendMessage(ClientActions action, String text) {
		Client.sendMessage(action, text);
	}
	
	public static void startGame() {
		HangmanViewModel.controller.startGame();
	}
	
	public static void login() {
		HangmanViewModel.controller.login();
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

	public static void removeLetterOption(String string) {
		HangmanViewModel.controller.removeLetter(string);
	}

	public static void gameLost() {
		HangmanViewModel.controller.gameLost();
	}

	public static void gameWon() {
		HangmanViewModel.controller.gameWon();
	}
	
	public static void fullRoom() {
		HangmanViewModel.controller.fullRoom();
	}

	public static void gameStarted() {
		HangmanViewModel.controller.gameStarted();
	}
	
	public static void showHangman(int wrongGuesses) {
		if (wrongGuesses >= 1) {
			HangmanViewModel.controller.showHead();
		}
		if (wrongGuesses >= 2) {
			HangmanViewModel.controller.showBody();
		}
		if (wrongGuesses >= 3) {
			HangmanViewModel.controller.showLeftLeg();
		}
		if (wrongGuesses >= 4) {
			HangmanViewModel.controller.showRightLeg();
		}
		if (wrongGuesses >= 5) {
			HangmanViewModel.controller.showLeftArm();
		}
		if (wrongGuesses >= 6) {
			HangmanViewModel.controller.showRightArm();
		}
	}

}
