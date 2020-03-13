package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import util.ServerActions;
import viewmodel.HangmanViewModel;

public class ListenThread extends Thread{

	
	private Socket socket;
	
	private InputStream input;
	
	private BufferedReader reader;
	
	public static String MESSAGE = null;
	
	public ListenThread(Socket socket) {
		this.socket = socket;
		
		try {
			this.input = this.socket.getInputStream();
			this.reader = new BufferedReader(new InputStreamReader(this.input));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		while(true)
		{
			System.out.println("In listener 0");
			this.read();

		}
	} 
	
	public void read() {
		try {
			String message = this.reader.readLine();
			this.handleMessages(message);
			System.out.println(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public void handleMessages(String message) {
		if(message.contains("Player: " )) {
			HangmanViewModel.addMessage(message);
		}
		String[] messages = message.split(Client.ACTION_SPLIT);
		if(messages[0].equals(ServerActions.USERNAMEERROR.toString())) {
			HangmanViewModel.userNameError();
		}
		if(messages[0].equals(ServerActions.VALID_PLAYER.toString())) {
			HangmanViewModel.login();
		}

		if(messages[0].equals(ServerActions.MESSAGE.toString()) || messages[0].equals(ServerActions.PLAYER.toString()) || messages[0].equals(ServerActions.PRINTUSERS.toString())) {
			HangmanViewModel.addMessage(messages[1]);
		}
		if(messages[0].equals(ServerActions.WORD.toString())) {
			HangmanViewModel.updateWord(messages[1]);
		}
		if(messages[0].equals(ServerActions.TURN.toString())) {
			HangmanViewModel.enableTurn();
		}
		
		if(messages[0].equals(ServerActions.START.toString())) {
			HangmanViewModel.startGame();
		}
		if (messages[0].equals(ServerActions.REMOVELETTEROPTION.toString())) {
			HangmanViewModel.removeLetterOption(messages[1]);
		}
		
		if (messages[0].equals(ServerActions.LOSE.toString())) {
			HangmanViewModel.gameLost();
		}
		if (messages[0].equals(ServerActions.WIN.toString())) {
			HangmanViewModel.gameWon();
		}
		if (messages[0].equals(ServerActions.FULL_ROOM.toString())) {
			HangmanViewModel.fullRoom();
		}
		if (messages[0].equals(ServerActions.GAME_STARTED.toString())) {
			HangmanViewModel.gameStarted();
		}
		if (messages[0].equals(ServerActions.WRONG.toString())) {
			HangmanViewModel.showHangman(Integer.parseInt(messages[1]));
		}

	}
	

}
