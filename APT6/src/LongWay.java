
public class LongWay {
private int length;
private int datConnectionMap[][];
private String[] origConn;
private String[] origTime;
private int count=0;
public int longest(String[] connections, String[] times) 
{
	
	length = connections.length;
	datConnectionMap= new int[length][length];
	origConn=connections;
	origTime=times;
	setup();
	getTimeForPath();
	getLongest();
	return count;
	
}
private void getLongest()
{
	for(int i = 0; i < length; i++)
	
	{
		for(int k = 0; k < length; k++)
		{
			if(datConnectionMap[i][k] > count)
				count=datConnectionMap[i][k];
			
		}
    }
}
private void getTimeForPath()
{
	for (int diag=0;diag<length;diag++)
	{
		for (int row=0;row<length;row++)
		{
			for (int col=0;col<length;col++)
			{
				int current=datConnectionMap[row][col];
				int adj1=datConnectionMap[row][diag];
				int adj2=datConnectionMap[diag][col];
						
				if(current < adj1 + adj2 && adj1 != 0 && adj2 != 0)
				{
					datConnectionMap[row][col]=adj1+adj2;
					
				}
				
			}
		}
	}

}
private void setup()
{
	
	for (int i = 0; i < length; i++)
	{
		if (!origConn[i].equals(""))
		{
			String conn[] = origConn[i].split(" ");
			String time[] = origTime[i].split(" ");
			int k = 0;
			while(k < conn.length)
			{
				int edge = Integer.parseInt(conn[k]);
				int tim = Integer.parseInt(time[k]);
				if (tim > datConnectionMap[i][edge])
					datConnectionMap[i][edge] = tim;
				k++;
				
			}
			
		}
	}
}
    
    
}