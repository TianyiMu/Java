import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.TreeMap;


public class OlympicSort {
	TreeMap<String, ArrayList<Integer>> table = new TreeMap<String, ArrayList<Integer>>();
	public String[] standings(String[] results) {
	   
      for(int i=0;i<results.length;i++)
      {
    	  String[] temp=results[i].split(" ");
    	  for(int j=0;j<temp.length;j++)
    	  {
    		  if(j==0)
    		  {
    			  if(!table.containsKey(temp[j]))
    			  {
    			  ArrayList<Integer> tp=new ArrayList<Integer>();
    			  tp.add(0);
    			  tp.add(0);
    			  tp.add(0);
    			  tp.set(0, 1);
    			  table.put(temp[j], tp);
    			  }
    			  else
    			  {
    				  ArrayList<Integer> tp=table.get(temp[j]);
    				  tp.set(0, tp.get(0)+1);
    				  table.put(temp[j], tp);
    			  }
    		  }
    		  else if(j==1)
    		  {
    			  if(!table.containsKey(temp[j]))
    			  {
    			  ArrayList<Integer> tp=new ArrayList<Integer>();
    			  tp.add(0);
    			  tp.add(0);
    			  tp.add(0);
    			  tp.set(1, 1);
    			  table.put(temp[j], tp);
    			  }
    			  else
    			  {
    				  ArrayList<Integer> tp=table.get(temp[j]);
    				  tp.set(1, tp.get(1)+1);
    				  table.put(temp[j], tp);
    			  }
    		  }
    		  else if(j==2)
    		  {
    			  if(!table.containsKey(temp[j]))
    			  {
    			  ArrayList<Integer> tp=new ArrayList<Integer>();
    			  tp.add(0);
    			  tp.add(0);
    			  tp.add(0);
    			  tp.set(2, 1);
    			  table.put(temp[j], tp);
    			  }
    			  else
    			  {
    				  ArrayList<Integer> tp=table.get(temp[j]);
    				  tp.set(2, tp.get(2)+1);
    				  table.put(temp[j], tp);
    			  }
    		  }
    	  }
    	  
    	  
    	  
    	  
    	  
      }
      ArrayList<String> tppp=new ArrayList<String>();
      ArrayList<String> score=new ArrayList<String>();
	  for(Entry<String, ArrayList<Integer>> entry : table.entrySet()) {
		  String key = entry.getKey();
		  ArrayList<Integer> value = entry.getValue();
		  String tempp=key+" "+value.get(0)+" "+value.get(1)+" "+value.get(2);
		  String tem=value.get(0)+" "+value.get(1)+" "+value.get(2);
		  score.add(tem);
		  tppp.add(tempp);
		}
	  String[] out=new String[tppp.size()];
	  String[] outs=new String[score.size()];
	  for(int z=0;z<score.size();z++)
	  {
		  outs[z]=score.get(z);
	  }
	  for(int z=0;z<tppp.size();z++)
	  {
		  out[z]=tppp.get(z);
	  }
	  Arrays.sort(outs);
	  Arrays.sort(out);
	  String[] outt=new String[tppp.size()];
	  for(int z=0;z<tppp.size();z++)
	  {
		  for(int k=tppp.size()-1;k>=0;k--)
		  {
			  if(out[k].contains(outs[z]))
			  {
				  outt[z]=out[k];
				  out[k]="a";
				  outs[z]="b";
				  break;
			  }
		  }
	  }
	  Collections.reverse(Arrays.asList(outt));
	  return outt;
   }
 }