import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class SubstringSort {
	
	
	public String[] sortSubstrings(String[] words) {
        int leng = words.length;
        Word[] wordList = new Word[leng];
        int count=0;
        while(count<leng)
        {
        	wordList[count] = new Word(words[count]);
        	count++;
        }
        	
        		Arrays.sort(wordList);
        		count=0;
        		while(count<leng)
        		{
        			words[count] = wordList[count].content;
        			count++;
        		}
        return words;
}
	
    class Word implements Comparable<Word> {
            String[] input;
            String[] result;
            String content;
            String key = "aeiou";
            boolean Con(char c) {
            	if(key.indexOf(c) == -1)
            	{
            		return true;
            	}
            	else
            		return false;
        }
            ArrayList<String> LL = new ArrayList<String>();

            Word(String word) {
                    content = word;
                    checkConsonat(word);
                    input = LL.toArray(new String[0]);
                    result = LL.toArray(new String[0]);
                    Arrays.sort(result);
            }
            public void checkConsonat(String w)
            {
            	while (w.length() > 0) 
            	{
                    String out = "";
                    while (w.length() > 0 && Con(w.charAt(0))) 
                    {
                            out += w.charAt(0);
                            w = w.substring(1,w.length());
                    }
                    while (w.length() > 0 && !Con(w.charAt(0))) 
                    {
                            out += w.charAt(0);
                            w = w.substring(1,w.length());
                    }
                    LL.add(out);
            }
            }

            public int compareTo(Word wordComp) {
                    
                    int count=0;
                    while(count < wordComp.result.length && count < result.length)
                    {
                    	if (!wordComp.result[count].equals(result[count]))
                    	{
                            return result[count].compareTo(wordComp.result[count]);
                    	}
                    	count++;
                    }
                    if (result.length != wordComp.result.length)
                    {
                            return result.length - wordComp.result.length;
                    }
                    count=0;
                    while(count < wordComp.input.length && count < input.length)
                    {
                    	if (!wordComp.input[count].equals(input[count]))
                    	{
                            return input[count].compareTo(wordComp.input[count]);
                    	}
                    	count++;
                    }
                    return 0;
            }
    }

    

    

    
}