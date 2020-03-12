package client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

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
	
	public void sendUsername(String message) {
		System.out.println("Username sent: " + message);
		this.writer.println(message);
	}
	
	public void sendChat(String message) {
		System.out.println("Chat sent: " + message);
		this.writer.println(message);
	}
	
}
