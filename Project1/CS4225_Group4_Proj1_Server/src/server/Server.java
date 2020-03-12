package server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
	

	
	public static final String GAME_INSTRUCTIONS = "Hangman Enter Quit to leave";

	public static List<String> usernames;
	public static ArrayList<UserThread> users;
	
	
	public static HangmanGame game;
	
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
	
	public void run () {
		Server.game = new HangmanGame();
		
		Server.users = new ArrayList<UserThread>();
		Server.usernames = new ArrayList<String>();
		try (ServerSocket server = new ServerSocket(PORT)){
			Socket clientSocket;
			
			while(true) {
				clientSocket = server.accept();
				System.out.println("Connected to Client");
				UserThread user = new UserThread(clientSocket);
				user.start();
				
				//TODO ADD 15 SEC timer to wait for the game to begin
				

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public synchronized static String AddUser(UserThread user) {
		if (Server.users.size() < 4) {
			Server.users.add(user);
			Server.takeTurn(user);
			Server.broadcastMessage(ServerActions.PLAYER,"A new player has joined: " + user.getUserName(), null);

			try {
				user.sendMessage(ServerActions.PRINTUSERS, Server.AllUsers());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "Success";
		} else {
			return "";
		}
	}

	public static void broadcastMessage(ServerActions action, String message, UserThread user) {
		for(UserThread currUser : Server.users) {
			try {
				if (user == null) {
					currUser.sendMessage(action,message);
				} else {
					currUser.sendMessage(action,user.getUserName() + ": " + message);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String AllUsers() {
		String output = "Current Players: " + System.lineSeparator();
		for (UserThread currUser : Server.users) {
			output += currUser.toString() + System.lineSeparator();
		}
		return output;
	}
	
	public static void Guess(String guess, UserThread user) {
		
		Server.broadcastMessage(ServerActions.MESSAGE, guess, user);
		boolean correct = Server.game.guessLetter(guess);
		if(correct) {
			Server.broadcastMessage(ServerActions.WORD, Server.game.hiddenWord, null);
		}
		else {
			//TODO HANDLE INCORRECT GUESS
		}
		Server.takeTurn(user);
		
		
	}
	
	public static void takeTurn(UserThread user) {
		
		System.out.println("Server take turn");
		UserThread currUser = Server.users.remove(0);
		try {
			currUser.sendMessage(ServerActions.TURN, "your turn");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Server.users.add(user);
		
	}


}