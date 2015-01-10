import java.sql.*;
import java.util.*;
//import java.io.*;
public class connect 
{
	public void initcon(){
	    try 
	    {        
	      Class.forName(driverName).newInstance();
	      dbConn = DriverManager.getConnection(dbURL,userName,userPwd);
	      stat = dbConn.createStatement();
	      statRead4740=dbConn.createStatement();
	      statReadChangePWD=dbConn.createStatement();
	    } catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	}
	public void statRead4740()
	{
		try
		{
			 
			  ResultSet res = statRead4740.executeQuery("select time, username, source from lock");
			  ArrayList<String> timeList = new ArrayList<String>();
			  ArrayList<String> usernameList = new ArrayList<String>();
			  ArrayList<String> sourceList = new ArrayList<String>();
		
			  while (res.next()) 
			  {
				  String username = res.getString(2);
				  String time = res.getString(1);
				  String source = res.getString(3);
				  if(time!=null)
				  {
					  if(!username.equalsIgnoreCase("AD-OSMIUM$"))
					  {
						  timeList.add(time);
						  usernameList.add(username);
						  sourceList.add(source);
					  }
				  }
			  }
			  statReadChangePWD();
			  for(int count = 0; count<timeList.size();count++)
			  {
				  String code = "1";
				  if(sourceList.get(count).substring(0,2).equalsIgnoreCase("ex"))
				  {
					  code = code+"1";
				  }
				  else
				  {
					  code = code+"0";
				  }
				  if(PWDMap.get(usernameList.get(count))!=null)
				  {
					  if(PWDMap.get(usernameList.get(count)).compareToIgnoreCase(timeList.get(count))<=0)
					  {
						  code = code+"1";
					  }
					  else
					  {
						  code = code+"0";
					  }
				  }
				  else
				  {
					  code = code+"0";
				  }
				  dataObjList.add(new dataObj(usernameList.get(count),timeList.get(count),code));
			  }
			
			  
			  
			  
		}
		catch (Exception e)
	    {
	       e.printStackTrace();
	    }
	}
	
	public void statReadChangePWD()
	{
		try
		{
			 
			  ResultSet res = statReadChangePWD.executeQuery("select time, username, passwordChange from ChangePWD");
			  
			  while (res.next()) 
			  {
				  if(res.getString(3).equalsIgnoreCase("yes"))
				  {
					  PWDMap.put(res.getString(2), res.getString(1));
				  }
				  
			  }
			  
			  
		}
		catch (Exception e)
	    {
	       e.printStackTrace();
	    }
	}

	public void close()
	{
	    try {
	        if (dbConn!= null ) dbConn.close();
	        if (stat!= null ) stat.close();
	        if (statRead4740!= null ) statRead4740.close();
	        if (statReadChangePWD!= null ) statReadChangePWD.close();
	        PWDMap.clear();
	        dataObjList.clear();
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
	public Statement statRead4740=null;
	public Statement statReadChangePWD=null;
	public HashMap<String, String>PWDMap = new HashMap<String, String>();
	public ArrayList<dataObj> dataObjList = new ArrayList<dataObj>();
	//public ArrayList<Up> upName = new ArrayList<Up>();
	//public HashMap<String, DHCP> DHCPmap = new HashMap<String, DHCP>();
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
