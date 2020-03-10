package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ListenThread extends Thread{

	private Socket socket;
	
	private InputStream input;
	private OutputStream output;
	
	public ListenThread(Socket socket) {
		this.socket = socket;
		
		try {
			this.input = this.socket.getInputStream();
			this.output = this.socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		while(true)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.input));
			String serverMessage = "";
			try {
				serverMessage = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//TODO HANDLE SERVER MESSAGES
			System.out.println(serverMessage);
		}
	} 
}
