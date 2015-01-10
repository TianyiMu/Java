
import java.util.*;

public class Down implements Comparable<Down>
{
private String username;
public ArrayList<File> files = new ArrayList<File>();

	public Down(String i)
	{
		username = i;
	}
	public String getDownUsername()
	{
		return username;
	}
	public void addFile(String fileName, String time)
	{
		this.files.add(new File(fileName,time));
	}
	public boolean fileListIsEmpty()
	{
		if(this.files.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean fileListContain(String fileName, String time)
	{
		int i = 0;
		while(i<this.files.size())
		{
			if(files.get(i).getFileName().equalsIgnoreCase(fileName))
			{
				
				if(files.get(i).getFileTime().compareTo(time)<=0)
				{
					return true;
				}
			}
			i++;
		}
			return false;
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}
	@Override
	public int compareTo(Down other) 
	{
		return this.username.compareTo(other.username);
	}

}
