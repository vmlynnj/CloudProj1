package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import util.ServerActions;
import viewmodel.HangmanViewModel;

/**
 * Creates a thread to listen to the server
 * @author Victoria Jenkins, Justin Smith, Aaron Merrell
 *
 */
public class ListenThread extends Thread {

	
	private Socket socket;
	
	private InputStream input;
	
	private BufferedReader reader;
	
	/**
	 * Creates a listening thread
	 * @param socket the socket to listen on
	 */
	public ListenThread(Socket socket) {
		this.socket = socket;
		
		try {
			this.input = this.socket.getInputStream();
			this.reader = new BufferedReader(new InputStreamReader(this.input));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		while (true) {
			this.read();

		}
	} 
	
	/**
	 * Starts reading input from the server
	 */
	public void read() {
		try {
			String message = this.reader.readLine();
			
			this.handleMessages(message);
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * Handles messages from the server
	 * @param message the message the server sends
	 */
	public void handleMessages(String message) {
		String[] messages = message.split(Client.ACTION_SPLIT);
		if (messages.length >= 2) {
			String action = messages[0];
			String serverMessage = messages[1];
			this.handleLogin(action, serverMessage);
			this.printPlayers(action, serverMessage);
			this.printMessage(action, serverMessage);
			this.handleTakingTurn(action, serverMessage);
			this.determineWinLoss(action);
		}

	}
	
	
	private void handleTakingTurn(String action, String message) {
		if (action.equals(ServerActions.WORD.toString())) {
			HangmanViewModel.updateWord(message);
		}
		if (action.equals(ServerActions.TURN.toString())) {
			HangmanViewModel.enableTurn();
		}
		if (action.equals(ServerActions.REMOVELETTEROPTION.toString())) {
			HangmanViewModel.removeLetterOption(message);
		}
		if (action.equals(ServerActions.WRONG.toString())) {
			HangmanViewModel.showHangman(Integer.parseInt(message));
		}
	}
	
	private void handleLogin(String action, String message) {
		if (action.equals(ServerActions.USERNAMEERROR.toString())) {
			HangmanViewModel.userNameError();
		}
		if (action.equals(ServerActions.VALID_PLAYER.toString())) {
			HangmanViewModel.login();
		}
		if (action.equals(ServerActions.FULL_ROOM.toString())) {
			HangmanViewModel.fullRoom();
		}
		if (action.equals(ServerActions.GAME_STARTED.toString())) {
			HangmanViewModel.gameStarted();
		}
		if (action.equals(ServerActions.START.toString())) {
			HangmanViewModel.startGame();
		}
	}
	
	private void printPlayers(String action, String message) {
		if (action.equals(ServerActions.PLAYER.toString()) || action.equals(ServerActions.PRINTUSERS.toString())) {
			HangmanViewModel.addMessage(message);
		}
		if (action.equals(ServerActions.LISTPLAYERS.toString())) {
			HangmanViewModel.addMessage(message);
		}
	}
	private void printMessage(String action, String message) {
		if (action.equals(ServerActions.MESSAGE.toString())) {
			HangmanViewModel.addMessage(message);
		}
	}
	
	private void determineWinLoss(String action) {
		if (action.equals(ServerActions.LOSE.toString())) {
			HangmanViewModel.gameLost();
		}
		if (action.equals(ServerActions.WIN.toString())) {
			HangmanViewModel.gameWon();
		}	
	}

}
