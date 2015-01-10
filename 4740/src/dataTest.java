import java.util.ArrayList;
import java.util.Scanner;
public class dataTest 
{
 
	public static void main(String[] args) 
	{

		long startTime = System.currentTimeMillis();
		connect test = new connect();
		test.initcon();
		test.statRead4740();
		System.out.println("Enter rules here: ");
		System.out.println("Sample: time>10;source=exchange;password change=yes");
		String input;
		@SuppressWarnings("resource")
		Scanner scanIn = new Scanner(System.in);
		input = scanIn.nextLine();
		//System.out.println(input);
		ArrayList<String> rules = new ArrayList<String>();
		while(input.contains(";"))
		{
			int index = input.indexOf(";");
			String temp = input.substring(0, index);
			rules.add(temp);
			input=input.substring(index+1);
		}
		rules.add(input);
		//String code="000";
		ArrayList<String> code = new ArrayList<String>();
		String codeTemp=null;
		code.add(codeTemp);
		code.add(codeTemp);
		code.add(codeTemp);
		for(int i=0;i<rules.size();i++)
		{
			if(rules.get(i).contains("time"))
			{
				if(rules.get(i).substring(4).equalsIgnoreCase(">10")||rules.get(i).substring(4).equalsIgnoreCase("=10"))
				{
					code.set(0, "1");
				}
				else if(rules.get(i).substring(4).equalsIgnoreCase("<10"))
				{
					code.set(0, "0");
				}
				else
				{
					System.out.println("wrong rule");
				}
			}
			else if(rules.get(i).contains("source"))
			{
				if(rules.get(i).substring(7).equalsIgnoreCase("exchange"))
				{
					code.set(1, "1");
				}
				else if(rules.get(i).substring(7).equalsIgnoreCase("pc"))
				{
					code.set(1, "0");
				}
				else
				{
					System.out.println("wrong rule");
				}
			}
			else if(rules.get(i).contains("password change"))
			{
				if(rules.get(i).substring(16).equalsIgnoreCase("yes"))
				{
					code.set(2, "1");
				}
				else if(rules.get(i).substring(16).equalsIgnoreCase("no"))
				{
					code.set(2, "0");
				}
				else
				{
					System.out.println("wrong rule");
				}
			}
			
		}
		//System.out.println(code.toString());
		String yesNull="";
		for(int i=0;i<code.size();i++)
		{
			if(code.get(i)==null)
			{
				if(i==0)
				{
					yesNull=yesNull+"1";
				}
				if(i==1)
				{
					yesNull=yesNull+"2";
				}
				if(i==2)
				{
					yesNull=yesNull+"3";
				}
			}
		}
		if(yesNull=="")
		{
			String codeRule="";
			for(int i=0;i<code.size();i++)
			{
				codeRule=codeRule+code.get(i);
			}
			for(int i=0;i<test.dataObjList.size();i++)
				{
					if(test.dataObjList.get(i).code.equalsIgnoreCase(codeRule))
						{
							System.out.println(test.dataObjList.get(i).keyString);
							System.out.println(test.dataObjList.get(i).time);
							System.out.println(test.dataObjList.get(i).code);
						}
				}
		}
		if(yesNull.equalsIgnoreCase("1"))
		{
			String codeRule="";
			for(int i=1;i<code.size();i++)
			{
				codeRule=codeRule+code.get(i);
			}
			for(int i=0;i<test.dataObjList.size();i++)
				{
					if(test.dataObjList.get(i).code.substring(1).equalsIgnoreCase(codeRule))
						{
							System.out.println(test.dataObjList.get(i).keyString);
							System.out.println(test.dataObjList.get(i).time);
							System.out.println(test.dataObjList.get(i).code);
						}
				}
		}
		if(yesNull.equalsIgnoreCase("2"))
		{
			String codeRule="";
			codeRule=codeRule+code.get(0);
			codeRule=codeRule+code.get(2);
			for(int i=0;i<test.dataObjList.size();i++)
				{
					if((test.dataObjList.get(i).code.substring(0, 1)+test.dataObjList.get(i).code.substring(2)).equalsIgnoreCase(codeRule))
						{
							System.out.println(test.dataObjList.get(i).keyString);
							System.out.println(test.dataObjList.get(i).time);
							System.out.println(test.dataObjList.get(i).code);
						}
				}
		}
		if(yesNull.equalsIgnoreCase("3"))
		{
			String codeRule="";
			for(int i=0;i<(code.size()-1);i++)
			{
				codeRule=codeRule+code.get(i);
			}
			for(int i=0;i<test.dataObjList.size();i++)
				{
					if(test.dataObjList.get(i).code.substring(0, 2).equalsIgnoreCase(codeRule))
						{
							System.out.println(test.dataObjList.get(i).keyString);
							System.out.println(test.dataObjList.get(i).time);
							System.out.println(test.dataObjList.get(i).code);
						}
				}
		}
		if(yesNull.equalsIgnoreCase("12"))
		{
			String codeRule="";
			codeRule=codeRule+code.get(2);
			for(int i=0;i<test.dataObjList.size();i++)
				{
					if(test.dataObjList.get(i).code.substring(2).equalsIgnoreCase(codeRule))
						{
							System.out.println(test.dataObjList.get(i).keyString);
							System.out.println(test.dataObjList.get(i).time);
							System.out.println(test.dataObjList.get(i).code);
						}
				}
		}
		if(yesNull.equalsIgnoreCase("13"))
		{
			String codeRule="";
			codeRule=codeRule+code.get(1);
			for(int i=0;i<test.dataObjList.size();i++)
				{
					if(test.dataObjList.get(i).code.substring(1, 2).equalsIgnoreCase(codeRule))
						{
							System.out.println(test.dataObjList.get(i).keyString);
							System.out.println(test.dataObjList.get(i).time);
							System.out.println(test.dataObjList.get(i).code);
						}
				}
		}
		if(yesNull.equalsIgnoreCase("23"))
		{
			String codeRule="";
			codeRule=codeRule+code.get(0);
			for(int i=0;i<test.dataObjList.size();i++)
				{
					if(test.dataObjList.get(i).code.substring(0, 1).equalsIgnoreCase(codeRule))
						{
							System.out.println(test.dataObjList.get(i).keyString);
							System.out.println(test.dataObjList.get(i).time);
							System.out.println(test.dataObjList.get(i).code);
						}
				}
		}
		test.close();
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total time in milliseconds:"+totalTime);
		System.out.println("finished");
	}

}
