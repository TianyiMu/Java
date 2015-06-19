public class DNAgcPercent
{
    public double percentage(String dnaStrand)
    {
    	int count=0;
            for(int i=0;i<dnaStrand.length();i++)
            {
            	if(dnaStrand.substring(i, i+1).equals("c")||dnaStrand.substring(i, i+1).equals("g"))
            	count++;
            }
            if(dnaStrand.length()==0)
            return 0;
            return count/(double)dnaStrand.length();
    }
}