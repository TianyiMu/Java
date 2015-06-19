import java.util.ArrayList;
import java.util.Arrays;


public class PizzaToppings
   {
      public int whichToppings(String[] menu, String[] favorites){
         int[] count = new int[favorites.length];
         ArrayList <String> menuList = new ArrayList <String>();
         for(int k=0;k<menu.length;k++)
    	 {
    		 menuList.add(menu[k]);
    	 }
         for(int i=0;i<favorites.length;i++)
         {
        	 String[] select = favorites[i].split(" ");
        	 for(int j=0;j<select.length;j++)
        	 {
        		 if(menuList.contains(select[j]))
        		 {
        			 count[i]++;
        		 }
        		 else
        		 {
        			 count[i]=0;
        			 break;
        		 }
        	 }
         }
         int temp=0;
         int counter=-1;
         for(int i=0;i<count.length;i++)
         {
        	 if(count[i]>temp)
        	 {
        		 counter=i;
        		 temp=count[i];
        		 break;
        	 }
         }
         
         return counter;
      }
   }