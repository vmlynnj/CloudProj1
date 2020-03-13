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
	
	private static ListenThread listen;
	private static WriteThread write;

	/**
	 * Starts the client connect to the server
	 * 
	 * @precondition none
     * @postcondition none
     * 
	 */
	public static void startConnection() {	
		Socket clientSocket = null;

		try {
			clientSocket = new Socket(HOST, PORT);
			System.out.println("Connected to server");
			
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
	 * Sends a client message to the server
	 * 
	 * @precondition none
     * @postcondition none
     * 
	 * @param action
	 * 		the action to be sent
	 * @param message
	 * 		the message to be sent
	 */
	public static void sendMessage(ClientActions action, String message) {
		write.sendMessage(action, message);
	}



	/**
	 * Returns the client listenThread
	 * @return the listen
	 */
	public static ListenThread getListen() {
		return Client.listen;
	}

	/**
	 * Sets the client listenThread
	 * @param listen the listen to set
	 */
	public static void setListen(ListenThread listen) {
		Client.listen = listen;
	}

	/**
	 * Gets the client write thread
	 * 
	 * @return the write
	 */
	public static WriteThread getWrite() {
		return Client.write;
	}

	/**
	 * Sets the client writeThread
	 * 
	 * @param write the write to set
	 */
	public static void setWrite(WriteThread write) {
		Client.write = write;
	}

}