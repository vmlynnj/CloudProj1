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

	private static ListProperty<String> messages;
	
	public HangmanViewModel(ListProperty<String> listProperty) {
		Client.startConnection();
		HangmanViewModel.messages = listProperty;
	}
	
	
	public static void addMessage(String message) {
		HangmanViewModel.messages.add(message);
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
		System.out.println("Submit chat button clicked");
		//Client.sendChat(text);
		HangmanViewModel.messages.add(text);
		Client.sendChat(text);
	}

}
