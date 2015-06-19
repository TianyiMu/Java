 public class DNAMutate {
      public String mutate(String dna) {
          String r="";
          for (int i=dna.length();i>=1;i--)
          {
        	  r=r+dna.substring(i-1, i);
          }
          return r;
      }
   }
