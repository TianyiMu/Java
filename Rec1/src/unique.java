import java.util.Arrays;


public class unique {
	public static void main(String[] args) {
		String[] st={"a","a","b","b"};
		int a= uniqueCount(st);
	}
	public static int uniqueCount(String[] list) {
		Arrays.sort(list); // sort array in alphabetical order
		String last = list[0];
		int count = 1;
		int countUniq =1;
		for (int k=1; k<list.length; k++) {
		if (!list[k].equals(last)) {
			System.out.println(last + " appeared " +countUniq +" times");
			countUniq=1;
		last = list[k];
		count++;
		
		}
		else
		{
			countUniq++;
		}
		}
		System.out.println(last + " appeared " +countUniq +" times");
		return count;
		}
}
