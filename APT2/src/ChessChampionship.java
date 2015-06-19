public class ChessChampionship {
     public int[] points(String[] games) 
     {
         int[] count = new int[games[0].length()];
         for(int i=0;i<games.length;i++)
         {
        	 for(int j=0;j<games.length;j++)
             {
            	 if(games[i].substring(j, j+1).equals("W"))
            	 {
            		 count[i]=count[i]+3;
            	 }
            	 else if(games[i].substring(j, j+1).equals("D"))
            	 {
            		 count[i]++;
            		 count[j]++;
            	 }
            	 else if(games[i].substring(j, j+1).equals("L"))
            	 {
            		 count[j]=count[j]+3;
            	 }
            		 
             }
         }
         return count;
     }
 }