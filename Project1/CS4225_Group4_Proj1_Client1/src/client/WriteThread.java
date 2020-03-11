package client;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WriteThread extends Thread {

	private Socket socket;
	private InputStream input;
	private OutputStream output;
	
	public WriteThread(Socket socket) {
		this.socket = socket;
		
		try {
			this.input = this.socket.getInputStream();
			this.output = this.socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	
	
	public void sendMessage(String message) throws IOException {
		var objOut = new ObjectOutputStream(this.output);
		objOut.writeObject(message);
		System.out.println("Message sent: " + message);
		objOut.flush();
	}
	
}
