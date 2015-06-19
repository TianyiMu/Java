import java.util.HashMap;
import java.util.HashSet;

public class MatchingNGrams {
      public int totalMatches(String[] ngrams) {
    	  int count=0;
         for(int i=0;i<ngrams.length;i++)
         {
        	 for(int k=i+1;k<ngrams.length;k++)
        	 {
        		 if(compare(ngrams[i],ngrams[k]))
        			 count++;
        	 }
         }
         return count;
      }
      public boolean compare(String a,String b)
      {
    	  boolean result=true;
    	  HashMap<String, String> output=new HashMap<String, String>(a.length());
    	  HashSet<String> count=new HashSet<String>();
    	  for (int k = 0;k < a.length(); k++)
		  {
    		  String aTemp=Character.toString(a.charAt(k));
    		  String bTemp=Character.toString(b.charAt(k));
			  if(output.containsKey(aTemp))
			  {
				  if(!output.get(aTemp).equals(bTemp))
				  {
					  result=false;
					  break;
				  }

			  }
			  else
			  {
				  if(count.contains(bTemp))
				  {
					  result=false;
					  break;
				  }
				  count.add(bTemp);
				  output.put(aTemp, bTemp);
			  }
		  }
    	  return result;
    	  
    	  
    	  
      }
   }