package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import server.Server;


public class UserThread extends Thread {
	private String username;
	private Socket socket;

	private Server server;
	private PrintWriter writer;
	
	private BufferedReader reader;
	
	public UserThread(Socket socket,Server server) {
		this.socket = socket;

		this.server = server;
	}
	
	public void run() {
		try {
			InputStream input = this.socket.getInputStream();
			this.reader = new BufferedReader(new InputStreamReader(input));
			OutputStream output = this.socket.getOutputStream();
			this.writer = new PrintWriter(output, true);
			System.out.println("USER THREAD");
			String message = reader.readLine();
			System.out.println("USERName: "+ message);
			
			this.username = message;
			Server.AddUser(this);
			do {
				message = reader.readLine();
				Server.broadcastMessage(message, this);
				System.out.println("MESSAGE: "+ message);
			} while (message != "QUIT");
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void sendMessage(String message) throws IOException {
		System.out.println("Server sends: "+ message);
		this.writer.println(message);
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
