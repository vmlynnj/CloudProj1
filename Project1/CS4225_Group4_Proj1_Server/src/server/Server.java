package server;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import model.UserThread;

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
	
	private static boolean isGameOver;
	
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
		Server.users = new ArrayList<UserThread>();
		Server.usernames = new ArrayList<String>();
		try (ServerSocket server = new ServerSocket(PORT)){
			Socket clientSocket;
			
			while(true) {
				clientSocket = server.accept();
				System.out.println("Connected to Client");
				UserThread user = new UserThread(clientSocket, this);
				user.start();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public synchronized static String AddUser(UserThread user) {
		if (Server.users.size() < 4) {
			Server.users.add(user);
			Server.broadcastMessage("PLAYER=","A new player has joined: " + user.getUserName(), null);
			try {
				user.sendMessage("PRINTUSERS=", Server.AllUsers());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "Success";
		} else {
			return "";
		}
	}

	public static void broadcastMessage(String type, String message, UserThread user) {
		for(UserThread currUser : Server.users) {
			try {
				if (user == null) {
					currUser.sendMessage(type,message);
				} else {
					currUser.sendMessage(type,user.getUserName() + ": " + message);
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


}