import java.util.ArrayList;


public class DHCP 
{
	private String IP;
	
	public ArrayList<DHCPuser> DHCPuser = new ArrayList<DHCPuser>();
	public DHCP (String ip)
	{
		this.IP = ip;
		
	}
	public String getDHCPip()
	{
		return IP;
	}
	public void addDHCPuser(String computerName, String allocationTime, String releaseTime)
	{
		this.DHCPuser.add(new DHCPuser(computerName, allocationTime, releaseTime));
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
