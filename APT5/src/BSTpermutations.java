import java.util.HashSet;

public class BSTpermutations 
{
      public long howMany(int[] values) 
      {
	    	  int n = values.length;
	    	  return count(n,0);
      }
      public long count(long n, long ret)
      {
    	  		if(n < 2)
    	  				return 1; 
    	  		else if(n==3)
    	  			return 5;
    	  		else if(n==4)
    	  			return 14;
    	  		else
    	  		{
		    	  for(long l =0; l<n; l++)
		    	  {
			    	  long left = count(l,0);
			    	  long right = count(n-l-1,0);
			    	  ret += left*right;
		    	  }
		    	  return ret;
    	  		}
	    	
      }
      
   }