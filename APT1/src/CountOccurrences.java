public class CountOccurrences{
     public int numberOccurrences(int number, int digit) {
    	    String Num = new Integer(number).toString();
    	    String Digi = new Integer(digit).toString();
    	    int count=0;
    	    for (int i=0;i<Num.length();i++)
    	    {
    	    	if(Num.substring(i,i+1).equals(Digi))
    	    	{
    	    		count++;
    	    	}
    	    }
    	    return count;
     }
   }