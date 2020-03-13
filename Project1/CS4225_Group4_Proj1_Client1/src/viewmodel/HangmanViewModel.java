/**
 * 
 */
package viewmodel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import client.Client;
import javafx.application.Platform;
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
	
	private static ScheduledExecutorService service;
	
	private static boolean takenTurn = false;
	private static String username;
	
	public HangmanViewModel(ListProperty<String> listProperty, HangmanCodeBehind codeBehind) {
		service = Executors.newSingleThreadScheduledExecutor();
		Client.startConnection();
		HangmanViewModel.messages = listProperty;
		HangmanViewModel.controller = codeBehind;
	}
	
	
	public static void addMessage(String message) {
		HangmanViewModel.controller.addMessage(message);

	}

	public void sendMessage(ClientActions action, String text) {
		Client.sendMessage(action, text);
	}
	
	public void sendGuess(String text) {
		service.shutdownNow();
		Client.sendMessage(ClientActions.GUESS, text);
	}
	

	public static void retry() {
		Client.sendMessage(ClientActions.RETRY, "retry");
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
		service = Executors.newSingleThreadScheduledExecutor();
		Runnable removeTask = () -> {
			if(!takenTurn) {
				HangmanViewModel.showRemove();
			}
			
		};
		Runnable task = () -> {
			if(!takenTurn) {
				HangmanViewModel.showWarning();
				service.schedule(removeTask, 15, TimeUnit.SECONDS);
			}
		};
		

		HangmanViewModel.controller.enableTurn();
		service.schedule(task, 15, TimeUnit.SECONDS);
		
		
	}
	
	public static void showWarning() {
		HangmanViewModel.controller.showWarning();
	}
	
	public static void showRemove() {
		Client.sendMessage(ClientActions.QUIT, "quit");
		HangmanViewModel.controller.showRemove();
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


	/**
	 * Gets the username
	 * @return the username
	 */
	public static String getUsername() {
		return username;
	}
	
	public static boolean getTakenTurn() {
		return takenTurn;
	}
	/**
	 * Sets taken turn
	 * @param isTaken whether or not the turn has been taken
	 */
	public static void setTakenTurn(boolean isTaken) {
		takenTurn = isTaken;
	}

	/**
	 * Sets the username
	 * @param name the name to set it to
	 */
	public static void setUsername(String name) {
		username = name;
	}

}
