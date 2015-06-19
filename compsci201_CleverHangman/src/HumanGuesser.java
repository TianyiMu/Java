import java.util.*;

/**
 * Human plays hangman while conforming to standard hangman interface,
 * allowing for changing guessing strategy from auto to human in
 * the context of analyzing tradeoffs in hangman
 * @author ola
 *
 */
public class HumanGuesser implements IHangGuesser {
	
	private static Scanner myInput = new Scanner(System.in);
	private HangmanGame myGame;
	private TreeSet<Character> myCharsGuessed;
	private ArrayList<String> words=new ArrayList<String>();
	private int length;
	private HashMap<String,ArrayList<String>> myWordMap=new HashMap<String,ArrayList<String>>(26);
	private HashSet<String> tempSet=new HashSet<String>();
	private String disp="";
	private int possibleCount=0;
	private int missLeft=0;
	private static char BLANK = '_';
	private boolean myDebug;
	private String secretWord="";
	
	public HumanGuesser() {
		myCharsGuessed = new TreeSet<Character>();
		myDebug = true;
	}
	public HumanGuesser(boolean debug){
		myDebug = debug;
	}
	public String getSecret(){
    	if (gameOverHung() || gameOverGuessed()){
    		return secretWord;
    	}
    	return "game is not over, you know "+disp;
    }
	private void fetch()
	{
		words.clear();
		ArrayList<String> tempp=HangmanFileLoader.getAllWords(length);
		for(int i=0;i<tempp.size();i++)
		{
			words.add(tempp.get(i));
		}
		
	}
	
	/**
	 * Get a line from the user and return the line as a String
	 * @param prompt is printed as an instruction to the user
	 * @return entire line entered by the user
	 */
	protected static String readString(String prompt) {
		System.out.printf("%s ",prompt);
		String entered = myInput.nextLine();
		return entered;
	}
	
	@Override
	public void gameSetup(HangmanGame game) {
		myGame = game;
		disp=myGame.getDisplay();
		length=myGame.getDisplay().length();
		missLeft=myGame.missesLeft();
		fetch();
	}

	public boolean gameOverHung() {
    	return missLeft<=0;
    }
	public boolean gameOverGuessed(){
    	for(int k=0; k < disp.length(); k++){
    		if (disp.charAt(k) == BLANK){
    			return false;
    		}
    	}
    	return true;
    }
	private void getMap(String guessed)
	{
		myWordMap.clear();
		tempSet.clear();
		for(int i=0;i<words.size();i++)
		{
			String temp=words.get(i);
			String pattern="";
			for(int k=0;k<length;k++)
			{
				if(temp.substring(k, k+1).equals(guessed))
					pattern=pattern+guessed;
				else
					pattern=pattern+"_";
			}
				for(int j=0;j<disp.length();j++)
				{
					if(!disp.substring(j, j+1).equals("_"))
					{
						pattern=pattern.substring(0, j)+disp.substring(j, j+1)+pattern.substring(j+1);
					}
				}
				if(myWordMap.containsKey(pattern))
				{
					ArrayList<String> templist=myWordMap.get(pattern);
					templist.add(temp);
					tempSet.add(pattern);
					myWordMap.put(pattern, templist);
				}
				else
				{
					ArrayList<String> templist=new ArrayList<String>();
					templist.add(temp);
					tempSet.add(pattern);
					myWordMap.put(pattern, templist);
				}
			
		}
	}
	private void getHighest()
	{
		int count=0;
		String old=disp;
		ArrayList<String> templist=new ArrayList<String>();
		
		for(String s: tempSet)
		{
			int length=myWordMap.get(s).size();
			if(length>count)
			{
				count=length;
				templist=myWordMap.get(s);
				disp=s;
			}
		}
		words=templist;
		possibleCount=count;
		if(old.equals(disp))
		{
			missLeft--;
		}
		secretWord=words.get(0);
	}
	
	
	@Override
	public boolean nextGuess() {	
		System.out.printf("letters guessed: ");
		for(char ch : myCharsGuessed){
			System.out.printf("%c ",ch);
		}
		System.out.println();
		System.out.println("misses left: "+missLeft);
		
		System.out.printf("guess this word: %s\n",disp);
		String guess = readString("your guess: ");
		if(!myCharsGuessed.contains(guess.charAt(0)))
			{
				getMap(guess);
				getHighest();
			}
		if(myDebug)
		{
			for(String s: tempSet)
			{
				System.out.println(s+" "+myWordMap.get(s).size());
			}
			System.out.println("(Secret Word is:"+secretWord+") "+"Words possible "+possibleCount);
			
		}
		
		
		//boolean miss = myGame.makeGuess(guess.charAt(0));
		myCharsGuessed.add(guess.charAt(0));
		return gameOverGuessed() || gameOverHung();
	}

	@Override
	public void gameOver() {
		if (! gameOverGuessed() && ! gameOverHung()) {
			throw new IllegalStateException("game not over");
		}
	}
}
