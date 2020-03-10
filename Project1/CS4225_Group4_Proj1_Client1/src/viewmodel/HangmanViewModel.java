/**
 * 
 */
package viewmodel;

import client.Client;
import client.WriteThread;
import javafx.scene.control.SingleSelectionModel;

/**
 * @author jsmit124
 *
 * @version 1.0
 */
public class HangmanViewModel {

	public HangmanViewModel() {
		Client.startConnection();
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
		var socket = Client.socket;
		var writeThread = new WriteThread(socket);
		writeThread.sendMessage(text);
	}

}
