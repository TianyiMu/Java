public class Freefall {
      public double fallingDistance(double time, double velo){
        double distance =0;
        distance = time*velo+0.5*9.8*time*time;
        
        return distance;
      }
  }