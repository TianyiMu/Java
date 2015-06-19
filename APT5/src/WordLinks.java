import java.util.ArrayList;
import java.util.List;

public class WordLinks {
    public String isLinked(String[] inner, String from, String to) {
    	
    	List<String> wordlist=new ArrayList<String>();
    	for(int i=0;i<inner.length;i++)
    	{
    		wordlist.add(inner[i]);
    	}
    	boolean link = isLinked(wordlist, from, to, 0);
    	if(link)
    		return "ladder";
    	else
    		return "none";
    }
    public boolean isStep(String from, String to)
    {
    	int count=0;
    	for(int i=0;i<from.length();i++)
    	{
    		String a=from.substring(i, i+1);
    		String b=to.substring(i, i+1);
    		if(!a.equals(b))
    			count++;
    	}
    	if(count>1)
    	return false;
    	else
    		return true;
    }
    public boolean isLinked(List<String> words, String from, String to, int links){
    		if(isStep(from,to) && links > 0) {
    		return true;
    		}
    		for(String s : words){
    		if(isStep(from,s)){
    		List<String> copy = new ArrayList<String>(words);
    		copy.remove(s);
    		if(isLinked(copy, s, to, links+1)) {
    		return true;
    		}
    		}
    		}
    		return false;
    		}
  }