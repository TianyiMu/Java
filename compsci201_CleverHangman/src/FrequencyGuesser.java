import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;


public class FrequencyGuesser implements IHangGuesser {

	private HangmanGame myGame;
	private int length;
	private ArrayList<String> words=new ArrayList<String>();
	private HashMap<String,Integer> myWordMap=new HashMap<String,Integer>(26);
	private HashSet<String> allchar;
	private boolean myDebug;
	public FrequencyGuesser(){
		myDebug = true;
	}
	public FrequencyGuesser(boolean debug){
		myDebug = debug;
	}
	@Override
	
	public void gameSetup(HangmanGame game) {
		myGame = game;
		length=myGame.getDisplay().length();
		fetch();
		
	}

	private void fetch()
	{
		words.clear();
		ArrayList<String> tempp=HangmanFileLoader.getAllWords(length);
		for(int i=0;i<tempp.size();i++)
		{
			words.add(tempp.get(i));
		}
		allchar=new HashSet<String>();
		for (String s : words)
		{
			HashSet<String> temp=new HashSet<String>();
			for(int i=0;i<s.length();i++)
			{
				temp.add(s.substring(i, i+1));
				allchar.add(s.substring(i, i+1));
			}
			for (String d : temp)
			{
				if(myWordMap.containsKey(d))
				{
					myWordMap.put(d, myWordMap.get(d)+1);
				}
				else
				{
					myWordMap.put(d,1);
				}
			}
		}
	}
	private char getBest(){
		int max=0;
		String guess="";
		for (String d : allchar)
		{
			if(!myWordMap.containsKey(d))
			{
				continue;
			}
			if(myWordMap.get(d)>max)
			{
				max=myWordMap.get(d);
				guess=d;
			}
			else if(myWordMap.get(d)==max)
			{
				if(d.compareToIgnoreCase(guess)<0)
				{
					guess=d;
				}
			}
		}
		return guess.toCharArray()[0];
	}
	private void removeTrue(char ss)
	{
		Iterator<String> it = words.iterator();
		while (it.hasNext())
		{
		    String s = it.next();
		    if (s.indexOf(ss)!=-1) {
		        it.remove();
		    }
		}
	}
	private boolean match(String s, String disp)
	{
		HashSet<String> curr=new HashSet<String>();
		for(int i=0;i<s.length();i++)
		{
			if(!disp.substring(i, i+1).equals("_"))
			{
				curr.add(disp.substring(i, i+1));
			}
		}
		for(int i=0;i<s.length();i++)
		{
			if(disp.substring(i, i+1).equals("_"))
			{
				if(curr.contains(s.substring(i, i+1)))
						{
					return false;
						}
			}
			else
			{
				if(!disp.substring(i, i+1).equals(s.substring(i, i+1)))
				{
					return false;
				}
			}
		}
		return true;
	}
	private void removeTemplate()
	{
		String dispWord = myGame.getDisplay();
		Iterator<String> it = words.iterator();
		while (it.hasNext())
		{
		    String s = it.next();
		    if (!match(s,dispWord)) {
		        it.remove();
		    }
		}
	}
	private void removeFalse(char ss)
	{
		Iterator<String> it = words.iterator();
		while (it.hasNext())
		{
		    String s = it.next();
		    if (s.indexOf(ss)==-1) {
		        it.remove();
		    }
		}
	}
	private void reCal()
	{
		myWordMap=new HashMap<String,Integer>(26);
		for (String s : words)
		{
			HashSet<String> temp=new HashSet<String>();
			for(int i=0;i<s.length();i++)
			{
				temp.add(s.substring(i, i+1));
			}
			for (String d : temp)
			{
				if(myWordMap.containsKey(d))
				{
					myWordMap.put(d, myWordMap.get(d)+1);
				}
				else
				{
					myWordMap.put(d,1);
				}
			}
		}
	}
	
	@Override
	public boolean nextGuess() {
		
		char guess=getBest();
		if (myDebug){
			System.out.printf("guessing %s using %c\n", myGame.getDisplay(),guess);
			System.out.println(words.size());
		}
		boolean play=myGame.makeGuess(guess);
		if(play)
		{
			removeTemplate();
			allchar.remove(""+guess);
			removeTrue(guess);
			
			reCal();
		}
		else
		{
			removeTemplate();
			removeFalse(guess);
			
			reCal();
			if(getBest()==guess)
			{
				allchar.remove(""+guess);
			}
		}
		return myGame.gameOverHung() || myGame.gameOverGuessed();
	}

	@Override
	public void gameOver() {

	}

}
