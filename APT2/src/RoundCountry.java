public class RoundCountry {
    public int minBorders(int[] x, int[] y, int[] r,int x1, int y1, int x2, int y2) {
        // you write code here
    	int count=0;
    	for (int i =0; i<x.length; i++)
    	{
    		int check1=0;
    		int check2=0;
    		
    		int r1=(int) Math.sqrt((x1-x[i])*(x1-x[i])+(y1-y[i])*(y1-y[i]));
    		int r2=(int) Math.sqrt((x2-x[i])*(x2-x[i])+(y2-y[i])*(y2-y[i]));
    		if (r1<r[i])
    			check1=1;
    		if (r2<r[i])
    			check2=1; 
    		if(check1!=check2)
    			count++;
    		
    	}
    	return count;
    }
 }