
public class DHCPuser 
{
	private String computerName;
	private String allocationTime;
	private String releaseTime;
	public DHCPuser(String computerName, String allocationTime, String releaseTime)
	{
		this.computerName=computerName;
		this.allocationTime=allocationTime;
		this.releaseTime=releaseTime;
	}
	public String getComputerName()
	{
		return computerName;
	}
	public String getAllocationTime()
	{
		return allocationTime;
	}
	public String getReleaseTime()
	{
		return releaseTime;
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
