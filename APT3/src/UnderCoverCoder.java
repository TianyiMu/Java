import java.util.HashMap;
import java.util.HashSet;

public class UnderCoverCoder {

	private HashMap<String, Integer> input;
      public int totalNotes(String[] clips, String[] notes) {
    	  countClips(clips);
    	  int total=0;
    	  for(int i=0;i<notes.length;i++)
    	  {
    		  boolean write=false;
    		  HashMap<String, Integer> output=new HashMap<String, Integer>(notes[i].length());
    		  output.clear();
    		  String temp=notes[i].toLowerCase();
    		  for (int k = 0;k < temp.length(); k++)
    		  {
    			  if(output.containsKey(Character.toString(temp.charAt(k))))
    			  {
    				  output.put(Character.toString(temp.charAt(k)), output.get(Character.toString(temp.charAt(k)))+1);
    			  }
    			  else if(Character.toString(temp.charAt(k)).equals(" "));
    			  else
    			  {
    				  output.put(Character.toString(temp.charAt(k)), 1);
    			  }
    		  }
    		  HashSet<String> count=new HashSet<String>();
    		  for (int k = 0;k < temp.length(); k++)
    		  {
    			  if(Character.toString(temp.charAt(k)).equals(" "));
    			  else
    				  count.add(Character.toString(temp.charAt(k)));
    		  }
    		  for (String s: count)
    		  {
    			  
    			  if(!input.containsKey(s))
    			  {
    				  write=false;
    				  break;
    			  }
    			  else if(input.containsKey(s)&&(output.get(s)>input.get(s)))
    			  {
    				  write=false;
    				  break;
    			  }
    			  else if(input.containsKey(s)&&(output.get(s)<=input.get(s)))
    				  write=true;
    		  }
    		  if(count.size()==0)
    			  write=true;
    		  if(write)
    			  total++;
    	  }
    	  return total;
      }
      public void countClips(String[] clips)
      {
    	  HashSet<String> count=new HashSet<String>();
    	  for(int i=0;i<clips.length;i++)
    	  {
    		  String temp=clips[i].toLowerCase();
    		  for (int k = 0;k < temp.length(); k++)
    		  {
    			  if(Character.toString(temp.charAt(k)).equals(" "));
    			  else
    				  count.add(Character.toString(temp.charAt(k)));
    		  }
    	  }
    	  input=new HashMap<String, Integer>(count.size());
    	  for(int i=0;i<clips.length;i++)
    	  {
    		  String temp=clips[i].toLowerCase();
    		  for (int k = 0;k < temp.length(); k++)
    		  {
    			  if(input.containsKey(Character.toString(temp.charAt(k))))
    			  {
    				  input.put(Character.toString(temp.charAt(k)), input.get(Character.toString(temp.charAt(k)))+1);
    			  }
    			  else if(Character.toString(temp.charAt(k)).equals(" "));
    			  else
    			  input.put(Character.toString(temp.charAt(k)), 1);
    		  }
    	  }
      }
   }