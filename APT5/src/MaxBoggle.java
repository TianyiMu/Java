import java.util.ArrayList;

public class MaxBoggle {
	private ArrayList<String> grid;
	private long allCount;
	private long smallCount;
	private long[] oldCount;
	long mod = (long) 1E13;
	
      public long maxPoints(String[] matrix,String[] words){
    	  ArrayList<String> temp=new ArrayList<String>();
    	  for(int i=0;i<matrix.length;i++)
    	  {
    		  for(int k=0;k<matrix[i].length();k++)
    		  {
    			  temp.add(matrix[i].substring(k, k+1));
    		  }
    	  }
    	  grid=temp;
    	  allCount=0;
    	  for(String s : words)
    	  {
    		  smallCount=0;
    		  find(s);
    		  long leng=s.length();
    		  long score=leng*leng;
    		  long temps=(score*smallCount)%mod;
    		  allCount=temps+allCount;
    	  }

    	  allCount=allCount%mod;
    	  return allCount;
      }
      public void find(String s)
      {
    	  for(int i=0;i<s.length();i++)
    	  {
    		  long[] count=new long[16];
    		  String ch=s.substring(i, i+1); 
    		  for(int k=0;k<grid.size();k++)
    		  {
    			  if(i==0)
    			  {
    				  if(grid.get(k).equals(ch))
    				  {
    					  count[k]=1;
    				  }
    			  }
    			  else
    			  {
    				  if(grid.get(k).equals(ch))
    				  {
    					  if(k==0)
    					  {
    						  count[k]=(oldCount[k+1]+oldCount[k+4]+oldCount[k+5])%mod;
    					  }
    					  else if(k==1)
    					  {
    						  count[k]=(oldCount[k-1]+oldCount[k+1]+oldCount[k+4]+oldCount[k+5]+oldCount[k+3])%mod;
    					  }
    					  else if(k==2)
    					  {
    						  count[k]=(oldCount[k-1]+oldCount[k+1]+oldCount[k+4]+oldCount[k+5]+oldCount[k+3])%mod;
    					  }
    					  else if(k==3)
    		    		  {
    						  count[k]=(oldCount[k-1]+oldCount[k+4]+oldCount[k+3])%mod;
    		    			  
    		    		  }
    					  else if(k==7)
    					  {
    						  count[k]=(oldCount[k-1]+oldCount[k+4]+oldCount[k-4]+oldCount[k-5]+oldCount[k+3])%mod;
    					  }
    					  else if(k==11)
    					  {
    						  count[k]=(oldCount[k-1]+oldCount[k+4]+oldCount[k-4]+oldCount[k-5]+oldCount[k+3])%mod;
    					  }
    					  else if(k==13)
    					  {
    						  count[k]=(oldCount[k-1]+oldCount[k-4]+oldCount[k-5]+oldCount[k-3]+oldCount[k+1])%mod;
    					  }
    					  else if(k==14)
    					  {
    						  count[k]=(oldCount[k-1]+oldCount[k-4]+oldCount[k-5]+oldCount[k-3]+oldCount[k+1])%mod;
    					  }
    					  else if(k==15)
    					  {
    						  count[k]=(oldCount[k-1]+oldCount[k-4]+oldCount[k-5])%mod;
    					  }
    		    		  else if(k==4)
    		    		  {
    		    			  count[k]=(oldCount[k+1]+oldCount[k+4]+oldCount[k-4]+oldCount[k+5]+oldCount[k-3])%mod;
    		    			    
    		    		  }
    		    		  else if(k==8)
    		    		  {
    		    			  count[k]=(oldCount[k+1]+oldCount[k+4]+oldCount[k-4]+oldCount[k+5]+oldCount[k-3])%mod;
    		    			    
    		    		  }
    		    		  else if(k==12)
    		    		  {
    		    			  count[k]=(oldCount[k+1]+oldCount[k-4]+oldCount[k-3])%mod;
    		    			    
    		    		  }
    		    		  else
    		    		  {
    		    			  count[k]=(oldCount[k+1]+oldCount[k-1]+oldCount[k+4]+oldCount[k-4]+oldCount[k+5]+oldCount[k-3]+oldCount[k-5]+oldCount[k+3])%mod;
	    		    		  
    		    		  }
    				  }
    			  }
    		  }
    		  oldCount=count;
    	  }
    	  for(long a : oldCount)
    	  {
    		  smallCount=smallCount+a;
    	  }
    	  smallCount=smallCount%mod;
    	  
      }
      /*public void findWord(String s, int idx, int start)
      {
    	  
    	  String ch=s.substring(idx, idx+1);
    	  if(start<0||start>15);
    	  else if((!grid.get(start).equals(ch))&&idx==0)
    	  {
    		  findWord(s,idx,start+1);
    	  }
    	  else if((!grid.get(start).equals(ch))&&idx!=0);
    	  else
    	  {
    		  if((idx+1)==s.length())
    		  {
    			  smallCount++;
    		  }
    		  else
    		  {
    		  if(start==3||start==7||start==11)
    		  {
    			  //findWord(s,idx+1,start+1);
        		  findWord(s,idx+1,start-1);
        		  findWord(s,idx+1,start+4);
        		  findWord(s,idx+1,start-4);
        		  //findWord(s,idx+1,start-3);
        		  findWord(s,idx+1,start-5);
        		  findWord(s,idx+1,start+3);
        		  //findWord(s,idx+1,start+5); 
    		  }
    		  else if(start==4||start==8||start==12)
    		  {
    			  findWord(s,idx+1,start+1);
        		  //findWord(s,idx+1,start-1);
        		  findWord(s,idx+1,start+4);
        		  findWord(s,idx+1,start-4);
        		  findWord(s,idx+1,start-3);
        		  //findWord(s,idx+1,start-5);
        		  //findWord(s,idx+1,start+3);
        		  findWord(s,idx+1,start+5);  
    		  }
    		  else
    		  {
    		  findWord(s,idx+1,start+1);
    		  findWord(s,idx+1,start-1);
    		  findWord(s,idx+1,start+4);
    		  findWord(s,idx+1,start-4);
    		  findWord(s,idx+1,start-3);
    		  findWord(s,idx+1,start-5);
    		  findWord(s,idx+1,start+3);
    		  findWord(s,idx+1,start+5);
    		  }
    		  }
    	  
    	  }
    	  
      }*/
  }