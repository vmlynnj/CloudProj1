package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import server.Server;

/**
 * A thread for the server
 * @author Victoria Jenkins, Justin Smith, Aaron Merrell
 *
 */
public class ServerThread extends Thread {

	private Socket socket;
	
	private InputStream input;
	private OutputStream output;

	/**
	 * Creates a thread to handle the passed in socket
	 * 
	 * @precondition clientSocket != null
	 * 
	 * @param clientSocket The client to serve
	 */
	public ServerThread(Socket clientSocket) {
		this.socket = clientSocket;
		try {
			this.input = this.socket.getInputStream();
			this.output = this.socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Runs the application
	 */
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.input));
			String username = reader.readLine();
			UserThread user = new UserThread(socket, username);
			if(GameThread.usernames.contains(username)) {
				while (GameThread.usernames.contains(username)) {
					user.sendMessage("This username has been taken. Please try another one");
					username = reader.readLine();
				}
			}
			user.setUserName(username);
			GameThread.usernames.add(username);
			GameThread.AddUser(user);
			user.start();


		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			this.socket.close();
			this.input.close();
			this.output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
