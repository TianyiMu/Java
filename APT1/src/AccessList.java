public class AccessList {
     public String mayAccess(int[] rights, int minPermission) {
            String access = "";
            for (int i=0; i<rights.length; i++)
            {
            	if(rights[i]<minPermission)
            	{
            		access = access + "D";
            	}
            	else
            	{
            		access = access + "A";
            	}
            }
            return access;
     }
  }