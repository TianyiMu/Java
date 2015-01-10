//import java.lang.*;
//import java.util.*;


public class SipList 
{
	

public static void main(String[] args) 
{
	long startTime = System.currentTimeMillis();
	Serverconn test = new Serverconn();
	test.initcon();
	test.readDown();
	test.loadUp();
	test.loadDHCP();
	test.readUp();
	//test.start();
	//System.out.println(test.downMap.get("NEGERWANG").getDownUsername());
    
	test.close();
	long endTime   = System.currentTimeMillis();
	long totalTime = endTime - startTime;
	System.out.println("Total time in milliseconds:"+totalTime);
	System.out.println("finished");
	
}
}
