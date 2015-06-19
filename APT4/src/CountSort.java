import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;



public class CountSort {

	public String[] countSorter(String[] wordList) {
		TreeMap<String,Integer> map = new TreeMap<String,Integer>();
		for(int i=0;i<wordList.length;i++)
		{
			if(map.containsKey(wordList[i]))
			{
				map.put(wordList[i], map.get(wordList[i])+1);
			}
			else
			map.put(wordList[i], 1);
		}
		ArrayList<String> word=new ArrayList<String>();
		while(!map.isEmpty())
		{
			int high=0;
			String temp="";
			for(String s: map.keySet())
			{
				if(map.get(s)>high)
				{
					high=map.get(s);
					temp=s;
				}
				
			}
			word.add(temp);
			map.remove(temp);
		}
		String[] list=new String[word.size()];
		for(int i=0;i<word.size();i++)
		{
			list[i]=word.get(i);
		}
		return list;
    }
}
