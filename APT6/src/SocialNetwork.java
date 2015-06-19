import java.util.*;
import java.util.Map.Entry;

public class SocialNetwork {
     private HashMap<String,ArrayList<String>> peopleMap=new HashMap<String,ArrayList<String>>();
	private HashSet<String> getPath=new HashSet<String>();
	private int count=0;
	public int butterflies(String[] people) {
    	 
		setup(people);
    	 
		for(Map.Entry<String, ArrayList<String>> entry : peopleMap.entrySet()) {
			String key = entry.getKey();
			  ArrayList<String> value = entry.getValue();
			  boolean check=false;
			  if(value.size()<2)
				  continue;
			  for(int i=0;i<value.size()-1;i++)
			  {
				  for(int k=i+1;k<value.size();k++)
				  {
					  if(!checkPath(value.get(i),value.get(k), key))
						  check=true;
				  }
			  }
			  if(check)
				  count++;
			}
         return count;
     }
	private boolean checkPath(String a,String b, String remove)
	{
		getPath.clear();
		ArrayList<String> aList=peopleMap.get(a);
		for(int i=0;i<aList.size();i++)
		{
			if(!aList.get(i).equals(remove))
			getPath.add(aList.get(i));
		}
		goDeeper(true,remove);
		for(String s:getPath)
		{
			if(s.equals(b))
				return true;
		}
		
		return false;
	}
	private void goDeeper(boolean flag, String remove)
	{
		while(flag)
		{
			flag=false;
			HashSet<String> temp=new HashSet<String>();
			for(String s:getPath)
				temp.add(s);
			
			for(String s:temp)
			{
				ArrayList<String> temptList=peopleMap.get(s);
				for(int i=0;i<temptList.size();i++)
				{
					if(!getPath.contains(temptList.get(i))&&!temptList.get(i).equals(remove))
					{
						flag=true;
						getPath.add(temptList.get(i));
					}
				}
			}
			
		}
	}
	
	private void setup(String[] people)
	{
		for(int i=0;i<people.length;i++)
		{
			ArrayList<String> tempList=new ArrayList<String>();
			String[] temp=people[i].split(" ");
			for(String s:temp)
			{
				tempList.add(s);
			}
			peopleMap.put(i+"", tempList);
				
		}
	}
	
  }