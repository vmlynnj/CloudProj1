package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameThread extends Thread {

	// TODO MORE DETAILS
	public static final String GAME_INSTRUCTIONS = "Hangman Enter Quit to leave";

	public static List<String> usernames;
	public static BlockingQueue<UserThread> users;

	private HangmanGame game;

	public GameThread() {
		GameThread.usernames = Collections.synchronizedList(new ArrayList<String>());
		GameThread.users = new LinkedBlockingQueue<UserThread>();
		this.game = new HangmanGame();
	}

	@Override
	public void run() {

	}

	public synchronized static String AddUser(UserThread user) {
		if (GameThread.users.size() < 4) {
			GameThread.users.add(user);
			GameThread.broadcastMessage("A new player has joined: " + user.getUserName(), null);
			return "Success";
		} else {
			return "";
		}
	}

	public static void broadcastMessage(String message, UserThread user) {
		for(UserThread currUser : GameThread.users) {
			try {
				if (user == null) {
					currUser.sendMessage(message);
				} else {
					currUser.sendMessage(user.getName() + ": " + message);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String AllUsers() {
		String output = "Current Players: " + System.lineSeparator();
		for (UserThread currUser : GameThread.users) {
			output += currUser.toString() + System.lineSeparator();
		}
		return output;
	}

}
