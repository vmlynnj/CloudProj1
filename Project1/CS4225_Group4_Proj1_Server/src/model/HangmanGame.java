/**
 * 
 */
package model;

import java.util.Random;

import io.FileReader;

/**
 * Controlls the hangman game
 * @author jsmit124
 *
 * @version 1.0
 */
public class HangmanGame {
	private String wordToGuess;
	private String hiddenWord;
	private boolean gameWon;
	private boolean gameLost;
	
	private int incorrectGuesses;
	

	/**
	 * Instantiates a new HangmanGame object
	 * 
	 * @precondition none
	 * @postcondition this.wordToGuess EQUALS random word from text file
	 * 				AND this.hiddenWord EQUALS this.wordToGuess.replaceAll("[a-zA-Z]", "_")
	 * 				AND this.incorrectGuess EQUALS 0
	 * 				AND this.gameWon EQUALS false
	 * 				AND this.gameLost EQUALS false
	 */
	public HangmanGame() {
		this.setupGame();
		
		this.incorrectGuesses = 0;
		this.gameWon = false;
		this.gameLost = false;
	}
	

	/**
	 * Iterates through word file, chooses a random word, and sets this.wordToGuess and this.hiddenWord
	 */
	private void setupGame() {
		var words = FileReader.readFileWords("words.txt");
		Random randomGenerator = new Random();
		var randomWordNumber = randomGenerator.nextInt(words.size());
		
		this.wordToGuess = words.get(randomWordNumber);
		this.hiddenWord = this.wordToGuess.replaceAll("[a-zA-Z]", "_");
	}
	
	
	/**
	 * Handles the user guessing a letter.
	 * Takes the letter guessed and if the letter exists within this.wordToGuess,
	 * the method will replace all of the underscores within this.hiddenWord with
	 * the letter guessed and return true; otherwise returns false
	 * 
	 * @precondition letter !EQUAL null AND letter !EQUAL empty
	 * @postcondition if this.wordToGuess.contains(letter), then respective location
	 * 				  in this.hiddenWord is replaced with the guessed letter
	 * 				  ; otherwise, nothing is changed
	 * 
	 * @param letter
	 * 			The letter guessed by the user
	 * 
	 * @return result
	 * 		True if this.wordToGuess.contains(letter); False otherwise
	 */
	public boolean guessLetter(String letter) {
		boolean result;
		StringBuilder sb = new StringBuilder();
		
		if (this.wordToGuess.toLowerCase().contains(letter.toLowerCase())) {
			var currLetterIndex = 0;
			var answerLetters = this.wordToGuess.split("");
			for (var currLetter : answerLetters) {
				if (currLetter.toLowerCase().equals(letter.toLowerCase())) {
					sb.append(letter);
				} else {
					sb.append(this.hiddenWord.charAt(currLetterIndex));
				}
				currLetterIndex++;
			}
			result = true;
			this.hiddenWord = sb.toString();
		} else {
			this.incorrectGuesses++;
			result = false;
		}
		
		this.checkForWinLoss();
		return result;
	}
	
	
	/*
	 * Returns true if this.hiddenWord EQUALS this.wordToGuess;
	 * 		   false otherwise
	 */
	private void checkForWinLoss() {
		if (this.hiddenWord.toLowerCase().equals(this.wordToGuess.toLowerCase())) {
			this.gameWon = true;
		} else if (this.incorrectGuesses == 6) {
			this.gameLost = true;
		}
	}
	
	/**
	 * Returns the word to guess
	 * @return this.wordToGuess
	 */
	public String getWordToGuess() {
		return this.wordToGuess;
	}
	
	/**
	 * Returns the current state of the hidden word
	 * @return this.hiddenWord
	 */
	public String getHiddenWord() {
		return this.hiddenWord;
	}

	
	/**
	 * Returns true if the game has been won; false otherwise
	 * @return this.gameWon
	 */
	public boolean isGameWon() {
		return this.gameWon;
	}

	
	/**
	 * Returns true if the game has been lost; false otherwise
	 * @return this.gameLost
	 */
	public boolean isGameLost() {
		return this.gameLost;
	}

	
	/**
	 * Returns the current count of the incorrect guesses
	 * @return this.incorrectGuesses
	 */
	public int getIncorrectGuesses() {
		return this.incorrectGuesses;
	}
	
	/**
	 * Sets the incorrect guesses
	 * @param incorrectGuesses the amount of incorrect guesses
	 */
	public void setIncorrectGuesses(int incorrectGuesses) {
		this.incorrectGuesses = incorrectGuesses;
	}
	/**
	 * Sets the word that is being guessed
	 * @param wordToGuess the word that is being guessed
	 */
	public void setWordToGuess(String wordToGuess) {
		this.wordToGuess = wordToGuess;
	}


	/**
	 * Sets the hidden word
	 * @param hiddenWord sets the hidden word
	 */
	public void setHiddenWord(String hiddenWord) {
		this.hiddenWord = hiddenWord;
	}


	/**
	 * Sets if the game is won or not
	 * @param gameWon whether or not the game is run
	 */
	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}


	/**
	 * Sets if the game is lost
	 * @param gameLost whether or not the game is lost
	 */
	public void setGameLost(boolean gameLost) {
		this.gameLost = gameLost;
	}




}
