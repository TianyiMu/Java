import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;


public class Thesaurus {

  public Set<String> sToSet(String s) {
	  String[] temp=s.split(" ");
	  Set<String> word=new TreeSet<String>();
	  for(int i=0;i<temp.length;i++)
	  {
		  word.add(temp[i]);
	  }
    return word;
  }

  public String collToS(Collection<String> elems) {
	  ArrayList<String> temp = new ArrayList<String>();
	  for (String s : elems)
	  {
		  temp.add(s);
	  }
	  String temps=temp.get(0).toString();
	  for(int k=1;k<temp.size();k++)
 	 {
		  temps=temps+" "+temp.get(k);
 	 }
    return temps;
  }

  public int numInCommon(Set<String> a, Set<String> b) {
    int count=0;
    for (String s : a) {
        if(b.contains(s))
        	count++;
    }
    return count;
  }

  public Set<String> union(Set<String> a, Set<String> b) {
	 Set<String> a1=a;
	 Set<String> b1=b;
	 a1.addAll(b1);
    return a1;
  }

  public String[] edit(String[] entry) {
	  ArrayList<Set> allWord = new ArrayList<Set>();
	  for(int i=0;i<entry.length;i++)
	  {
		  allWord.add(sToSet(entry[i]));
	  }
	  boolean change=true;
	  while(change)
	  {
		  change=false;
		  int i=0;
		  while(i<(allWord.size()-1))
				  {

			  for(int k=i+1;k<allWord.size();k++)
			  {
				  if(numInCommon(allWord.get(i), allWord.get(k))>=2)
						  {
					  change=true;
					  Set<String> merge=union(allWord.get(i), allWord.get(k));
					  allWord.set(i, merge);
					  allWord.remove(k);
					  k--;
						  }
			  }
			  i++;
				  }
	  }
	  String[] temp =new String[allWord.size()];
	  for(int i=0;i<allWord.size();i++)
	  {
		  temp[i]=collToS(allWord.get(i));
	  }
	  Arrays.sort(temp);

    return temp;
  }
}
  
