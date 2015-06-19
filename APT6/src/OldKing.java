import java.util.*;

public class OldKing {
	private HashSet<String> taken=new HashSet<String>();
	private String upper="";
	private String lower="a";
	private ArrayList<String> path=new ArrayList<String>();
	private String next="";
	private boolean go;
	private String test="";
	private String size;
      public String kingPath(int n, String initSquare) {
    	  size=""+n;
          taken.add(initSquare);
          path.add(initSquare);
          next=initSquare;
          upper=Character.toString ((char)(n+96));
          go=true;
          while(go)
          {
        	  find();
          }
          String output=path.get(0);
          for(int i=1;i<path.size()-1;i++)
          {
        	  output=output+"-"+path.get(i);
          }
          if(output.length()>40)
          {
        	  	output=output.substring(0, 20)+"..."+output.substring(output.length()-20, output.length());
          }
          return output;
          
      }
      private void find()
      {
    	  go=false;
    	  String c=next.substring(0, 1);
    	  String r=next.substring(1);
    	  int charValueC = c.charAt(0);
    	  int charValueR = r.charAt(0);
    	  String up = c+String.valueOf( (char) (charValueR + 1));
    	  String down = c+String.valueOf( (char) (charValueR - 1));
    	  String left = String.valueOf( (char) (charValueC - 1))+r;
    	  String right = String.valueOf( (char) (charValueC + 1))+r;
    	  ArrayList<String> possible=new ArrayList<String>();
    	  possible.add(up);
    	  possible.add(down);
    	  possible.add(left);
    	  possible.add(right);
    	  Collections.sort(possible);
    	  Collections.reverse(possible);
    	  for(int i=0;i<possible.size();i++)
    	  {
    		  String temp=possible.get(i);
    		  String ct=temp.substring(0, 1);
        	  String rt=temp.substring(1);
    		  if(ct.compareTo(lower)>=0&&ct.compareTo(upper)<=0&&rt.compareTo("1")>=0&&rt.compareTo(size)<=0&&!taken.contains(possible.get(i)))
    		  {
    			  go=true;
    			  next=possible.get(i);
    			  break;
    		  }
    	  }
    	  possible.clear();
    	  taken.add(next);
    	  path.add(next);
    	  
    	  
      }
  }