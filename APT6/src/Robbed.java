
import java.util.Arrays;


public class Robbed {
        
	private char[][] datNewGrid;
	private int Row, Col;
	private int CashRow, CashCol;
	private int Robr, Robc;
	private String[] datMaze;
        
	public int countRoutes(String[] maze) { 
        		datMaze=maze;
 
        		Row = maze.length; 
     
        		Col = maze[0].length();
        		datNewGrid = new char[maze.length][maze[0].length()];
        		Robr=0;
        		Robc=0;
        		setup();
        		int dist = getDist(Robr,Robc); 
        		return getCount(Robr,Robc, dist+1); 
        	}
	private void setup()
        {
     
	        	for(int r=0; r < Row; r++){ 
	                Arrays.fill(datNewGrid[r], '.'); 
	                for(int c=0; c < Col; c++){ 
	                        char ch = datMaze[r].charAt(c); 
	                        if (ch == 'C'){ 
	                        		CashRow = r;
	                        		CashCol = c; 
	                        		datNewGrid[r][c] = 'C';  
	                        	
	                        } 
	                        else if (ch == 'R'){
	                        		Robr = r; 
	                        		Robc = c; 
	                        		datNewGrid[r][c] = 'R'; 
	                        } 
	                        else if (ch == 'X'){ 
	                        			datNewGrid[r][c] = 'X'; 
	                        } 
	                } 
	        } 
        }
	private int getDist(int r, int c) {
		return Math.abs(r-CashRow) + Math.abs(c-CashCol);
		}
	private int nextMove(int r, int c, int dis)
    {
        	  
		int right = getCount(r+1, c, dis-1);
		int left = getCount(r-1, c, dis-1);
		int up = getCount(r, c+1, dis-1);
		int down = getCount(r, c-1, dis-1);
		return right+left+up+down;
    }
	private int getCount(int r, int c, int dist){ 
                  if (r < 0 || c < 0 || r >= Row || c >= Col) 
                          return 0; 
                  if (datNewGrid[r][c] == 'C') 
                          return 1; 
                  if (datNewGrid[r][c] == 'X') 
                          return 0;
                  if(getDist(r, c) > dist)
                          return 0;
                  return nextMove(r, c,dist);
          }
	
          
}