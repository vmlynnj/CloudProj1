package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import server.Server;
import utility.ServerActions;


public class UserThread extends Thread {
	
	
	private String username;
	private Socket socket;

	private PrintWriter writer;
	
	private BufferedReader reader;
	
	public UserThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			InputStream input = this.socket.getInputStream();
			this.reader = new BufferedReader(new InputStreamReader(input));
			OutputStream output = this.socket.getOutputStream();
			this.writer = new PrintWriter(output, true);
			System.out.println("USER THREAD");
		
			String inputMessage = reader.readLine();
			String username = inputMessage.split(Server.ACTION_SPLIT)[1];
			while(Server.usernames.contains(username)) {
				System.out.println("Username already taken. Please try another one.");
				this.sendMessage(ServerActions.USERNAMEERROR, "Username already taken. Please try another one.");
				inputMessage = reader.readLine();
				username = inputMessage.split(Server.ACTION_SPLIT)[1];
			}
			this.username = username;
			Server.usernames.add(this.username);
			Server.AddUser(this);
			String action = "";
			String message = "";
			
			do {
				inputMessage = reader.readLine();
				action = inputMessage.split(Server.ACTION_SPLIT)[0];
				message = inputMessage.split(Server.ACTION_SPLIT)[1];
				this.handleInput(action, message);
				Server.broadcastMessage(ServerActions.MESSAGE, inputMessage, this);
			} while (inputMessage != "QUIT");
			
			Server.users.remove(this);
			Server.usernames.remove(this.getUserName());
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	
	public void handleInput(String action, String message) {
		//handles it
	}
	public void sendMessage(ServerActions action, String message) throws IOException {
		System.out.println(action.toString()+Server.ACTION_SPLIT+message);
		this.writer.println(action.toString()+Server.ACTION_SPLIT+message);
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



}
