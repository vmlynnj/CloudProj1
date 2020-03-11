package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

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
			System.out.println("ServerThread created.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	/**
	 * Runs the application
	 */
	public void run() {
		System.out.println("Starting serverThread.run() method.");
		try {
			
			System.out.println("Inside try block of the serverThread.run() method.");
			ObjectInputStream reader = new ObjectInputStream(this.input);
			System.out.println("Created reader inside of the serverThread.run() method.");
			String username = (String) reader.readObject();
			System.out.println("Server thread has received username: " + username); //TODO DOESNT GET HERE
			UserThread user = new UserThread(socket, username);
			if(GameThread.usernames.contains(username)) {
				while (GameThread.usernames.contains(username)) {
					user.sendMessage("This username has been taken. Please try another one");
					username = (String) reader.readObject();
				}
			}
			user.setUserName(username);
			GameThread.usernames.add(username);
			user.sendMessage(GameThread.AddUser(user));

		} catch (IOException | ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
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
