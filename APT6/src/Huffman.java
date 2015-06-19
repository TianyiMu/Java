public class Huffman {
	private String output="";
	private String input;
	private String[] dic;
      public String translate(String encoded, String[] dictionary) {
            input=encoded;
            dic=dictionary;
            while(input.length()!=0)
            {
            	find();
            }
            return output;
      }
      private String getChar(int idx)
      {
    	  return Character.toString ((char)(idx+65));
      }
      private void find()
      {
    	  for(int i=0;i<dic.length;i++)
    	  {
    		  int l=dic[i].length();
    		  if(l<=input.length()){
    		  if(input.substring(0, l).equals(dic[i]))
    		  {
    			  output=output+getChar(i);
    			  input=input.substring(l);
    			  break;
    		  }
    		  }
    	  }
      }
   }