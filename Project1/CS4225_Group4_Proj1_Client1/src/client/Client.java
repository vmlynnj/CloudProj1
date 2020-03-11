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
	
	public static Socket socket = null;
	public static ListenThread listen;
	public static WriteThread write;

	public static void startConnection()
	{	
		Socket clientSocket = null;

		try {
			clientSocket = new Socket(HOST, PORT);
			Client.socket = clientSocket;
			
			//listen = new ListenThread(clientSocket);
			//listen.start();
			write = new WriteThread(clientSocket);
			write.start();
			System.out.println("Client started on port " + PORT);

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
	
	public static void sendChat(String message) {
		try {
			write.sendMessage(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}