
public class NaiveExecutor {

	public static void main(String[] args){
		
		for(int wlength=8; wlength <= 8; wlength++){
			//NaiveGuesser a=new NaiveGuesser(false);
			FrequencyGuesser a=new FrequencyGuesser(false);
		    GuessExecutor ng = new GuessExecutor(wlength,9, a, true);
		    
		    System.out.printf("word length: %d\n",wlength);
		    boolean ignored = ng.stress();
		}
	}
}
