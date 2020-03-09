package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import util.OutputWriter;

/**
 * Represents a client in a Client/Server setup. For our class, the port number
 * is 4225.
 * 
 * @author Victoria Jenkins, Justin Smith, Aaron Merrell
 */
public class Client {

	private static final String HOST = "localhost";
	private static final int PORT = 4225;

	/**
	 * Main entry point of client.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		Socket clientSocket = null;
		InputStream incomingMessages = null;
		OutputStream outgoingMessages = null;
		OutputWriter writer = new OutputWriter();

		try {
			clientSocket = new Socket(HOST, PORT);
			outgoingMessages = clientSocket.getOutputStream();
			incomingMessages = clientSocket.getInputStream();

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

}