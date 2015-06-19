import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;


public class PathSort {

	public String[] sortPath(String[] dire) {
		TreeMap<Integer,ArrayList<String>> tree = new TreeMap<Integer,ArrayList<String>>();
		for(int i=0;i<dire.length;i++)
		{
			int count=0;
			for(int k=0;k<dire[i].length();k++)
			{
				if(dire[i].substring(k, k+1).equals("/"))
				{
					count++;
				}
			}
			if(!tree.containsKey(count))
			{
				ArrayList<String> tp=new ArrayList<String>();
				tp.add(dire[i]);
				tree.put(count, tp);
			}
			else
			{
				ArrayList<String> tp=tree.get(count);
				tp.add(dire[i]);
				tree.put(count, tp);
			}
		}
		String[] out=new String[dire.length];
		int inx=0;
		for(Entry<Integer, ArrayList<String>> entry : tree.entrySet()) {
			  Integer key = entry.getKey();
			  ArrayList<String> value = entry.getValue();
			  Collections.sort(value);
			  for(int i=0;i<value.size();i++)
			  {
				  out[inx]=value.get(i);
				  inx++;
			  }
			}
		//Iterator<String> iterator = tree.iterator();
		
		//int idx=0;
		//while (iterator.hasNext()) {
		//	out[idx]=iterator.next();
		//	idx++;
		//}
		return out;
   }
}
