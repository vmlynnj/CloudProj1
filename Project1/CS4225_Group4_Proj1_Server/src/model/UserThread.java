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
	 * 
	 * @precondition none
	 * @postcondition none
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
			this.login(inputMessage.split(ACTION_SPLIT)[0], inputMessage.split(ACTION_SPLIT)[1]);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private void listenForInput() {
		try {
			String inputMessage = "";
			String action = "";
			do {
				inputMessage = this.reader.readLine();
				System.out.println("Server receives: " +  inputMessage);
				String[] clientMessage = inputMessage.split(ACTION_SPLIT);
				if (clientMessage.length >= 2) {
					action = clientMessage[0];
					String message = clientMessage[1];
					if (action.equals(ClientActions.RETRY.toString())) {
						Server.restart();
					}
					if (action.equals(ClientActions.LOGIN.toString())) {
						this.login(action, message);
					} else {
						this.handleInput(action, message);
					}
					
				}

			} while (!action.equals(ClientActions.QUIT.toString()));
			
			
			Server.deleteUser(this);
		} catch (IOException e) {
		}
	}
	
	/**
	 * Handles the user input
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param action the action the user takes
	 * @param message the message the user sends
	 */
	public void handleInput(String action, String message) {
		if (action.toString().equals(ClientActions.GUESS.toString())) {
			Server.guess(message, this);
		}
		if (action.toString().equals(ClientActions.TURN_END.toString())) {
			Server.takeTurn(this);
		}
	}
	
	private void login(String action, String message) {
		if (action.toString().equals(ClientActions.LOGIN.toString())) {
			while (Server.getUsernames().contains(message)) {
				System.out.println("Username already taken. Please try another one.");
				try {
					this.sendMessage(ServerActions.USERNAMEERROR, "Username already taken. Please try another one.");
				
					String inputMessage = this.reader.readLine();
					String[] clientMessage = inputMessage.split(ACTION_SPLIT);
					if (clientMessage.length >= 2) {
						String login = clientMessage[0];
						String username = clientMessage[1];
						this.login(login, username);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (Server.getGameOpen()) {
				this.username = message;
				Server.getUsernames().add(this.username);
				Server.addUser(this);
				this.listenForInput();
			} else {
				try {
					this.sendMessage(ServerActions.GAME_STARTED, "game has started");
					this.listenForInput();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	/**
	 * Sends the message to the user thread
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param action the action the server is sending
	 * @param message the message the server sends
	 * 
	 * @throws IOException throws an exceptions if streams are corrupted
	 */
	public void sendMessage(ServerActions action, String message) throws IOException {
		System.out.println(action.toString() + ACTION_SPLIT + message);
		this.writer.println(action.toString() + ACTION_SPLIT + message);
	}

	/**
	 * Gets the user name of the user
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the user name of the user
	 */
	public String getUserName() {
		return this.username;
	}

	/**
	 * Sets the user name of the user
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param name the name of the username
	 */
	public void setUserName(String name) {
		this.username = name;
	}

	@Override
	public String toString() {
		return "Player: " + this.username;
	}

}
