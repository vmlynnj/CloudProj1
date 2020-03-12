package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

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
		String[] messages = message.split("=");
		if(messages[0].equals("USERNAMEERROR")) {
			HangmanViewModel.userNameError();
		}
		if(messages[0].equals("Message")){
			HangmanViewModel.addMessage(messages[1]);
		}
		if(messages[0].equals("PLAYER")) {
			HangmanViewModel.addMessage(messages[1]);
		}
		
		if(messages[0].equals("PRINTUSERS")) {
			HangmanViewModel.startGame();
			HangmanViewModel.addMessage(messages[1]);
		}
		
	}
	

}
