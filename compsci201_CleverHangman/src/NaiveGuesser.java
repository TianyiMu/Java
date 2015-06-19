
/**
 * This guesser uses a static ordering of letters to guess a hangman word,
 * the ordering given is based on standard frequency of letters in English
 * @author Owen Astrachan
 *
 */
public class NaiveGuesser implements IHangGuesser {
	private HangmanGame myGame;
	char[] myLetters;
	int myIndex;
	boolean myDebug;
	
	public NaiveGuesser(){
		myDebug = true;
	}
	
	public NaiveGuesser(boolean debug){
		myDebug = debug;
	}
	
	@Override
	public void gameSetup(HangmanGame game) {
		myGame = game;
		//int l=myGame.mySecret.length();
		String alpha="";
		/*if(l==5)
		{
			//alpha = "seaoiuyhrltndcpmgbkfwvzxjq";
			alpha = "searoiltnudcypmhgbkfwvzxjq";
		}
		else if(l==6)
		{
			//alpha = "eaiousyrlntdcmpghbkfwvzxjq";
			alpha = "esariolntducmpghbykfwvzxjq";
		}
		else if(l==7)
		{
			//alpha = "eiaousrntldcgpmhbyfkwvzxjq";
			alpha = "esiarntolducgpmhbyfkwvzxjq";
		}
		else if(l==8)
		{
			//alpha = "eiaousrntldcgmphbyfkwvzxqj";
			alpha = "esiarntoldcugmphbyfkwvzxqj";
		}
		else if(l==9)
		{
			//alpha = "eiaousrntlcdgmphbyfvkwzxqj";
			alpha = "esirantolcdugmphbyfvkwzxqj";
		}
		else if(l==10)
		{
			//alpha = "eioausrntlcdgmphbyfvkwzxqj";
			alpha = "eisrantolcdugmphbyfvkwzxqj";
		}
		else*/
			//alpha = "esiarntolcdupmghbyfvkwzxqj";
			alpha = "etaoinshrdlucmfwypvbgkqjxz";
		myLetters = new char[alpha.length()];
		int k=0;
		for(char ch : alpha.toCharArray()){
			myLetters[k++] = ch;
		}
		myIndex = 0;
	}

	@Override
	public boolean nextGuess() {
		char ch = myLetters[myIndex++];
		if (myDebug){
			System.out.printf("guessing %s using %c\n", myGame.getDisplay(),ch);
		}
		boolean miss = myGame.makeGuess(ch);
		
		return myGame.gameOverHung() || myGame.gameOverGuessed();
	}

	@Override
	public void gameOver() {
		// nothing to do here? 
	}
}
