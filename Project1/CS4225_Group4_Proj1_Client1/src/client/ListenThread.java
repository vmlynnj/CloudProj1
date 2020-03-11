package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import viewmodel.HangmanViewModel;

public class ListenThread extends Thread{

	private Socket socket;
	
	private InputStream input;
	
	private BufferedReader reader;
	
	public static String MESSAGE = null;
	
	public ListenThread(Socket socket) {
		this.socket = socket;
		
		try {
			this.input = this.socket.getInputStream();
			this.reader = new BufferedReader(new InputStreamReader(this.input));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		while(true)
		{
			System.out.println("In listener");
			try {
				String message = this.reader.readLine();
				HangmanViewModel.addMessage(message);
				
				//TODO HANDLE SERVER MESSAGES
				System.out.println("server Said: "+message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		}
	} 
}
