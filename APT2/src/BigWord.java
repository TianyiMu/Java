import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BigWord {
      public String most(String[] sentences) 
      {
    	  ArrayList <String> allWord = new ArrayList <String>();
    	  HashSet <String> cache = new HashSet<String>();
    	  for(int i=0;i<sentences.length;i++)
    	  {
    		  String[] word = sentences[i].split(" ");
    		  for(int j=0;j<word.length;j++)
    		  {
    			  allWord.add(word[j].toLowerCase());
    			  cache.add(word[j].toLowerCase());
    		  }
    	  }
    	  HashMap <String, Integer> time = new HashMap<String, Integer>(cache.size());
    	  for(int i=0;i<allWord.size();i++)
    	  {
    		  if(time.containsKey(allWord.get(i)))
    		  {
    			  time.put(allWord.get(i), time.get(allWord.get(i))+1);
    		  }
    		  else
    			  time.put(allWord.get(i), 1);
    	  }
    	  String most="";
    	  int count=0;
    	  for(int i=0;i<allWord.size();i++)
    	  {
    		  if(time.get(allWord.get(i))>count)
    		  {
    			  count=time.get(allWord.get(i));
    			  most=allWord.get(i);
    		  }
    	  }
    	  return most;
      }
  }