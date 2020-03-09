package server;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import model.ServerThread;

import java.net.ServerSocket;

/**
 * Represents a server in a Client/Server setup. For our class, the port number
 * is 4225.
 * 
 * @author Victoria Jenkins, Justin Smith, Aaron Merrell
 */
public class Server {

	private static final int PORT = 4225;

	/**
	 * Main entry point of server; must run this program first.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket clientSocket = null;

		serverSocket = start(serverSocket);
		try {
			while (true) {
				clientSocket = serverSocket.accept();
				new ServerThread(clientSocket).start();
			}
		} catch (SocketTimeoutException e) {

		} catch (IOException e) {

		}

		try {
			serverSocket.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * Starts the server. Server will timeout after 100 seconds.
	 * 
	 * @param serverSocket the server socket
	 * 
	 * @return the server socket
	 */
	private static ServerSocket start(ServerSocket serverSocket) {
		try {
			serverSocket = new ServerSocket(PORT);
			serverSocket.setSoTimeout(100000);
		} catch (Exception e) {
			System.err.println("IOException:  " + e + " -- most probably the server is already started.");
			System.exit(-1);
		}
		return serverSocket;
	}

}