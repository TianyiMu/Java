import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * You write code here to play a game of Hangman.
 * Some sample code provided at the start. You'll probably remove almost 
 * all of it (readString might stick around).
 * 
 * @author Tianyi Mu
 */

public class HangmanGame {

	public static void main(String args[]){
		HangmanGame game = new HangmanGame();
		game.play();
	}
	// Used for reading data from the console.
	Scanner myInput;
	
	public HangmanGame() {
		// Set up our read-from-console.
		myInput = new Scanner(
				new BufferedReader(new InputStreamReader(System.in)));
	}
	
	
	/**
	 * Get a line from the user and return the line as a String.
	 * 
	 * @param prompt is printed as an instruction to the user
	 * @return entire line entered by the user
	 */
	public String readString(String prompt) {
		System.out.printf("%s ", prompt);
		String entered = myInput.nextLine();
		return entered;
	}
	
	/**
	 * Play one game of Hangman. This should prompt
	 * user for parameters and then play a complete game.
	 * You'll likely want to call other functions from this one. 
	 * The existing code may provide some helpful examples.
	 */
	public void play() {
		
		 HangmanFileLoader data = new HangmanFileLoader();
		 int choice=5;
		 while(choice<0||choice>2)
		 {
		 String inputChoice = readString("Which word set? (enter \"0\" for default \"1\" for foods or \"2\" for names)");
		 choice = Integer.parseInt(inputChoice);
		 } //offers selections
		 int low=0;
		 int high=0;
		 if(choice==0) //identify selection
		 {
			 data.readFile("lowerwords.txt");
			 low=2;
			 high=20;
		 }
		 else if(choice==1) //identify selection
		 {
			 data.readFile("food.txt");
			 low=3;
			 high=10;
		 }
		 else if(choice==2) //identify selection
		 {
			 data.readFile("name.txt");
			 low=4;
			 high=10;
		 }
		 String inputLetter = readString("How many letters? (from "+low+" through "+high+")");
		 int letterCount = Integer.parseInt(inputLetter);
		 while(letterCount<low||letterCount>high) //prevent bad inputs
		 {
			 inputLetter = readString("How many letters? (from "+low+" through "+high+")");
			 letterCount = Integer.parseInt(inputLetter);
		 }
		 String inputGuess = readString("How many misses?");
		 int guessCount = Integer.parseInt(inputGuess);
		 String secretWord = data.getRandomWord(letterCount);
		 ArrayList <String> currentWord = new ArrayList <String>();
		 for (int i=1;i<=letterCount;i++) //setup the display for hangman's word
		 {
			 currentWord.add("_");
		 }
		 ArrayList <String> guessedWord = new ArrayList <String>();
		 //System.out.println(secretWord);
		 for(int i=0;i<currentWord.size();i++) //shows hangman's word with blanks
		 {
			 System.out.print(" "+currentWord.get(i)+" ");
		 }
		 System.out.println();
		 System.out.println("Misses left: "+ guessCount);
		 System.out.println("Guesses so far:");
		 boolean gameWon = false;
		 while (guessCount!=0) 
		 {
			 String guess = readString("Guess? ");
			 int match=0;
			 if(guess.length()==1)
			 {
				 if(secretWord.contains(guess))
					 match=1;
			 }
			 else
			 {
				 if(secretWord.equals(guess)) //case when the user guess the whole word at once
				 { gameWon=true;
				 	break;
				 }
			 }
			 if(match==1) 
			 {
				 int index = secretWord.indexOf(guess);
				 while(index>=0) //changes the display of the hangman word
				 {
					 currentWord.remove(index);
					 currentWord.add(index, guess);
					 index = secretWord.indexOf(guess, index+1);
				 }
			 } 
			 else 
			 {
				 guessCount--;
				 System.out.println("No");
				 if(guessedWord.contains(guess))
					 guessCount++;
				 if(!guessedWord.contains(guess)) // add the guess to "guesses so far"
					 guessedWord.add(guess);
			 }
			 if(!currentWord.contains("_")) //when there is no blanks in the hangman word
			 {
				 guessCount=0;
				 gameWon=true;
				 break;
			 }
			 for(int i=0;i<currentWord.size();i++) // update the display of hangman word
			 {
				 System.out.print(" "+currentWord.get(i)+" ");
			 }
			 System.out.println();
			 System.out.println("Misses left: "+ guessCount);
			 System.out.print("Guesses so far:");
			 for(int i=0;i<guessedWord.size();i++)
			 {
				 System.out.print(" "+guessedWord.get(i)+" ");
			 }
			 System.out.println();
		 }
		 if (!gameWon) {
			 System.out.println("you lost, secret word was " + secretWord);
		 }	 
		 else
			 System.out.println("Win! The word is " + secretWord);
		 
	}
}
