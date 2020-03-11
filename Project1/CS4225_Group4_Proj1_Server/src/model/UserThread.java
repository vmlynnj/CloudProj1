package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;


public class UserThread extends Thread {
	private String username;
	private Socket socket;

	private InputStream incomingMessages;
	private OutputStream output;

	ObjectInputStream reader;

	public UserThread(Socket socket, String userName) {
		this.socket = socket;
		try {
			this.incomingMessages = this.socket.getInputStream();
			this.output = this.socket.getOutputStream();
			//this.reader = new ObjectInputStream(this.incomingMessages);
			this.username = userName;
			System.out.println("userThread username is " + this.username);
			this.sendMessage(GameThread.AllUsers());
			this.sendMessage(GameThread.GAME_INSTRUCTIONS);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			try {
				TimeUnit.SECONDS.sleep(3);
				this.getMessage();
			} catch (IOException | InterruptedException e) {
			}
		}
	}

	public String getMessage() throws IOException {
		String message = null;
		try {
			message = (String) this.reader.readObject();
			System.out.println("UserThread has received message: " + message);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return message;
	}

	public void sendMessage(String message) throws IOException {
		System.out.println("USer message : "+ message);
		var objOut = new ObjectOutputStream(this.output);
		objOut.write(message.getBytes());
		objOut.flush();
	}

	public String getUserName() {
		return this.username;
	}

	public void setUserName(String name) {
		this.username = name;
	}

	@Override
	public String toString() {
		return "Player: " + username;
	}

	public void disconnect() {
		try {
			this.socket.close();
			this.incomingMessages.close();
			this.output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
