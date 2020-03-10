/**
 * 
 */
package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author jsmit124
 *
 * @version 1.0
 */
public class FileReader {
	
	public static String[] readFileWords(String fileName) {
		ArrayList<String> words = new ArrayList<String>();
		
		try {
		File openFile = new File(fileName);
		Scanner fileReader = new Scanner(openFile);
		
		while (fileReader.hasNextLine()) {
			String data = fileReader.nextLine();
			words.add(data);
		}
		fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error reading file.");
			e.printStackTrace();
		}
		
		return (String[]) words.toArray();
	}
}
