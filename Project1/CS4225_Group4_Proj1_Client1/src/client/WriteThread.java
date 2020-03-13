package client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import util.ClientActions;

/**
 * A thread for writing to the server
 * @author Victoria Jenkins, Justin Smith, Aaron Merrel
 *
 */
public class WriteThread extends Thread {

	private Socket socket;
	private OutputStream output;
	private PrintWriter writer;
	
	/**
	 * Creates a thread for writing to a server
	 * @param socket the socket to write to
	 */
	public WriteThread(Socket socket) {
		this.socket = socket;
		
		try {
			this.output = this.socket.getOutputStream();
			this.writer = new PrintWriter(this.output, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
		
	/**
	 * Sends a message to the server
	 * @param action the action taken
	 * @param message the message sent
	 */
	public void sendMessage(ClientActions action, String message) {
		System.out.println(action.toString() + message);
		this.writer.println(action.toString() + Client.ACTION_SPLIT + message);
	}
	
}
