

public class MinExecutor {

	public static void main(String[] args) {
		for(int wlength=8; wlength <= 8; wlength++)
		{
			for(int i=8;i<=10;i++)
			{
			//GuessExecutor ng = new GuessExecutor(wlength,i, new NaiveGuesser(false));
			//int i=3;
				GuessExecutor ng = new GuessExecutor(wlength,i, new FrequencyGuesser(false));
				if (ng.stress()){
				   System.out.println("min for "+wlength+" = "+i);
				   System.out.println();
				   break;
				}
			}
		}

	}

}
