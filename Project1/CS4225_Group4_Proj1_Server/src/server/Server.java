package server;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import model.GameThread;
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
		Server server = new Server();
		server.run();
		
		
	}
	
	public void run () {
		try (ServerSocket server = new ServerSocket(PORT)){
			Socket clientSocket;
			
			while(true) {
				clientSocket = server.accept();
				System.out.println("Connected to server");
				UserThread user = new UserThread(clientSocket, this);
				user.start();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}