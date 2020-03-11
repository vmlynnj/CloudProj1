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
			this.reader = new ObjectInputStream(this.incomingMessages);
			this.username = userName;
			System.out.println("userThread username is " + this.username);
			this.sendMessage(GameThread.GAME_INSTRUCTIONS);
			//GameThread.AddUser(this);
			this.sendMessage(GameThread.AllUsers());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			try {
				String message = (String)this.reader.readObject();
				GameThread.broadcastMessage(message, this);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		while (true) {
//			try {
//				TimeUnit.SECONDS.sleep(3);
//				this.getMessage();
//			} catch (IOException | InterruptedException e) {
//			}
//		}
	}

	public void sendMessage(String message) throws IOException {
		System.out.println("USer message : "+ message);
		var objOut = new ObjectOutputStream(this.output);
		objOut.writeObject(message);
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
