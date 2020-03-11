package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WriteThread extends Thread {

	private Socket socket;
	private InputStream input;
	private OutputStream output;
	private ObjectOutputStream objOut;
	
	public WriteThread(Socket socket) {
		this.socket = socket;
		
		try {
			this.input = this.socket.getInputStream();
			this.output = this.socket.getOutputStream();
			this.objOut = new ObjectOutputStream(this.output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void run() {
		while (true) {
//			Console console = System.console();
			 
//	        String message = console.readLine("\nEnter Something: ");
//	        try {
//				this.sendMessage(message);
//			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	
	public void sendUsername(String message) throws IOException {
		this.objOut.writeObject(message);
		System.out.println("Username sent: " + message);
		this.objOut.flush();
	}
	
	public void sendChat(String message) throws IOException {
		this.objOut.writeObject(message);
		System.out.println("Chat sent: " + message);
		this.objOut.flush();
	}
	
}
