import java.util.*;

/**
 * This is a skeleton program. You'll
 * need to modify it, either by adding code in main
 * or in methods called from main.
 * @author Tianyi Mu
 *
 */

public class HangmanStats {
	public void numberOfWords(HangmanFileLoader loader)
	{
		/*
		for(int i =4;i<=20;i++)
		{
			HashSet<String> set1 = new HashSet<String>();
			for(int k=0; k < 100000; k += 1) 
			{
				set1.add(loader.getRandomWord(i));
			}
			System.out.println("number of "+i+" letter words is "+set1.size());
		}*/
		
		HashMap <Integer, Integer> time = new HashMap<Integer, Integer>(21-4);
		for(int i =4;i<=20;i++)
		{
			int iter =10000;
		HashSet<Integer> count = new HashSet<Integer>();
		HashSet<String> set1 = new HashSet<String>();
		for(int j=0;j<5;j++)
		{
		set1.clear();
		for(int k=0; k < iter; k += 1) 
		{
			set1.add(loader.getRandomWord(i));
			count.add(set1.size());
		}
		}
		for(int k=0; k < iter; k += 1) 
		{
			set1.add(loader.getRandomWord(i));
		}
		while(!count.contains(set1.size()))
		{
			iter=iter+20000;
			set1.clear();
			for(int k=0; k < iter; k += 1) 
			{
				set1.add(loader.getRandomWord(i));
			}
			count.add(set1.size());
			
		}
		time.put(i, set1.size());
		}
		
		for(int i =4;i<=20;i++)
		{
			System.out.println("number of "+i+" letter words is "+time.get(i));
		}
	}
	
	public void statisticalQuestion(HangmanFileLoader loader){
		int length=0;
		int size=0;
		HashSet<Integer> count = new HashSet<Integer>();
		HashSet<String> set1 = new HashSet<String>();
		for(int i =4;i<=20;i++)
		{
			int iter =10000;
		if(set1.size()>size)
		{
			size=set1.size();
			length =i-1;
		}
		set1.clear();
		count.clear();
		for(int j=0;j<5;j++)
		{
		set1.clear();
		for(int k=0; k < iter; k += 1) 
		{
			set1.add(loader.getRandomWord(i));
			count.add(set1.size());
		}
		}
		for(int k=0; k < iter; k += 1) 
		{
			set1.add(loader.getRandomWord(i));
		}
		while(!count.contains(set1.size()))
		{
			iter=iter+20000;
			set1.clear();
			for(int k=0; k < iter; k += 1) 
			{
				set1.add(loader.getRandomWord(i));
			}
			count.add(set1.size());
		}
		
		}
		System.out.println("Most occured words are "+length+" characters long. There are "+size+".");
	}
	public static void main(String[] args) {
		HangmanStats stats = new HangmanStats();
		HangmanFileLoader loader = new HangmanFileLoader();
		loader.readFile("lowerwords.txt");
		stats.numberOfWords(loader);
		stats.statisticalQuestion(loader);
			
	}
}
