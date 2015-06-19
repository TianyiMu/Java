import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class CricketWorldCup  {
	ArrayList<String> standing=new ArrayList<String>();
	ArrayList<Country> countryList=new ArrayList<Country>();
	HashMap<String,Country> table=new HashMap<String,Country>();
	
      public String[] standings(String[] teams, String[] eliminatedBy) {
    	  
    	  for(int i=0;i<teams.length;i++)
    	  {
    		  countryList.add(new Country(teams[i],eliminatedBy[i]));
    		  table.put(teams[i], new Country(teams[i],eliminatedBy[i]));
    	  }
    	  calc(teams,eliminatedBy);
    	  calcRest();
    	  String[] result=new String[standing.size()];
    	  for(int i=0;i<standing.size();i++)
    	  {
    		  result[i]=standing.get(i);
    	  }
    	  return result;
      }
      private void calcRest()
      {
    	  for(int i=0;i<standing.size();i++)
    	  {
    		  String name=standing.get(i);
    		  for(int k=0;k<countryList.size();k++)
    		  {
    			  if(standing.contains(countryList.get(k).win));
    			  else if(countryList.get(k).loss.equals(name))
    			  {
    				  standing.add(countryList.get(k).win);
    			  }
    		  }
    	  }
      }
      private void calc(String[] teams, String[] eliminatedBy)
      {
    	  HashMap<String,Integer> count= new HashMap<String,Integer>();
    	  for(String s:eliminatedBy)
    	  {
    		  if(count.containsKey(s)) {
    			  count.put(s,count.get(s) + 1);
    	        }
    	        else {
    	        	count.put(s, 1);
    	        }
    	  }
    	  while(count.size()!=0)
    	  {
    	  String temp="";
    	  int dup=0;
    	  for (String key : count.keySet()) {
    		    if(count.get(key)>dup)
    		    {
    		    	temp=key;
    		    	dup=count.get(key);
    		    }
    		    if((count.get(key)==dup)&&(!key.equals(""))&&(!temp.equals("")))
    		    {
    		    	
    		    	String old=table.get(temp).loss;
    		    	String ne=table.get(key).loss;
    		    	int idxOld=standing.indexOf(old);
    		    	int idxne=standing.indexOf(ne);
    		    	if(idxOld==-1)
    		    	{
    		    		temp=key;
        		    	dup=count.get(key);
    		    	}
    		    	else if(idxne==-1)
    		    	{
    		    		dup=count.get(key);
    		    	}
    		    	else if(idxOld<idxne)
    		    	{
    		    		dup=count.get(key);
    		    	}
    		    	else
    		    	{
    		    		temp=key;
        		    	dup=count.get(key);
    		    	}
    		    }
    		}
    	  if(!temp.equals(""))
    	  standing.add(temp);
    	  count.remove(temp);
    	  }
    	  
    	  
      }
      
      class Country
      {
    	  String win;
    	  String loss;
    	  
    	  Country(String w,String l)
    	  {
    		  this.win=w;
    		  this.loss=l;
    	  }
    	  public String getWin()
    	  {
    		  return this.win;
    	  }
    	  public String getLoss()
    	  {
    		  return this.loss;
    	  }
      }
   }