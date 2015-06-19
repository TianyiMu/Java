
public class BinarySearchExecutor {

	public static void main(String[] args) {
		for(int wlength=5; wlength <= 10; wlength++)
		{
			double lo=1;
			int high=26;
			while(lo<=high)
			{
				int mid=(int) Math.floor(lo + (high-lo)/2.0);
				GuessExecutor ng = new GuessExecutor(wlength,mid, new FrequencyGuesser(false));
				GuessExecutor ng2 = new GuessExecutor(wlength,mid-1, new FrequencyGuesser(false));
				
				if (ng.stress()&&ng2.stress())
					high = mid-1;
				else if(ng.stress())
				{
					System.out.println("min for "+wlength+" = "+mid);
					System.out.println();
					   break;
				}
				else
					lo = mid+1;
			}
			
		}

	}
	/*public String convertToPattern(char[] template, String word, char guess)
	{
		char[] list=word.toCharArray();
		for(int i=1;i<list.length;i++)
		{
			if(guess==list[i])
			{
				template[i]=guess;
			}
		}
		String result="";
		for(int i=1;i<list.length;i++)
		{
			result=result+Character.toString(template[i]);
		}
		return result;
	}*/

}
