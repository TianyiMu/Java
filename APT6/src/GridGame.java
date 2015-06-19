public class GridGame {
	
	private String[][] theMap;
	private String[][] originalMap;
	private int count=0;
	private boolean whosTurn;
     public int winningMoves(String[] grid){
          theMap=new String[4][4];
          restore(grid);
          originalMap=theMap;
          findCount(grid);
          return count;
     }
     private void restore(String[] grid)
     {
    	 for(int i=0;i<grid.length;i++)
         {
       	  for(int k=0;k<grid[i].length();k++)
       	  {
       		  theMap[i][k]=grid[i].substring(k, k+1);
       	  }
         }
     }
     private void findCount(String[] grid)
     {
    	 for(int i=0;i<4;i++)
         {
       	  for(int k=0;k<4;k++)
       	  {
       		whosTurn=true;
       		if(makeMoves(k, i))
       			count++;
       		restore(grid);
       	  }
         }
     }
     private boolean checkLocation(int col, int rol)
     {
    	 boolean yes=false;
    	 if(col>=theMap.length||rol>=theMap.length||col<0||rol<0)
    	 {
    		 yes=true;
    	 }
    	 else if(theMap[rol][col].equals("."))
    	 {
    		 yes=true;
    	 }
    	 return yes;
     }
     private boolean checkAround(int col, int rol)
     {
    	 boolean yes=false;
    	 if(checkLocation(col, rol)&&checkLocation(col-1, rol)&&checkLocation(col+1, rol)&&checkLocation(col, rol-1)&&checkLocation(col, rol+1))
    	 {
    		 yes=true;
    	 }
    	 return yes;
     }
     private boolean makeMoves(int col, int rol)
     {
    	 if(checkAround(col, rol))
    	 {
    		 boolean myTurn=true;
    		 theMap[rol][col]="X";
    		 for(int i=0;i<4;i++)
             {
           	  for(int k=0;k<4;k++)
           	  {
           		if(makeMoves(k, i))
           			myTurn=false;
           	  }
             }
    		 theMap[rol][col]=".";
    		 return myTurn;
    	 }
    	 else
    	 return false;
     }
   }