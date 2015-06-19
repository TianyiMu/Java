import java.util.ArrayList;
import java.util.Arrays;

public class OlympicSort {
        class Country implements Comparable 
        {
                String countryName;
                int ggg, sss, bbb;
                Country(String name, int ggg, int sss, int bbb) 
                {
                        this.countryName=new String(name);
                        this.ggg=ggg;
                        this.sss=sss;
                        this.bbb=bbb;
                }
                public int compareTo(Object obj) 
                {
                        Country count = (Country) obj;
                        if(ggg!=count.ggg)
                                return count.ggg-ggg;
                        if(sss!=count.sss)
                                return count.sss-sss;
                        if(bbb!=count.bbb)
                                return count.bbb-bbb;
                        return countryName.compareTo(count.countryName);
                }
                public String toString()
                {
                        return countryName + " " + ggg + " " + sss + " " + bbb;
                }
        }

        public String[] standings(String[] results) 
        {
                ArrayList nameList = new ArrayList();
                for(int i=0;i<results.length;i++) 
                {
                        String[] temp=results[i].split(" ");
                        for(int k=0;k<temp.length;k++)
                        {
	                        	if (!nameList.contains(temp[k]))
	                        		nameList.add(temp[k]);
                        }
                }

                Country[] countryList=new Country[nameList.size()];
                for(int i=0;i<nameList.size();i++) 
                        countryList[i] = new Country((String)nameList.get(i), 0, 0, 0);

                for(int i=0;i<results.length;i++) 
                {
                        String[] temp=results[i].split(" ");
                        int now=0;
                        for(int k=0;k<temp.length;k++)
                        {
	                        	String name=temp[k];
	                        	int idx=nameList.indexOf(name);
	                        	if(now==0)
	                        		countryList[idx].ggg++;
	                        	if(now==1)
	                        		countryList[idx].sss++;
	                        	if(now==2)
	                        		countryList[idx].bbb++;
	                        	now++;
                        }
                }
                Arrays.sort(countryList);
                String[] output=new String[nameList.size()];
                for (int i=0;i<nameList.size();i++)
                		output[i]=countryList[i].toString();
                return output;
        }

}