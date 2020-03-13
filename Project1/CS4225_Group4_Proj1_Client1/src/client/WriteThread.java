package client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import util.ClientActions;

public class WriteThread extends Thread {

	private Socket socket;
	private OutputStream output;
	private PrintWriter writer;
	
	public WriteThread(Socket socket) {
		this.socket = socket;
		
		try {
			this.output = this.socket.getOutputStream();
			this.writer = new PrintWriter(this.output, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void run() {

	}
	

	
	public void sendMessage(ClientActions action, String message) {
		System.out.println(action.toString() + message);
		this.writer.println(action.toString() + Client.ACTION_SPLIT + message);
	}
	
}
