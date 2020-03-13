package server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import model.HangmanGame;
import model.UserThread;
import utility.ServerActions;
import java.net.ServerSocket;

/**
 * Represents a server in a Client/Server setup. For our class, the port number
 * is 4225.
 * 
 * @author Victoria Jenkins, Justin Smith, Aaron Merrell
 */
public class Server {
	private static final String ACTION_SPLIT = "=";
	public static final String GAME_INSTRUCTIONS = "Hangman Enter Quit to leave";

	private static List<String> usernames;

	private static Queue<UserThread> users;
	private static boolean gameOpen;
	
	
	private static HangmanGame game;
	
	private static final int PORT = 4225;
	
	/**
	 * Main entry point of server; must run this program first.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		Server server = new Server();
		server.run();
		
		
	}
	
	/**
	 * Runs the application
	 */
	public void run() {
		Server.game = new HangmanGame();
		
		Server.users = new LinkedList<UserThread>();
		Server.usernames = new ArrayList<String>();
		
		this.gameOpen = true;
		
		
		try (ServerSocket server = new ServerSocket(PORT)) {
			Socket clientSocket;
			while (true) {
				this.startTimer();
				while (Server.gameOpen) {
					clientSocket = server.accept();
					System.out.println("Connected to Client");
					

					UserThread user = new UserThread(clientSocket, true);
					user.start();
				}
				clientSocket = server.accept();
				System.out.println("Connected to Client CANT JOIN");
				this.startTimer();
				UserThread user = new UserThread(clientSocket, false);
				user.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void startTimer() {
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		
		Runnable task = () -> {
			Server.gameOpen = false;
			Server.broadcastMessage(ServerActions.START, "The game has begun", null);
			System.out.println("TIMer ENDS");
			Server.takeTurn(Server.users.peek());
			Server.broadcastMessage(ServerActions.WORD, Server.game.getHiddenWord(), null);
		};
		
		service.schedule(task, 30, TimeUnit.SECONDS);
	}

	/**
	 * Adds the users to currently active users
	 * @param user the user to add
	 */
	public static synchronized  void addUser(UserThread user) {

		try {
			if (Server.users.size() < 4) {
				Server.users.add(user);

				Server.broadcastMessage(ServerActions.PLAYER, "server: A new player has joined: " + user.getUserName(), null);

				user.sendMessage(ServerActions.VALID_PLAYER, "valid player");
				user.sendMessage(ServerActions.PRINTUSERS, Server.allUsers());
			} else {
				user.sendMessage(ServerActions.FULL_ROOM, "room is full");

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the users to currently active users
	 * @param user the user to add
	 */
	public static synchronized  void deleteUser(UserThread user) {
		System.out.println("DELETE USER _________________________");
		
		if (Server.getUsers().toArray()[Server.getUsers().size()-1] == user && Server.getUsers().size() > 0) {
			Server.getUsers().remove(user);
			Server.getUsernames().remove(user.getUserName());
			Server.takeTurn(null);
		}
		Server.getUsers().remove(user);
		Server.getUsernames().remove(user.getUserName());
		Server.broadcastMessage(ServerActions.MESSAGE, "server: "+user.getUserName()+" has left the game", null);
		if (Server.users.size() <= 0) {
			Server.restart();
		}
	}

	/**
	 * Broadcasts a message to all users
	 * @param action the server action taken
	 * @param message the message to send to the clients
	 * @param user the user that is sending the message if any
	 */
	public static void broadcastMessage(ServerActions action, String message, UserThread user) {
		for (UserThread currUser : Server.users) {
			try {
				if (user == null) {
					currUser.sendMessage(action, message);
				} else {
					currUser.sendMessage(action, user.getUserName() + ": " + message);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets all the users in play
	 * @return all of the users
	 */
	public static String allUsers() {
		String output = "Current Players: " + System.lineSeparator();
		for (UserThread currUser : Server.users) {
			output += ServerActions.LISTPLAYERS + ACTION_SPLIT+currUser.toString() + System.lineSeparator();
		}
		return output;
	}
	
	/**
	 * Handles the user guessing a letter
	 * @param guess the guess the user makes
	 * @param user the user that guessed it
	 */
	public static void guess(String guess, UserThread user) {
		
		Server.broadcastMessage(ServerActions.MESSAGE, guess, user);
		Server.game.guessLetter(guess);
		if (Server.game.isGameLost()) {
			Server.broadcastMessage(ServerActions.WORD, "Your word was: " + Server.game.getWordToGuess().toUpperCase(), null);
			Server.broadcastMessage(ServerActions.LOSE, "The game is lost", null);
			Server.restart();
		}
		if (Server.game.isGameWon()) {
			Server.broadcastMessage(ServerActions.WIN, "The game is won", null);
			Server.gameOpen = true;
		}
		Server.takeTurn(user);
		Server.broadcastMessage(ServerActions.REMOVELETTEROPTION, guess, null);
		Server.broadcastMessage(ServerActions.WORD, Server.game.getHiddenWord(), null);
		Server.broadcastMessage(ServerActions.WRONG, "" + Server.game.getIncorrectGuesses(), null);
		
		
	}
	
	/**
	 * user takes a turn
	 * @param user the user 
	 */
	public static void takeTurn(UserThread user) {
		
		System.out.println("Server take turn");
		UserThread currUser = Server.users.poll();
		try {
			Server.broadcastMessage(ServerActions.WORD, Server.game.getHiddenWord(), null);
			Server.broadcastMessage(ServerActions.WRONG, "" + Server.game.getIncorrectGuesses(), null);
			currUser.sendMessage(ServerActions.TURN, "your turn");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (user != null) {
			Server.users.add(currUser);
		}
	}
	
	public static void restart() {
		Server.users.clear();
		Server.usernames.clear();
		Server.gameOpen = true;
	}
	/**
	 * Gets the usernames currently in use
	 * @return the usernames
	 */
	public static List<String> getUsernames() {
		return Server.usernames;
	}

	/**
	 * Gets the users currently in play
	 * @return the users
	 */
	public static Queue<UserThread> getUsers() {
		return users;
	}
	
	/**
	 * Gets the users currently in play
	 * @return the users
	 */
	public static boolean getGameOpen() {
		return gameOpen;
	}






}