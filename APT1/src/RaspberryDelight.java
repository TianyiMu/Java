public class RaspberryDelight{
      public int toasts(int upper_limit, int layer_count){
         
         int times = (int) Math.ceil((double)layer_count/upper_limit);
         return times;
      }
  }