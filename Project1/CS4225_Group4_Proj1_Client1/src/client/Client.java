package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import util.ClientActions;

/**
 * Represents a client in a Client/Server setup. For our class, the port number
 * is 4225.
 * 
 * @author Victoria Jenkins, Justin Smith, Aaron Merrell
 */
public class Client {

	public static final String ACTION_SPLIT = "=";
	
	private static final String HOST = "localhost";
	private static final int PORT = 4225;
	
	private static Socket socket = null;
	private static ListenThread listen;
	private static WriteThread write;

	/**
	 * Starts the connection with the server
	 */
	public static void startConnection() {	
		Socket clientSocket = null;

		try {
			clientSocket = new Socket(HOST, PORT);
			System.out.println("Connected to server");
			Client.socket = clientSocket;
			
			listen = new ListenThread(clientSocket);
			listen.start();
			write = new WriteThread(clientSocket);
			write.start();

		} catch (UnknownHostException e) {
			System.err.println("Problem with the host.");
		} catch (IOException e) {
			System.err.println("Unable to connect; very likely that the server was not started.");
		} catch (RuntimeException e) {
			System.out.println(
					"Error parsing file - Ensure input matrices have correct columns/row values and no values are missing"
							+ System.lineSeparator() + e.getMessage());
		}
	}
	
	/**
	 * Sends messag eto the server
	 * @param action the action
	 * @param message the message
	 */
	public static void sendMessage(ClientActions action, String message) {
		write.sendMessage(action, message);
	}
	
	/**
	 * Gets the socket
	 * @return the socket
	 */
	public static Socket getSocket() {
		return socket;
	}
	
	/**
	 * Gets the listener
	 * @return the listener 
	 */
	public static Socket getListen() {
		return socket;
	}
	
	/**
	 * Gets writer
	 * @return
	 */
	public static Socket getWrite() {
		return socket;
	}

}