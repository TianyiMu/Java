import java.util.*;

public class EmergencyNetwork {
	public int lagTime(int[] bosses) {
		return minLagTime(0, bosses);
    }
     
    int minLagTime(int me, int[] bosses) {
    	ArrayList<Integer> subords = new ArrayList<Integer>();
    
    	boolean sub=false;
    	for (int k = 0; k < bosses.length; k++) {
    		if (bosses[k] == me) {
    			sub=true;
    			int kTime = minLagTime(k, bosses); 
    			subords.add(kTime); 
    		}	
    	}
    	if(!sub)
    		return 0;
    	Collections.sort(subords);
    	Collections.reverse(subords);
    	for(int i=0;i<subords.size();i++)
    	{
    		subords.set(i, i+1+subords.get(i));
    	}
    	
    	return Collections.max(subords);
    }
}
