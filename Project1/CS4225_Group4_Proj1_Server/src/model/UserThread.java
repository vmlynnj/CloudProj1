package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import server.Server;
import utility.ClientActions;
import utility.ServerActions;

/**
 * The thread that keeps track of the users actions and state
 * @author Victoria Jenkins, Justin Smith, Aaron Merrell
 *
 */
public class UserThread extends Thread {
	
	private static final String ACTION_SPLIT = "=";
	private String username;
	private Socket socket;

	private PrintWriter writer;
	
	private BufferedReader reader;
	
	private boolean canJoin;

	/**
	 * Creates a user thread
	 * @param socket the socket the thread is listening on
	 * @param canJoin whether or not the user can join 
	 * true if the user can join
	 * false if the game has already started
	 */
	public UserThread(Socket socket, boolean canJoin) {
		this.socket = socket;
		this.canJoin = canJoin;
	}
	
	/**
	 * Runs the application
	 */
	public void run() {
		try {
			InputStream input = this.socket.getInputStream();
			this.reader = new BufferedReader(new InputStreamReader(input));
			OutputStream output = this.socket.getOutputStream();
			this.writer = new PrintWriter(output, true);
			System.out.println("USER THREAD");
			if (!this.canJoin) {
				this.sendMessage(ServerActions.GAME_STARTED, "Game started");
				return;
			}
			String inputMessage = this.reader.readLine();
			String username = inputMessage.split(ACTION_SPLIT)[1];
			while (Server.getUsernames().contains(username)) {
				System.out.println("Username already taken. Please try another one.");
				this.sendMessage(ServerActions.USERNAMEERROR, "Username already taken. Please try another one.");
				inputMessage = this.reader.readLine();
				username = inputMessage.split(ACTION_SPLIT)[1];
			}
			this.username = username;
			Server.getUsernames().add(this.username);
			Server.addUser(this);
			this.listenForInput();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private void listenForInput() {
		try {
			String inputMessage = "";
			do {
				inputMessage = this.reader.readLine();
				String[] clientMessage = inputMessage.split(ACTION_SPLIT);
				if(clientMessage.length >= 2) {
					String action = clientMessage[0];
					String message = clientMessage[1];
					this.handleInput(action, message);
				}

			} while (!inputMessage.equals(ClientActions.QUIT.toString()));
			
			if (Server.getUsers().peek() == this) {
				Server.getUsers().remove(this);
				Server.getUsernames().remove(this.getUserName());
				Server.takeTurn(null);
			}
			Server.getUsers().remove(this);
			Server.getUsernames().remove(this.getUserName());
			System.exit(0);
		} catch (IOException e) {
		}
	}
	
	/**
	 * Handles the user input
	 * @param action the action the user takes
	 * @param message the message the user sends
	 */
	public void handleInput(String action, String message) {
		if (action.toString().equals(ClientActions.GUESS.toString())) {
			Server.guess(message, this);
		}
	}
	
	/**
	 * Sends the message to the user thread
	 * @param action the action the server is sending
	 * @param message the message the server sends
	 * @throws IOException throws an exceptions if streams are corrupted
	 */
	public void sendMessage(ServerActions action, String message) throws IOException {
		System.out.println(action.toString() + ACTION_SPLIT + message);
		this.writer.println(action.toString() + ACTION_SPLIT + message);
	}

	/**
	 * Gets the user name of the user
	 * @return the user name of the user
	 */
	public String getUserName() {
		return this.username;
	}

	/**
	 * Sets the user name of the user
	 * @param name the name of the username
	 */
	public void setUserName(String name) {
		this.username = name;
	}

	@Override
	public String toString() {
		return "Player: " + username;
	}

}
