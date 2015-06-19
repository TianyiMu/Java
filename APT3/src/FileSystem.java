public class FileSystem {
      public long findUsed(int[] fileBytes, int blockBytes) {
    	  double count=0;
         for(int i=0; i<fileBytes.length;i++)
         {
        	 
        	 if((fileBytes[i]<=blockBytes)&&(fileBytes[i]!=0))
        		 count++;
        	 if(fileBytes[i]>blockBytes)
        	 {
        		 count=count+Math.ceil(fileBytes[i]/(double)blockBytes);
        	 }
         }
         long used=(long) (count*blockBytes);
         return used;
      }
   }