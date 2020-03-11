package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class UserThread {
	private String username;
	private Socket socket;

	private InputStream incomingMessages = null;
	private OutputStream output;

	BufferedReader reader;

	public UserThread(Socket socket, String userName) {
		this.socket = socket;
		try {
			this.incomingMessages = this.socket.getInputStream();
			this.output = this.socket.getOutputStream();
			reader = new BufferedReader(new InputStreamReader(this.incomingMessages));
			this.username = userName;
			System.out.println("userThread username is " + this.username);
			this.sendMessage(GameThread.AllUsers());
			this.sendMessage(GameThread.GAME_INSTRUCTIONS);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getMessage() throws IOException {
		String message = this.reader.readLine();
		return message;
	}

	public void sendMessage(String message) throws IOException {
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
