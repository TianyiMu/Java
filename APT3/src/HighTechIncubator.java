import java.util.HashSet;
import java.util.TreeSet;

public class HighTechIncubator {
	private HashSet<String> log=new HashSet<String>();
	private HashSet<String> log2=new HashSet<String>();
	private TreeSet<String> name=new TreeSet<String>();
      public String[] shameList(String[] location1,String[] location2,String[] location3) 
      {
    	  record(location1);
    	  comp(location2);
    	  for(int i=0;i<location3.length;i++)
    	  {
    		  if(log.contains(location3[i])||log2.contains(location3[i]))
    		  {
    			  name.add(location3[i]);
    		  }
    	  }
    	  String[] list=new String[name.size()];
    	  int i=0;
    	  for(String s: name)
    	  {
    		  list[i]=s;
    		  i++;
    	  }
    	  return list;
      }
      public void record(String[] loc)
      {
    	  for(int i=0;i<loc.length;i++)
    	  {
    		  log.add(loc[i]);
    	  }
      }
      public void comp(String[] loc)
      {
    	  for(int i=0;i<loc.length;i++)
    	  {
    		  if(log.contains(loc[i]))
    		  {
    			  name.add(loc[i]);
    		  }
    		  else
    			  log2.add(loc[i]);
    	  }
      }
   }