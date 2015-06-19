import java.util.*;
public class Erdos {
	private TreeMap<String,Integer> finalList=new TreeMap<String,Integer>();
	private String[] allName;
    public String[] number(String[] pubs) {
    	allName=pubs;
    	finalList.put("ERDOS", 0);
      
    	findNumber();
      addAll();
      String[] output=new String[finalList.size()];
      int idx=0;
      for(Map.Entry<String,Integer> entry : finalList.entrySet()) {
    	  String key = entry.getKey();
    	  Integer value = entry.getValue();
    	  if(value==-1)
    		  output[idx]=key;
    	  else
    		  output[idx]=key + " " + value;
    	  idx++;
    	}
      return output;
      
    }
    private void findNumber()
    {
    	int size=0;
    	while (size<allName.length)
    	{
    		for(int i=0;i<allName.length;i++)
    		{
    			String[] tempName=allName[i].split(" ");
    			int number=1000000;
    			for(int k=0;k<tempName.length;k++)
    			{
    				if(finalList.containsKey(tempName[k])&&finalList.get(tempName[k]) < number)
    				{
    					number=finalList.get(tempName[k]);
    				}
    			}
    			if(number<1000000)
    			{
    				for(int k=0;k<tempName.length;k++)
        			{
    					if (!finalList.containsKey(tempName[k])||finalList.get(tempName[k]) > number)
    						finalList.put(tempName[k], number+ 1);
        			}
    			}
    		}
    		size++;
    	}
    }
    private void addAll()
    {
    	for(int i=0;i<allName.length;i++)
    	{
    		String thisPub=allName[i];
			String[] temp=thisPub.split(" ");
			for(int j=0;j<temp.length;j++)
			{
				if(!finalList.containsKey(temp[j]))
					finalList.put(temp[j], -1);
			}
    	}
    }
  }