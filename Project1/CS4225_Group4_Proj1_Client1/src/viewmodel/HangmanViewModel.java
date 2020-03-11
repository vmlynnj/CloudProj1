/**
 * 
 */
package viewmodel;

import java.io.IOException;
import java.util.List;

import client.Client;
import client.WriteThread;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.SingleSelectionModel;

/**
 * @author jsmit124
 *
 * @version 1.0
 */
public class HangmanViewModel {

	private ListProperty<String> messages;
	
	public HangmanViewModel(ListProperty<String> listProperty) {
		Client.startConnection();
		this.messages = listProperty;
	}
	
	
	public void addMessage(String message) {
		this.messages.add(message);
	}
	/**
	 * @param selectionModel
	 */
	public void sendGuess(SingleSelectionModel<?> selectionModel) {
		//TODO
		
		System.out.println("Submit guess button clicked");
	}

	/**
	 * @param text
	 */
	public void sendChat(String text) {
		// TODO Auto-generated method stub
		System.out.println("Submit chat button clicked");
		this.messages.add(text);
		var socket = Client.socket;
		var writeThread = new WriteThread(socket);
		try {
			writeThread.sendMessage(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
