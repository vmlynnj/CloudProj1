package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A thread for the server
 * @author Victoria Jenkins, Justin Smith, Aaron Merrell
 *
 */
public class ServerThread extends Thread {

	private Socket socket;

	/**
	 * Creates a thread to handle the passed in socket
	 * 
	 * @precondition clientSocket != null
	 * 
	 * @param clientSocket The client to serve
	 */
	public ServerThread(Socket clientSocket) {
		this.socket = clientSocket;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Runs the application
	 */
	public void run() {
		InputStream incomingMessages = null;
		OutputStream outgoingMessages = null;
		try {
			incomingMessages = this.socket.getInputStream();
			outgoingMessages = this.socket.getOutputStream();

			var objIn = new ObjectInputStream(incomingMessages);
			var objOut = new ObjectOutputStream(outgoingMessages);


		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			this.socket.close();
			incomingMessages.close();
			outgoingMessages.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
