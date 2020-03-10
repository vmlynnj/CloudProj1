package server;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import model.ServerThread;
import model.UserThread;

import java.net.ServerSocket;

/**
 * Represents a server in a Client/Server setup. For our class, the port number
 * is 4225.
 * 
 * @author Victoria Jenkins, Justin Smith, Aaron Merrell
 */
public class Server {
	
	private static boolean isGameOver;
	
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
			
			while (isGameOver == false) {
				clientSocket = serverSocket.accept();
				new ServerThread(clientSocket).start();
			}
			
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
		} catch (Exception e) {
			System.err.println("IOException:  " + e + " -- most probably the server is already started.");
			System.exit(-1);
		}
		return serverSocket;
	}
	
	
	
	public void setIsGameOver(boolean isGameOver) {
		Server.isGameOver = isGameOver;
	}

}