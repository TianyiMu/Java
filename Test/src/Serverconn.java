import java.sql.*;
import java.util.*;
import java.io.*;
//import java.lang.*;
public class Serverconn
{

public void initcon(){
    try 
    {        
      Class.forName(driverName).newInstance();
      dbConn = DriverManager.getConnection(dbURL,userName,userPwd);
      stat = dbConn.createStatement();
      statReadUp=dbConn.createStatement();
      statCheckUserTime=dbConn.createStatement();
      statSearchIP=dbConn.createStatement();
      statReadDown=dbConn.createStatement();
      statLoadUp=dbConn.createStatement();
      statloadDHCP=dbConn.createStatement();
    } catch (Exception e)
    {
      e.printStackTrace();
    }
}
public void readDown()
{
	
	try
	{
		 
		  ResultSet res = statReadDown.executeQuery("SELECT time, username, fileName FROM KMLoad");
	
		  while (res.next()) 
		  {
			  
			  String username = res.getString(2);
			  String time = res.getString(1);
			  String fileName = res.getString(3);
		  		if (downMap.containsKey(username))
		  		{
		  			downMap.get(username).addFile(fileName, time);
		  		}
		  		else
		  		{
		  			downMap.put(username, new Down(username));
		  			downMap.get(username).addFile(fileName, time);
		  		}
		  		
		  }
		  
		  
	}
	catch (Exception e)
    {
       e.printStackTrace();
    } 
}
public void loadUp()
{
	int count = 0;

	try
	{
		
		 ResultSet res = statLoadUp.executeQuery("SELECT time, sIP, fileName FROM UPLoad");
		
	
		  while (res.next()) 
		  {
			  	count++;
			 	upName.add(new Up(res.getString(2),res.getString(3),res.getString(1)));
		  		
		  }
		  System.out.println("Total:"+count);
		
		  
	}
	catch (Exception e)
    {
       e.printStackTrace();
    } 
}
public void loadDHCP()
{
	int count = 0;
	
	try
	{
		
		 ResultSet res = statloadDHCP.executeQuery("select IP, computerName, allocationTime, releaseTime from DHCP");
		
	
		  while (res.next()) 
		  {
			  		count++;
			  		String ip = res.getString(1);
			  		String computerName = res.getString(2);
			  		String allocationTime = res.getString(3);
			  		String releaseTime = res.getString(4);
			  		if (DHCPmap.containsKey(ip))
			  		{
			  			DHCPmap.get(ip).addDHCPuser(computerName, allocationTime,releaseTime);
			  		}
			  		else
			  		{
			  			DHCPmap.put(ip, new DHCP(ip));
			  			DHCPmap.get(ip).addDHCPuser(computerName, allocationTime,releaseTime);
			  		}
		  }
		  System.out.println("Total:"+count);
		  
		  
	}
	catch (Exception e)
    {
       e.printStackTrace();
    } 
}
public void readUp()
{
	//int count = 0;
	
			  for(int upname = 0; upname<upName.size();upname++)
			
			{
			  //count++;
			  username.clear();
			  String sip = upName.get(upname).getUpSip();
			  String time = upName.get(upname).getUpTime();
			  String fileName = "";
			  CharSequence cs = "\\";
			  char ch = '\\';
			  if (upName.get(upname).getUpfileName().contains(cs))
			  	{
				  //System.out.println("has");
			  		fileName = upName.get(upname).getUpfileName().substring(upName.get(upname).getUpfileName().lastIndexOf(ch)+1);
			  		//System.out.println(fileName);
			  	}
			  	else
			  	{
			  		fileName = upName.get(upname).getUpfileName();
			  	}
			  
			  searchIP(sip, time);
			  if (username.size()!=0)
			  {
				  int i = 0;
				  while(i<username.size())
				  {
					  Down temp = downMap.get(username.get(i));
					  if(temp != null&&!temp.fileListIsEmpty()&&temp.fileListContain(fileName,time))
					  {
							  try 
							  {
								  	PrintWriter out = new PrintWriter(new FileWriter("F:\\test.txt", true));
								  	out.println("ALERT!!!!");
							        out.println("User:"+username.get(i)+". Time:"+time+". File name:"+fileName);
							        out.close();
							  } 
							  catch (IOException e){e.printStackTrace();}
					  }
					  i++;
				  }
			  }
			  //if(count%10000==0)
			  //{
			  //System.out.println(count);
			  //}
		  }
	}
public void searchIP(String ip, String time)
{
		DHCP temp = DHCPmap.get(ip);
         if(temp!=null)
         {
        	int i =0;
        	while(i<temp.DHCPuser.size())
        	{
        		
        		if(checkTime(time,temp.DHCPuser.get(i).getAllocationTime(),temp.DHCPuser.get(i).getReleaseTime()))
        		{
        			if(temp.DHCPuser.get(i).getComputerName().contains("-"))
        			{
        				String add = temp.DHCPuser.get(i).getComputerName().substring(0, temp.DHCPuser.get(i).getComputerName().indexOf("-"));
        				username.add(add.toLowerCase());
        				//System.out.println(add);
        			}
        			else
        			{
        				String add =temp.DHCPuser.get(i).getComputerName().substring(0, temp.DHCPuser.get(i).getComputerName().indexOf("."));
        				username.add(add.toLowerCase());
        				//System.out.println(add);
        			}
        		}
        		i++;
        	}
         }
}
public boolean checkTime(String time, String allocationTime, String releaseTime)
{
	if(releaseTime.equalsIgnoreCase("1975-01-01 00:00:00.000"))
	{
		
		if(time.compareTo(allocationTime.substring(0, 4)+allocationTime.substring(5, 7)+allocationTime.substring(8, 10)+allocationTime.substring(11, 13)+allocationTime.substring(14, 16)+allocationTime.substring(17, 19)+allocationTime.substring(20))>=0)
		{
			//System.out.println(allocationTime.substring(0, 4)+allocationTime.substring(5, 7)+allocationTime.substring(8, 10)+allocationTime.substring(11, 13)+allocationTime.substring(14, 16)+allocationTime.substring(17, 19)+allocationTime.substring(20));
			return true;
		}
	}
	else
	{
		if(time.compareTo(allocationTime.substring(0, 4)+allocationTime.substring(5, 7)+allocationTime.substring(8, 10)+allocationTime.substring(11, 13)+allocationTime.substring(14, 16)+allocationTime.substring(17, 19)+allocationTime.substring(20))>=0&&time.compareTo(releaseTime.substring(0, 4)+releaseTime.substring(5, 7)+releaseTime.substring(8, 10)+releaseTime.substring(11, 13)+releaseTime.substring(14, 16)+releaseTime.substring(17, 19)+releaseTime.substring(20))<=0)
		{
			//System.out.println(releaseTime.substring(0, 4)+releaseTime.substring(5, 7)+releaseTime.substring(8, 10)+releaseTime.substring(11, 13)+releaseTime.substring(14, 16)+releaseTime.substring(17, 19)+releaseTime.substring(20));
			return true;
		}
	}
	return false;
}
public void checkUserTime(String username,String ip,String t,String fileName)
{
	//System.out.println("getUserTime");
	String time ="";
    String sqls = "SELECT IP, computerName, recentTime FROM DHCP WHERE computerName LIKE '"+username+"%';";
    try 
    {
        ResultSet rs = statCheckUserTime.executeQuery(sqls);       
        while(rs.next())
        {   
        	if(ip.equalsIgnoreCase(rs.getString(1)))
        	{
        		//String temp = rs.getString("recentTime");
        		String a = rs.getString("recentTime").substring(0, 4);
        		String b = rs.getString("recentTime").substring(5, 7);
        		String c = rs.getString("recentTime").substring(8, 10);
        		String d = rs.getString("recentTime").substring(11, 13);
        		String e = rs.getString("recentTime").substring(14, 16);
        		String f = rs.getString("recentTime").substring(17, 19);
        		String g = rs.getString("recentTime").substring(20);
        		time = a+b+c+d+e+f+g;
        		if(t.compareTo(time)<= 0)
        		{
        			System.out.println("ALERT!!!!");
					System.out.println("User:"+username+". Time:"+t+". File name:"+fileName);
        		}
        		//System.out.println(time);
        	}
        }
    
    }
    catch (Exception e)
    {
       e.printStackTrace();
    }
    //return time;
}
public void close()
{
    try {
        if (dbConn!= null ) dbConn.close();
        if (stat!= null ) stat.close();
        if (statReadUp!= null ) statReadUp.close();
        if (statCheckUserTime!= null ) statCheckUserTime.close();
        if (statSearchIP!= null ) statSearchIP.close();
        if (statReadDown!= null ) statReadDown.close();
        if (statLoadUp!= null ) statLoadUp.close();
        if (statloadDHCP!= null ) statloadDHCP.close();
        downMap.clear();
        username.clear();
        upName.clear();
        DHCPmap.clear();
    }catch (Exception e)
    {
        e.printStackTrace();
    }
}
public String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
public String dbURL = "jdbc:sqlserver://localhost:18816; DatabaseName=test";  
public String userName = "sa"; 
public String userPwd = "tencent@123";  
public Connection dbConn=null;
public Statement stat=null;
public Statement statReadUp=null;
public Statement statCheckUserTime=null;
public Statement statSearchIP=null;
public Statement statReadDown=null;
public Statement statLoadUp=null;
public Statement statloadDHCP=null;
public HashMap<String, Down>downMap = new HashMap<String, Down>();
public ArrayList<String> username = new ArrayList<String>();
public ArrayList<Up> upName = new ArrayList<Up>();
public HashMap<String, DHCP> DHCPmap = new HashMap<String, DHCP>();

}
