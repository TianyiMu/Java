import java.util.Scanner;


/**
 * Runs CleverHangman
 *
 */
public class HangmanExecuter {
	
	public static void main(String[] args) {

		System.out.println("Welcome to CleverHangman!");
		Scanner sc=new Scanner(System.in); 
		System.out.println("Word length:");
		String length=sc.nextLine(); 
		int intLength = Integer.parseInt(length.substring(0, 1));
		System.out.println("Allowed miss:");
		String miss=sc.nextLine(); 
		int intMiss = Integer.parseInt(miss.substring(0, 1));
		HangmanGame game = new HangmanGame(intLength,intMiss);
		CleverHangman guesser = new CleverHangman(); //with debug mode on
		guesser.gameSetup(game);
		while (true){
			
			if (guesser.nextGuess()){
				break;
			}

		}
		
		if (guesser.gameOverHung()) {
			System.out.println("you lose!");
		}
		else if (guesser.gameOverGuessed()) {
			System.out.println("you win!");
		}
		System.out.printf("secret word is %s\n",guesser.getSecret());
	}
}
