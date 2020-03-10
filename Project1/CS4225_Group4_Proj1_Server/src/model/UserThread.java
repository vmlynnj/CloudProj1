package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class UserThread extends Thread {
	private String username;
	private Socket socket;
	
	private InputStream incomingMessages = null;
	private OutputStream output;
	
	public UserThread(Socket socket, String userName) {
		this.socket = socket;
		try {
			this.incomingMessages = this.socket.getInputStream();
			this.output = this.socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.username = userName;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Runs the application
	 */
	public void run() {
		try {
			this.sendMessage(GameThread.AllUsers());
			this.sendMessage(GameThread.GAME_INSTRUCTIONS);
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.incomingMessages));
			
			String userMessage = null;
			while(!userMessage.equals("Quit")) {
				userMessage = reader.readLine();
				//TODO CHECK TO SEE IF IT IS CORRECT
				GameThread.broadcastMessage(this.toString() + ":  " + userMessage, this);
			}
			

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			this.socket.close();
			this.incomingMessages.close();
			this.output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void sendMessage(String message) throws IOException {
		var objOut = new ObjectOutputStream(this.output);
		objOut.write(message.getBytes());
		objOut.flush();
	}
	
	public String getUserName() {
		return this.username;
	}
	
	public void setUserName(String name) 
	{
		this.username = name;
	}

	@Override
	public String toString() {
		return "Player: " + username;
	}
	
	
}
