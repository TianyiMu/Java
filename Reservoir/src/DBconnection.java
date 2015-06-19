import java.sql.*;  
import java.util.*;

public class DBconnection 
{
	static Connection conn;    
    
    static Statement stat;    
    public static void getConnection() //initiate connection to sql
    {    
    		conn = null;  
        try 
        {    
            Class.forName("com.mysql.jdbc.Driver");//load driver
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Reservoir", "root", "admin");//connect
            stat = conn.createStatement();
                
        } 
        catch (Exception e) 
        {    
            System.out.println("failed" + e.getMessage());    
        }     
    } 
    public static HashMap<String,Reservoir> readReservoirCharacteristics()//load basic reservoir data
    {
    		HashMap<String,Reservoir> reservoirMap=new HashMap<String,Reservoir>();
    		try {
				ResultSet res = stat.executeQuery("SELECT Name, NIDID, Division, "
						+ "District, Primary_Purpose, River, State, Boundary_Problem, "
						+ "No_Upstream_FederalDams, ClosestMajorDam FROM Reservoir");
				while (res.next()) 
				  {
					  String name = res.getString(1);//number refers to coln from select
					  String id = res.getString(2);
					  String division = res.getString(3);
					  String district = res.getString(4);
					  String purpose = res.getString(5);
					  String river = res.getString(6);
					  String state = res.getString(7);
					  String bound = res.getString(8);
					  int upstreamDam =res.getInt(9);
					  String closestDam = res.getString(10);
					  
					  
					  reservoirMap.put(id, new Reservoir(name,id,division,district,river,state,
							  closestDam, upstreamDam, bound.equalsIgnoreCase("canada"),purpose));
					  //create reservoir obj and put them in a map with NIDID as key
					  
				  }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return reservoirMap;
    }
    public static ArrayList<Double> getVolume(String id)
    {
    		ArrayList<Double> volume  = new ArrayList<Double>();
    		try {
				ResultSet res = stat.executeQuery("SELECT Volume_ACFT FROM LakeLevels WHERE NIDID='"+id+"'");
				while (res.next()) 
				  {
					  double vol = res.getDouble(1);//number refers to coln from select
					  volume.add(vol);
					  
				  }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return volume;
    }
    public static ArrayList<Double> getGuideVolume(String id)
    {
    		ArrayList<Double> volume  = new ArrayList<Double>();
    		try {
				ResultSet res = stat.executeQuery("SELECT GuideCurve_ACFT FROM LakeLevels WHERE NIDID='"+id+"'");
				while (res.next()) 
				  {
					  double vol = res.getDouble(1);//number refers to coln from select
					  volume.add(vol);
					  
				  }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return volume;
    }
    public static ArrayList<Double> getLevel(String id)
    {
    		ArrayList<Double> volume  = new ArrayList<Double>();
    		try {
				ResultSet res = stat.executeQuery("SELECT Elev_Ft_12pm FROM LakeLevels WHERE NIDID='"+id+"'");
				while (res.next()) 
				  {
					  double vol = res.getDouble(1);//number refers to coln from select
					  volume.add(vol);
					  
				  }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return volume;
    }
    
    public static int getYear(String id)
    {
    		int year  = 0;
    		try {
				ResultSet res = stat.executeQuery("SELECT Year FROM LakeLevels WHERE NIDID='"+id+"'");
				if(res.next())
				year = res.getInt(1);
					  
				  
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return year;
    }
    public static ArrayList<Double> getAvgPrecip(String id, int year)
    {
    		ArrayList<Double> precip  = new ArrayList<Double>();
    		try {
				ResultSet res = stat.executeQuery("SELECT avePrcpIN FROM precip WHERE NIDID='"+id+"' AND year>="+year);
				while (res.next()) 
				  {
					  double vol = res.getDouble(1);//number refers to coln from select
					  precip.add(vol);
					  
				  }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return precip;
    }
    public static ArrayList<Double> getAvgTemp(String id, int year)
    {
    		ArrayList<Double> precip  = new ArrayList<Double>();
    		try {
				ResultSet res = stat.executeQuery("SELECT aveTaveF FROM temp WHERE NIDID='"+id+"' AND year>="+year);
				while (res.next()) 
				  {
					  double vol = res.getDouble(1);//number refers to coln from select
					  precip.add(vol);
					  
				  }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return precip;
    }
    public void close()//close connection to sql
	{
	    try {
	        if (conn!= null ) conn.close();
	        if (stat!= null ) stat.close();
	    }catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	}
    
    public static void main(String[] args) 
	{

	}

}
