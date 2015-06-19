import java.util.*;


public class CompressedTrieLexicon extends TrieLexicon {

	
	 @Override
	 public void load(ArrayList<String> list) 
	 {
	 	super.load(list);
	 	 compress(myRoot);
	 	 
	 	}
	 
	 @Override
	 public void load(Scanner s) 
	 {
	    	super.load(s);
	    	compress(myRoot);
	    }
	 
	 public void compress(Node first)
	 {
		 if(first==null)
	    		return;
	    	Map<Character, Node> myChildren = first.children;
	    	if(myChildren.size()==0)
	    		return;
	    	if(myChildren.size()==1&&!first.isWord)
	    	{
	    		Character[] key = myChildren.keySet().toArray(new Character[0]);
	    		Node child = myChildren.get(key[0]);
	    		first.info+=child.info;
	    		first.children=child.children;
	    		first.isWord = (first.isWord||child.isWord);
	    		compress(first);
	    	}
	    	Character[] allKey = myChildren.keySet().toArray(new Character[0]);
	    	for (int i = 0; i < allKey.length; i++)
			compress(myChildren.get(allKey[i]));
	    	
	 } 
	 
	 @Override
	 public LexStatus wordStatus(StringBuilder s){
	        Node t = myRoot;
	        for (int k = 0; k < s.length(); k++) {
	            char ch = s.charAt(k);
	            t = t.children.get(ch);
	            if (t == null)
	                return LexStatus.NOT_WORD; // no path below? done
	            String inf=t.info;
	            int infLength=inf.length();
	            if(infLength>=2)
	            {
	            		if(infLength+k>=s.length())
	            		{
	            			
	            			if(infLength+k>=s.length())
	            			{
	            				for(int i=k;i<s.length(); i++)
	            				{
	            					if(inf.charAt(i-k)!=s.charAt(i))
	        							return LexStatus.NOT_WORD;
	            				}
	            			}
	            			if(infLength+k==s.length()&&t.isWord)
	            				return LexStatus.WORD;
	            			return LexStatus.PREFIX;
	            		}
	            		else
	            		{
	            			for(int i=k;i<infLength; i++)
            				{
            					if(inf.charAt(i-k)!=s.charAt(i))
        							return LexStatus.NOT_WORD;
            				}
	    					k=k+infLength-1;
	            		}
	            }
	            
	        }
	        return t.isWord ? LexStatus.WORD : LexStatus.PREFIX; 
	    }
	 
	 @Override
	    public LexStatus wordStatus(String s) {
	        return wordStatus(new StringBuilder(s));
	    }
	 
}
